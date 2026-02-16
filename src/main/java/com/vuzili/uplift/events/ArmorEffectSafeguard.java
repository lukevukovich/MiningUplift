package com.vuzili.uplift.events;

import com.vuzili.uplift.init.EffectInit;
import com.vuzili.uplift.init.ItemInit;
import com.vuzili.uplift.objects.items.RoseGoldWeapon;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

/**
 * Safeguard that removes orphaned armor potion effects.
 *
 * When armor is removed through non-standard means (e.g. /clear command, death
 * with keepInventory, or external inventory manipulation), onArmorTick() never
 * fires to clean up the Integer.MAX_VALUE-duration effects. This listener runs
 * every tick AFTER armor ticking (Phase.END) and removes any armor-granted
 * effects whose corresponding full armor set is no longer equipped.
 *
 * Only effects with very long durations (> Integer.MAX_VALUE / 2) are removed,
 * so normal potion effects from brewing, beacons, etc. are never affected.
 */
@Mod.EventBusSubscriber(modid = "uplift", bus = Bus.FORGE)
public class ArmorEffectSafeguard {

	/**
	 * Duration threshold to identify armor-granted effects.
	 * Armor effects use Integer.MAX_VALUE; no vanilla/brewed potion comes close.
	 */
	private static final int ARMOR_DURATION_THRESHOLD = Integer.MAX_VALUE / 2;

	@SubscribeEvent
	public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
		if (event.phase != TickEvent.Phase.END) {
			return;
		}
		PlayerEntity player = event.player;
		if (player.world.isRemote) {
			return;
		}

		// Amethyst -> Speed
		removeOrphanedEffect(player, Effects.SPEED,
				ItemInit.amethyst_helmet, ItemInit.amethyst_chestplate,
				ItemInit.amethyst_leggings, ItemInit.amethyst_boots);

		// Burning Diamond -> Fire Resistance
		removeOrphanedEffect(player, Effects.FIRE_RESISTANCE,
				ItemInit.burning_diamond_helmet, ItemInit.burning_diamond_chestplate,
				ItemInit.burning_diamond_leggings, ItemInit.burning_diamond_boots);

		// Bloodstone -> Instant Health, Blindness, Slowness, Mining Fatigue
		if (!isWearingFullSet(player,
				ItemInit.bloodstone_helmet, ItemInit.bloodstone_chestplate,
				ItemInit.bloodstone_leggings, ItemInit.bloodstone_boots)) {
			removeIfArmorGranted(player, Effects.INSTANT_HEALTH);
			removeIfArmorGranted(player, Effects.BLINDNESS);
			removeIfArmorGranted(player, Effects.SLOWNESS);
			removeIfArmorGranted(player, Effects.MINING_FATIGUE);
		}

		// Chrome -> Luminous (custom effect)
		if (EffectInit.LUMINOUS != null) {
			removeOrphanedEffect(player, EffectInit.LUMINOUS,
					ItemInit.chrome_helmet, ItemInit.chrome_chestplate,
					ItemInit.chrome_leggings, ItemInit.chrome_boots);
		}

		// Ender -> Flight (custom effect)
		if (EffectInit.FLIGHT != null) {
			removeOrphanedEffect(player, EffectInit.FLIGHT,
					ItemInit.ender_helmet, ItemInit.ender_chestplate,
					ItemInit.ender_leggings, ItemInit.ender_boots);
		}

		// Obsidian -> Night Vision
		removeOrphanedEffect(player, Effects.NIGHT_VISION,
				ItemInit.obsidian_helmet, ItemInit.obsidian_chestplate,
				ItemInit.obsidian_leggings, ItemInit.obsidian_boots);

		// Platinum -> Strength
		removeOrphanedEffect(player, Effects.STRENGTH,
				ItemInit.platinum_helmet, ItemInit.platinum_chestplate,
				ItemInit.platinum_leggings, ItemInit.platinum_boots);

		// Ruby -> Resistance
		removeOrphanedEffect(player, Effects.RESISTANCE,
				ItemInit.ruby_helmet, ItemInit.ruby_chestplate,
				ItemInit.ruby_leggings, ItemInit.ruby_boots);

		// Sapphire -> Water Breathing
		removeOrphanedEffect(player, Effects.WATER_BREATHING,
				ItemInit.sapphire_helmet, ItemInit.sapphire_chestplate,
				ItemInit.sapphire_leggings, ItemInit.sapphire_boots);

		// Uranium -> Haste
		removeOrphanedEffect(player, Effects.HASTE,
			ItemInit.uranium_helmet, ItemInit.uranium_chestplate,
			ItemInit.uranium_leggings, ItemInit.uranium_boots);
	}

	/**
	 * Remove an effect if the player has it at armor-level duration
	 * but is NOT wearing the corresponding full armor set.
	 * Also skips removal if a held RoseGoldWeapon provides the same effect,
	 * so the weapon and armor systems don't conflict.
	 */
	private static void removeOrphanedEffect(PlayerEntity player, Effect effect,
			Item helmet, Item chestplate, Item leggings, Item boots) {
		if (isArmorGrantedEffect(player, effect)
				&& !isWearingFullSet(player, helmet, chestplate, leggings, boots)
				&& !isHeldRoseGoldProviding(player, effect)) {
			player.removePotionEffect(effect);
		}
	}

	/**
	 * Remove an effect if the player currently has it at armor-level duration.
	 * Used when the armor set check is done externally (e.g. Bloodstone with multiple effects).
	 */
	private static void removeIfArmorGranted(PlayerEntity player, Effect effect) {
		if (isArmorGrantedEffect(player, effect)) {
			player.removePotionEffect(effect);
		}
	}

	/**
	 * Check if the player has an active effect that was granted by armor.
	 * Armor effects are identified by their extremely long duration (Integer.MAX_VALUE).
	 */
	private static boolean isArmorGrantedEffect(PlayerEntity player, Effect effect) {
		EffectInstance instance = player.getActivePotionEffect(effect);
		return instance != null && instance.getDuration() > ARMOR_DURATION_THRESHOLD;
	}

	/**
	 * Check if the player is holding a RoseGoldWeapon that provides the given effect.
	 */
	private static boolean isHeldRoseGoldProviding(PlayerEntity player, Effect effect) {
		return isRoseGoldWithEffect(player.getHeldItemMainhand(), effect)
				|| isRoseGoldWithEffect(player.getHeldItemOffhand(), effect);
	}

	private static boolean isRoseGoldWithEffect(ItemStack stack, Effect effect) {
		if (stack.isEmpty()) return false;
		Item item = stack.getItem();
		return item instanceof RoseGoldWeapon && effect.equals(((RoseGoldWeapon) item).getEffect());
	}

	/**
	 * Check if the player is wearing a complete matching armor set.
	 */
	private static boolean isWearingFullSet(PlayerEntity player,
			Item helmet, Item chestplate, Item leggings, Item boots) {
		ItemStack head = player.getItemStackFromSlot(EquipmentSlotType.HEAD);
		ItemStack chest = player.getItemStackFromSlot(EquipmentSlotType.CHEST);
		ItemStack legs = player.getItemStackFromSlot(EquipmentSlotType.LEGS);
		ItemStack feet = player.getItemStackFromSlot(EquipmentSlotType.FEET);
		return head.getItem() == helmet
				&& chest.getItem() == chestplate
				&& legs.getItem() == leggings
				&& feet.getItem() == boots;
	}
}
