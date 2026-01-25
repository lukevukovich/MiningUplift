package com.vuzili.uplift.init;

import com.google.common.base.Supplier;
import com.vuzili.uplift.Uplift;
import com.vuzili.uplift.Uplift.UpliftItemGroup;
import com.vuzili.uplift.objects.entity.MineBomb;
import com.vuzili.uplift.objects.entity.UraniumBomb;
import com.vuzili.uplift.objects.items.ArmorPotionBloodstone;
import com.vuzili.uplift.objects.items.ArmorPotionBurningDiamond;
import com.vuzili.uplift.objects.items.ArmorPotionChrome;
import com.vuzili.uplift.objects.items.ArmorPotionEnder;
import com.vuzili.uplift.objects.items.ArmorPotionShadowglass;
import com.vuzili.uplift.objects.items.ArmorPotionPlatinum;
import com.vuzili.uplift.objects.items.ArmorPotionRuby;
import com.vuzili.uplift.objects.items.ArmorPotionSapphire;
import com.vuzili.uplift.objects.items.ArmorPotionTourmaline;
import com.vuzili.uplift.objects.items.ArmorPotionUranium;
import com.vuzili.uplift.objects.items.BurningDiamondFuel;
import com.vuzili.uplift.objects.items.Igniter;
import com.vuzili.uplift.objects.items.ModSpawnEggItem;
import com.vuzili.uplift.objects.items.RoseGoldWeapon;
import com.vuzili.uplift.objects.items.StoneSoup;
import com.vuzili.uplift.objects.items.TasmaniteFuel;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Food;
import net.minecraft.item.HoeItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.SwordItem;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.LazyValue;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(modid = Uplift.MOD_ID, bus= Bus.MOD)
@ObjectHolder(Uplift.MOD_ID)
public class ItemInit 
{
	//Ruby
	public static final Item ruby = null;
	
	public static final Item ruby_sword = null;
	public static final Item ruby_pickaxe = null;
	public static final Item ruby_shovel = null;
	public static final Item ruby_axe = null;
	public static final Item ruby_hoe = null;
	
	public static final Item ruby_helmet = null;
	public static final Item ruby_chestplate = null;
	public static final Item ruby_leggings = null;
	public static final Item ruby_boots = null;
	
	//Burning Diamond
	public static final Item burning_diamond = null;
	public static final Item burning_dust = null;
	
	public static final Item burning_diamond_sword = null;
	public static final Item burning_diamond_pickaxe = null;
	public static final Item burning_diamond_shovel = null;
	public static final Item burning_diamond_axe = null;
	public static final Item burning_diamond_hoe = null;
	
	public static final Item burning_diamond_helmet = null;
	public static final Item burning_diamond_chestplate = null;
	public static final Item burning_diamond_leggings = null;
	public static final Item burning_diamond_boots = null;
	public static final Item igniter = null;
	
	//Sapphire
	public static final Item sapphire = null;
	
	public static final Item sapphire_sword = null;
	public static final Item sapphire_pickaxe = null;
	public static final Item sapphire_shovel = null;
	public static final Item sapphire_axe = null;
	public static final Item sapphire_hoe = null;
	
	public static final Item sapphire_helmet = null;
	public static final Item sapphire_chestplate = null;
	public static final Item sapphire_leggings = null;
	public static final Item sapphire_boots = null;
	
	//Tourmaline
	public static final Item tourmaline = null;
	
	public static final Item tourmaline_sword = null;
	public static final Item tourmaline_pickaxe = null;
	public static final Item tourmaline_shovel = null;
	public static final Item tourmaline_axe = null;
	public static final Item tourmaline_hoe = null;
	
	public static final Item tourmaline_helmet = null;
	public static final Item tourmaline_chestplate = null;
	public static final Item tourmaline_leggings = null;
	public static final Item tourmaline_boots = null;
	
	//Shadowglass
	public static final Item shadow_dust = null;
	public static final Item shadowglass = null;
	
	public static final Item shadowglass_sword = null;
	public static final Item shadowglass_pickaxe = null;
	public static final Item shadowglass_shovel = null;
	public static final Item shadowglass_axe = null;
	public static final Item shadowglass_hoe = null;
	
	public static final Item shadowglass_helmet = null;
	public static final Item shadowglass_chestplate = null;
	public static final Item shadowglass_leggings = null;
	public static final Item shadowglass_boots = null;
	
	//Platinum
	public static final Item platinum_ingot = null;
	public static final Item platinum_nugget = null;
	
	public static final Item platinum_sword = null;
	public static final Item platinum_pickaxe = null;
	public static final Item platinum_shovel = null;
	public static final Item platinum_axe = null;
	public static final Item platinum_hoe = null;
	
	public static final Item platinum_helmet = null;
	public static final Item platinum_chestplate = null;
	public static final Item platinum_leggings = null;
	public static final Item platinum_boots = null;
	
	//Uranium
	public static final Item uranium_ingot = null;
	public static final Item uranium_nugget = null;
	
	public static final Item uranium_sword = null;
	public static final Item uranium_pickaxe = null;
	public static final Item uranium_shovel = null;
	public static final Item uranium_axe = null;
	public static final Item uranium_hoe = null;
	
	public static final Item uranium_helmet = null;
	public static final Item uranium_chestplate = null;
	public static final Item uranium_leggings = null;
	public static final Item uranium_boots = null;
	public static final Item uranium_bomb = null;
	
	//Tasmanite
	public static final Item tasmanite = null;
	
	//Bloodstone
	public static final Item bloodstone = null;
	public static final Item bloodstone_helmet = null;
	public static final Item bloodstone_chestplate = null;
	public static final Item bloodstone_leggings = null;
	public static final Item bloodstone_boots = null;
	
	//Ender
	public static final Item ender_gem = null;
	public static final Item ender_helmet = null;
	public static final Item ender_chestplate = null;
	public static final Item ender_leggings = null;
	public static final Item ender_boots = null;

	public static final Item ender_sword = null;
	public static final Item ender_pickaxe = null;
	public static final Item ender_shovel = null;
	public static final Item ender_axe = null;
	public static final Item ender_hoe = null;

	//Chrome
	public static final Item chrome_ingot = null;
	public static final Item chrome_nugget = null;

	public static final Item chrome_helmet = null;
	public static final Item chrome_chestplate = null;
	public static final Item chrome_leggings = null;
	public static final Item chrome_boots = null;
	
	//Opal
	public static final Item opal = null;
	
	//Rose Gold
	public static final Item rose_gold_ingot = null;
	public static final Item rose_gold_nugget = null;
	public static final Item rose_gold_sword = null;
	public static final Item rose_gold_axe = null;
	public static final Item rose_golden_apple = null;
	
	//Gem
	public static final Item gem_dust = null;
	
	//Mine Bomb
	public static final Item mine_bomb = null;
	
	//SpawnEggs
	public static final Item stone_spawn_egg = null;
	
	//Stone Meal
	public static final Item stone_meal = null;
	public static final Item stone_soup = null;

	@SuppressWarnings("deprecation")
	@SubscribeEvent
	public static void registerItems(final RegistryEvent.Register<Item> event)
	{
		//Ruby
		event.getRegistry().register(new Item(new Item.Properties().group(UpliftItemGroup.instance)).setRegistryName("ruby"));
		
		event.getRegistry().register(new SwordItem(ModItemTier.RUBY, 6, -2.4F, new Item.Properties().group(UpliftItemGroup.instance)).setRegistryName("ruby_sword"));
		event.getRegistry().register(new PickaxeItem(ModItemTier.RUBY, 3, -2.8F, new Item.Properties().group(UpliftItemGroup.instance)).setRegistryName("ruby_pickaxe"));
		event.getRegistry().register(new ShovelItem(ModItemTier.RUBY, 3.5f, -3.0F, new Item.Properties().group(UpliftItemGroup.instance)).setRegistryName("ruby_shovel"));
		event.getRegistry().register(new AxeItem(ModItemTier.RUBY, 9, -3.2F, new Item.Properties().group(UpliftItemGroup.instance)).setRegistryName("ruby_axe"));
		event.getRegistry().register(new HoeItem(ModItemTier.RUBY, -2.0f, new Item.Properties().group(UpliftItemGroup.instance)).setRegistryName("ruby_hoe"));
	
		event.getRegistry().register(new ArmorPotionRuby(ModArmorMaterial.RUBY, EquipmentSlotType.HEAD, new Item.Properties().group(UpliftItemGroup.instance)).setRegistryName("ruby_helmet"));
		event.getRegistry().register(new ArmorPotionRuby(ModArmorMaterial.RUBY, EquipmentSlotType.CHEST, new Item.Properties().group(UpliftItemGroup.instance)).setRegistryName("ruby_chestplate"));
		event.getRegistry().register(new ArmorPotionRuby(ModArmorMaterial.RUBY, EquipmentSlotType.LEGS, new Item.Properties().group(UpliftItemGroup.instance)).setRegistryName("ruby_leggings"));
		event.getRegistry().register(new ArmorPotionRuby(ModArmorMaterial.RUBY, EquipmentSlotType.FEET, new Item.Properties().group(UpliftItemGroup.instance)).setRegistryName("ruby_boots"));
		
		//Burning Diamond
		event.getRegistry().register(new BurningDiamondFuel(new Item.Properties().group(UpliftItemGroup.instance)).setRegistryName("burning_diamond"));
		event.getRegistry().register(new Item(new Item.Properties().group(UpliftItemGroup.instance)).setRegistryName("burning_dust"));
		
		event.getRegistry().register(new SwordItem(ModItemTier.BURNING_DIAMOND, 8, -2.4F, new Item.Properties().group(UpliftItemGroup.instance)).setRegistryName("burning_diamond_sword"));
		event.getRegistry().register(new PickaxeItem(ModItemTier.BURNING_DIAMOND, 5, -2.8F, new Item.Properties().group(UpliftItemGroup.instance)).setRegistryName("burning_diamond_pickaxe"));
		event.getRegistry().register(new ShovelItem(ModItemTier.BURNING_DIAMOND, 6.5f, -3.0F, new Item.Properties().group(UpliftItemGroup.instance)).setRegistryName("burning_diamond_shovel"));
		event.getRegistry().register(new AxeItem(ModItemTier.BURNING_DIAMOND, 9, -3.0F, new Item.Properties().group(UpliftItemGroup.instance)).setRegistryName("burning_diamond_axe"));
		event.getRegistry().register(new HoeItem(ModItemTier.BURNING_DIAMOND, 0.0f, new Item.Properties().group(UpliftItemGroup.instance)).setRegistryName("burning_diamond_hoe"));
		
		event.getRegistry().register(new ArmorPotionBurningDiamond(ModArmorMaterial.BURNING_DIAMOND, EquipmentSlotType.HEAD, new Item.Properties().group(UpliftItemGroup.instance)).setRegistryName("burning_diamond_helmet"));
		event.getRegistry().register(new ArmorPotionBurningDiamond(ModArmorMaterial.BURNING_DIAMOND, EquipmentSlotType.CHEST, new Item.Properties().group(UpliftItemGroup.instance)).setRegistryName("burning_diamond_chestplate"));
		event.getRegistry().register(new ArmorPotionBurningDiamond(ModArmorMaterial.BURNING_DIAMOND, EquipmentSlotType.LEGS, new Item.Properties().group(UpliftItemGroup.instance)).setRegistryName("burning_diamond_leggings"));
		event.getRegistry().register(new ArmorPotionBurningDiamond(ModArmorMaterial.BURNING_DIAMOND, EquipmentSlotType.FEET, new Item.Properties().group(UpliftItemGroup.instance)).setRegistryName("burning_diamond_boots"));
		event.getRegistry().register(new Igniter(new Item.Properties().group(UpliftItemGroup.instance).maxDamage(200)).setRegistryName("igniter"));
		
		//Sapphire
		event.getRegistry().register(new Item(new Item.Properties().group(UpliftItemGroup.instance)).setRegistryName("sapphire"));
		
		event.getRegistry().register(new SwordItem(ModItemTier.SAPPHIRE, 6, -2.4F, new Item.Properties().group(UpliftItemGroup.instance)).setRegistryName("sapphire_sword"));
		event.getRegistry().register(new PickaxeItem(ModItemTier.SAPPHIRE, 4, -2.8F, new Item.Properties().group(UpliftItemGroup.instance)).setRegistryName("sapphire_pickaxe"));
		event.getRegistry().register(new ShovelItem(ModItemTier.SAPPHIRE, 4.5f, -3.0F, new Item.Properties().group(UpliftItemGroup.instance)).setRegistryName("sapphire_shovel"));
		event.getRegistry().register(new AxeItem(ModItemTier.SAPPHIRE, 9, -3.1F, new Item.Properties().group(UpliftItemGroup.instance)).setRegistryName("sapphire_axe"));
		event.getRegistry().register(new HoeItem(ModItemTier.SAPPHIRE, -1.0f, new Item.Properties().group(UpliftItemGroup.instance)).setRegistryName("sapphire_hoe"));
		
		event.getRegistry().register(new ArmorPotionSapphire(ModArmorMaterial.SAPPHIRE, EquipmentSlotType.HEAD, new Item.Properties().group(UpliftItemGroup.instance)).setRegistryName("sapphire_helmet"));
		event.getRegistry().register(new ArmorPotionSapphire(ModArmorMaterial.SAPPHIRE, EquipmentSlotType.CHEST, new Item.Properties().group(UpliftItemGroup.instance)).setRegistryName("sapphire_chestplate"));
		event.getRegistry().register(new ArmorPotionSapphire(ModArmorMaterial.SAPPHIRE, EquipmentSlotType.LEGS, new Item.Properties().group(UpliftItemGroup.instance)).setRegistryName("sapphire_leggings"));
		event.getRegistry().register(new ArmorPotionSapphire(ModArmorMaterial.SAPPHIRE, EquipmentSlotType.FEET, new Item.Properties().group(UpliftItemGroup.instance)).setRegistryName("sapphire_boots"));
		
		//Tourmaline
		event.getRegistry().register(new Item(new Item.Properties().group(UpliftItemGroup.instance)).setRegistryName("tourmaline"));
		
		event.getRegistry().register(new SwordItem(ModItemTier.TOURMALINE, 6, -2.4F, new Item.Properties().group(UpliftItemGroup.instance)).setRegistryName("tourmaline_sword"));
		event.getRegistry().register(new PickaxeItem(ModItemTier.TOURMALINE, 5, -2.8F, new Item.Properties().group(UpliftItemGroup.instance)).setRegistryName("tourmaline_pickaxe"));
		event.getRegistry().register(new ShovelItem(ModItemTier.TOURMALINE, 5.5f, -3.0F, new Item.Properties().group(UpliftItemGroup.instance)).setRegistryName("tourmaline_shovel"));
		event.getRegistry().register(new AxeItem(ModItemTier.TOURMALINE, 9, -3.0F, new Item.Properties().group(UpliftItemGroup.instance)).setRegistryName("tourmaline_axe"));
		event.getRegistry().register(new HoeItem(ModItemTier.TOURMALINE, 0.0f, new Item.Properties().group(UpliftItemGroup.instance)).setRegistryName("tourmaline_hoe"));
		
		event.getRegistry().register(new ArmorPotionTourmaline(ModArmorMaterial.TOURMALINE, EquipmentSlotType.HEAD, new Item.Properties().group(UpliftItemGroup.instance)).setRegistryName("tourmaline_helmet"));
		event.getRegistry().register(new ArmorPotionTourmaline(ModArmorMaterial.TOURMALINE, EquipmentSlotType.CHEST, new Item.Properties().group(UpliftItemGroup.instance)).setRegistryName("tourmaline_chestplate"));
		event.getRegistry().register(new ArmorPotionTourmaline(ModArmorMaterial.TOURMALINE, EquipmentSlotType.LEGS, new Item.Properties().group(UpliftItemGroup.instance)).setRegistryName("tourmaline_leggings"));
		event.getRegistry().register(new ArmorPotionTourmaline(ModArmorMaterial.TOURMALINE, EquipmentSlotType.FEET, new Item.Properties().group(UpliftItemGroup.instance)).setRegistryName("tourmaline_boots"));
		
		//Shadowglass
		event.getRegistry().register(new Item(new Item.Properties().group(UpliftItemGroup.instance)).setRegistryName("shadow_dust"));
		event.getRegistry().register(new Item(new Item.Properties().group(UpliftItemGroup.instance)).setRegistryName("shadowglass"));
		
		event.getRegistry().register(new SwordItem(ModItemTier.SHADOWGLASS, 7, -2.4F, new Item.Properties().group(UpliftItemGroup.instance)).setRegistryName("shadowglass_sword"));
		event.getRegistry().register(new PickaxeItem(ModItemTier.SHADOWGLASS, 5, -2.8F, new Item.Properties().group(UpliftItemGroup.instance)).setRegistryName("shadowglass_pickaxe"));
		event.getRegistry().register(new ShovelItem(ModItemTier.SHADOWGLASS, 4.5f, -3.0F, new Item.Properties().group(UpliftItemGroup.instance)).setRegistryName("shadowglass_shovel"));
		event.getRegistry().register(new AxeItem(ModItemTier.SHADOWGLASS, 9, -3.1F, new Item.Properties().group(UpliftItemGroup.instance)).setRegistryName("shadowglass_axe"));
		event.getRegistry().register(new HoeItem(ModItemTier.SHADOWGLASS, -1.0f, new Item.Properties().group(UpliftItemGroup.instance)).setRegistryName("shadowglass_hoe"));
		
		event.getRegistry().register(new ArmorPotionShadowglass(ModArmorMaterial.SHADOWGLASS, EquipmentSlotType.HEAD, new Item.Properties().group(UpliftItemGroup.instance)).setRegistryName("shadowglass_helmet"));
		event.getRegistry().register(new ArmorPotionShadowglass(ModArmorMaterial.SHADOWGLASS, EquipmentSlotType.CHEST, new Item.Properties().group(UpliftItemGroup.instance)).setRegistryName("shadowglass_chestplate"));
		event.getRegistry().register(new ArmorPotionShadowglass(ModArmorMaterial.SHADOWGLASS, EquipmentSlotType.LEGS, new Item.Properties().group(UpliftItemGroup.instance)).setRegistryName("shadowglass_leggings"));
		event.getRegistry().register(new ArmorPotionShadowglass(ModArmorMaterial.SHADOWGLASS, EquipmentSlotType.FEET, new Item.Properties().group(UpliftItemGroup.instance)).setRegistryName("shadowglass_boots"));
		
		//Platinum
		event.getRegistry().register(new Item(new Item.Properties().group(UpliftItemGroup.instance)).setRegistryName("platinum_ingot"));
		event.getRegistry().register(new Item(new Item.Properties().group(UpliftItemGroup.instance)).setRegistryName("platinum_nugget"));
		
		event.getRegistry().register(new SwordItem(ModItemTier.PLATINUM, 10, -2.4F, new Item.Properties().group(UpliftItemGroup.instance)).setRegistryName("platinum_sword"));
		event.getRegistry().register(new PickaxeItem(ModItemTier.PLATINUM, 6, -2.8F, new Item.Properties().group(UpliftItemGroup.instance)).setRegistryName("platinum_pickaxe"));
		event.getRegistry().register(new ShovelItem(ModItemTier.PLATINUM, 7.0f, -3.0F, new Item.Properties().group(UpliftItemGroup.instance)).setRegistryName("platinum_shovel"));
		event.getRegistry().register(new AxeItem(ModItemTier.PLATINUM, 11, -3.0F, new Item.Properties().group(UpliftItemGroup.instance)).setRegistryName("platinum_axe"));
		event.getRegistry().register(new HoeItem(ModItemTier.PLATINUM, 0.0f, new Item.Properties().group(UpliftItemGroup.instance)).setRegistryName("platinum_hoe"));
		
		event.getRegistry().register(new ArmorPotionPlatinum(ModArmorMaterial.PLATINUM, EquipmentSlotType.HEAD, new Item.Properties().group(UpliftItemGroup.instance)).setRegistryName("platinum_helmet"));
		event.getRegistry().register(new ArmorPotionPlatinum(ModArmorMaterial.PLATINUM, EquipmentSlotType.CHEST, new Item.Properties().group(UpliftItemGroup.instance)).setRegistryName("platinum_chestplate"));
		event.getRegistry().register(new ArmorPotionPlatinum(ModArmorMaterial.PLATINUM, EquipmentSlotType.LEGS, new Item.Properties().group(UpliftItemGroup.instance)).setRegistryName("platinum_leggings"));
		event.getRegistry().register(new ArmorPotionPlatinum(ModArmorMaterial.PLATINUM, EquipmentSlotType.FEET, new Item.Properties().group(UpliftItemGroup.instance)).setRegistryName("platinum_boots"));
		
		//Uranium
		event.getRegistry().register(new Item(new Item.Properties().group(UpliftItemGroup.instance)).setRegistryName("uranium_ingot"));
		event.getRegistry().register(new Item(new Item.Properties().group(UpliftItemGroup.instance)).setRegistryName("uranium_nugget"));
		
		event.getRegistry().register(new SwordItem(ModItemTier.URANIUM, 7, -2.4F, new Item.Properties().group(UpliftItemGroup.instance)).setRegistryName("uranium_sword"));
		event.getRegistry().register(new PickaxeItem(ModItemTier.URANIUM, 5, -2.8F, new Item.Properties().group(UpliftItemGroup.instance)).setRegistryName("uranium_pickaxe"));
		event.getRegistry().register(new ShovelItem(ModItemTier.URANIUM, 5.5f, -3.0F, new Item.Properties().group(UpliftItemGroup.instance)).setRegistryName("uranium_shovel"));
		event.getRegistry().register(new AxeItem(ModItemTier.URANIUM, 9, -3.0F, new Item.Properties().group(UpliftItemGroup.instance)).setRegistryName("uranium_axe"));
		event.getRegistry().register(new HoeItem(ModItemTier.URANIUM, -1.0f, new Item.Properties().group(UpliftItemGroup.instance)).setRegistryName("uranium_hoe"));
		
		event.getRegistry().register(new ArmorPotionUranium(ModArmorMaterial.URANIUM, EquipmentSlotType.HEAD, new Item.Properties().group(UpliftItemGroup.instance)).setRegistryName("uranium_helmet"));
		event.getRegistry().register(new ArmorPotionUranium(ModArmorMaterial.URANIUM, EquipmentSlotType.CHEST, new Item.Properties().group(UpliftItemGroup.instance)).setRegistryName("uranium_chestplate"));
		event.getRegistry().register(new ArmorPotionUranium(ModArmorMaterial.URANIUM, EquipmentSlotType.LEGS, new Item.Properties().group(UpliftItemGroup.instance)).setRegistryName("uranium_leggings"));
		event.getRegistry().register(new ArmorPotionUranium(ModArmorMaterial.URANIUM, EquipmentSlotType.FEET, new Item.Properties().group(UpliftItemGroup.instance)).setRegistryName("uranium_boots"));
		
		event.getRegistry().register(new UraniumBomb(new Item.Properties().group(UpliftItemGroup.instance).maxStackSize(4)).setRegistryName("uranium_bomb"));
		
		//Tasmanite
		event.getRegistry().register(new TasmaniteFuel(new Item.Properties().group(UpliftItemGroup.instance)).setRegistryName("tasmanite"));
		
		//Bloodstone
		event.getRegistry().register(new Item(new Item.Properties().group(UpliftItemGroup.instance)).setRegistryName("bloodstone"));
		event.getRegistry().register(new ArmorPotionBloodstone(ModArmorMaterial.BLOODSTONE, EquipmentSlotType.HEAD, new Item.Properties().group(UpliftItemGroup.instance)).setRegistryName("bloodstone_helmet"));
		event.getRegistry().register(new ArmorPotionBloodstone(ModArmorMaterial.BLOODSTONE, EquipmentSlotType.CHEST, new Item.Properties().group(UpliftItemGroup.instance)).setRegistryName("bloodstone_chestplate"));
		event.getRegistry().register(new ArmorPotionBloodstone(ModArmorMaterial.BLOODSTONE, EquipmentSlotType.LEGS, new Item.Properties().group(UpliftItemGroup.instance)).setRegistryName("bloodstone_leggings"));
		event.getRegistry().register(new ArmorPotionBloodstone(ModArmorMaterial.BLOODSTONE, EquipmentSlotType.FEET, new Item.Properties().group(UpliftItemGroup.instance)).setRegistryName("bloodstone_boots"));
		
		//Ender
		event.getRegistry().register(new Item(new Item.Properties().group(UpliftItemGroup.instance)).setRegistryName("ender_gem"));
		
		event.getRegistry().register(new SwordItem(ModItemTier.ENDER, 8, -2.4F, new Item.Properties().group(UpliftItemGroup.instance)).setRegistryName("ender_sword"));
		event.getRegistry().register(new PickaxeItem(ModItemTier.ENDER, 5, -2.8F, new Item.Properties().group(UpliftItemGroup.instance)).setRegistryName("ender_pickaxe"));
		event.getRegistry().register(new ShovelItem(ModItemTier.ENDER, 5.5f, -3.0F, new Item.Properties().group(UpliftItemGroup.instance)).setRegistryName("ender_shovel"));
		event.getRegistry().register(new AxeItem(ModItemTier.ENDER, 9, -3.0F, new Item.Properties().group(UpliftItemGroup.instance)).setRegistryName("ender_axe"));
		event.getRegistry().register(new HoeItem(ModItemTier.ENDER, -1.0f, new Item.Properties().group(UpliftItemGroup.instance)).setRegistryName("ender_hoe"));
		
		event.getRegistry().register(new ArmorPotionEnder(ModArmorMaterial.ENDER, EquipmentSlotType.HEAD, new Item.Properties().group(UpliftItemGroup.instance)).setRegistryName("ender_helmet"));
		event.getRegistry().register(new ArmorPotionEnder(ModArmorMaterial.ENDER, EquipmentSlotType.CHEST, new Item.Properties().group(UpliftItemGroup.instance)).setRegistryName("ender_chestplate"));
		event.getRegistry().register(new ArmorPotionEnder(ModArmorMaterial.ENDER, EquipmentSlotType.LEGS, new Item.Properties().group(UpliftItemGroup.instance)).setRegistryName("ender_leggings"));
		event.getRegistry().register(new ArmorPotionEnder(ModArmorMaterial.ENDER, EquipmentSlotType.FEET, new Item.Properties().group(UpliftItemGroup.instance)).setRegistryName("ender_boots"));

		//Chrome
		event.getRegistry().register(new Item(new Item.Properties().group(UpliftItemGroup.instance)).setRegistryName("chrome_ingot"));
		event.getRegistry().register(new Item(new Item.Properties().group(UpliftItemGroup.instance)).setRegistryName("chrome_nugget"));

		event.getRegistry().register(new SwordItem(ModItemTier.CHROME, 6, -2.4F, new Item.Properties().group(UpliftItemGroup.instance)).setRegistryName("chrome_sword"));
		event.getRegistry().register(new PickaxeItem(ModItemTier.CHROME, 4, -2.8F, new Item.Properties().group(UpliftItemGroup.instance)).setRegistryName("chrome_pickaxe"));
		event.getRegistry().register(new ShovelItem(ModItemTier.CHROME, 4.5f, -3.0F, new Item.Properties().group(UpliftItemGroup.instance)).setRegistryName("chrome_shovel"));
		event.getRegistry().register(new AxeItem(ModItemTier.CHROME, 9, -3.1F, new Item.Properties().group(UpliftItemGroup.instance)).setRegistryName("chrome_axe"));
		event.getRegistry().register(new HoeItem(ModItemTier.CHROME, -1.0f, new Item.Properties().group(UpliftItemGroup.instance)).setRegistryName("chrome_hoe"));
		
		event.getRegistry().register(new ArmorPotionChrome(ModArmorMaterial.CHROME, EquipmentSlotType.HEAD, new Item.Properties().group(UpliftItemGroup.instance)).setRegistryName("chrome_helmet"));
		event.getRegistry().register(new ArmorPotionChrome(ModArmorMaterial.CHROME, EquipmentSlotType.CHEST, new Item.Properties().group(UpliftItemGroup.instance)).setRegistryName("chrome_chestplate"));
		event.getRegistry().register(new ArmorPotionChrome(ModArmorMaterial.CHROME, EquipmentSlotType.LEGS, new Item.Properties().group(UpliftItemGroup.instance)).setRegistryName("chrome_leggings"));
		event.getRegistry().register(new ArmorPotionChrome(ModArmorMaterial.CHROME, EquipmentSlotType.FEET, new Item.Properties().group(UpliftItemGroup.instance)).setRegistryName("chrome_boots"));
		
		//Opal
		event.getRegistry().register(new Item(new Item.Properties().group(UpliftItemGroup.instance)).setRegistryName("opal"));
		
		//Rose Gold
		event.getRegistry().register(new Item(new Item.Properties().group(UpliftItemGroup.instance)).setRegistryName("rose_gold_ingot"));
		event.getRegistry().register(new Item(new Item.Properties().group(UpliftItemGroup.instance)).setRegistryName("rose_gold_nugget"));
		event.getRegistry().register(new RoseGoldWeapon(Effects.SPEED, ModItemTier.ROSE_GOLD, 12, -2.4F, new Item.Properties().group(UpliftItemGroup.instance)).setRegistryName("rose_gold_sword"));
		event.getRegistry().register(new RoseGoldWeapon(Effects.INVISIBILITY, ModItemTier.ROSE_GOLD, 12, -3.0F, new Item.Properties().group(UpliftItemGroup.instance)).setRegistryName("rose_gold_axe"));
		event.getRegistry().register(new Item(new Item.Properties().group(UpliftItemGroup.instance).food(new Food.Builder().hunger(12).setAlwaysEdible().saturation(10.0F).effect(new EffectInstance(Effects.ABSORPTION, 4800), 1.0f).effect(new EffectInstance(Effects.REGENERATION, 400, 2), 1.0f).effect(new EffectInstance(Effects.FIRE_RESISTANCE, 2400), 1.0f).effect(new EffectInstance(Effects.RESISTANCE, 2400), 1.0f).build())).setRegistryName("rose_golden_apple"));
		
		//Gem
		event.getRegistry().register(new Item(new Item.Properties().group(UpliftItemGroup.instance)).setRegistryName("gem_dust"));
		
		//Mine Bomb
		event.getRegistry().register(new MineBomb(new Item.Properties().group(UpliftItemGroup.instance).maxStackSize(4)).setRegistryName("mine_bomb"));
		
		//SpawnEgg
		event.getRegistry().register(new ModSpawnEggItem(ModEntityTypes.STONE_ENTITY, 11447982, 8092539, new Item.Properties().group(UpliftItemGroup.instance).maxStackSize(16)).setRegistryName("stone_spawn_egg"));
	
		//Stone Meal
		event.getRegistry().register(new Item(new Item.Properties().group(UpliftItemGroup.instance)).setRegistryName("stone_meal"));
		event.getRegistry().register(new StoneSoup(new Item.Properties().maxStackSize(1).group(UpliftItemGroup.instance).food(new Food.Builder().hunger(6).saturation(0.8F).build())).setRegistryName("stone_soup"));
	}
	
	public enum ModItemTier implements IItemTier
	{
		
		//int harvestLevel, int maxUses, float efficiency, float attackDamage, int enchantability
		RUBY(1,200,5.0F,-1,20,() -> {
			return Ingredient.fromItems(ItemInit.ruby);
		}),
		SAPPHIRE(2,750,6.0f,-1,10,() -> {
			return Ingredient.fromItems(ItemInit.sapphire);
		}),
		CHROME(2,700,6.0f,-1,12,() -> {
			return Ingredient.fromItems(ItemInit.chrome_ingot);
		}),
		TOURMALINE(2,600,7.0f,-1,15,() -> {
			return Ingredient.fromItems(ItemInit.tourmaline);
		}),
		SHADOWGLASS(3,1800,7.0f,-1,10,() -> {
			return Ingredient.fromItems(ItemInit.shadowglass);
		}),
		BURNING_DIAMOND(4,1650,9.0f,-1,15,() -> {
			return Ingredient.fromItems(ItemInit.burning_diamond);
		}),
		PLATINUM(4,2150,10.0f,-1,10,() -> {
			return Ingredient.fromItems(ItemInit.platinum_ingot);
		}),
		URANIUM(3,1100,8.0f,-1,15,() -> {
			return Ingredient.fromItems(ItemInit.uranium_ingot);
		}),
		ROSE_GOLD(4,100,10.0f,-1,0,() -> {
			return Ingredient.fromItems(ItemInit.rose_gold_ingot);
		}),
		ENDER(3,2450,9.0f,-1,20,() -> {
			return Ingredient.fromItems(ItemInit.ender_gem);
		});
		
		private final int harvestLevel;
		private final int maxUses;
		private final float efficiency;
		private final float attackDamage;
		private final int enchantability;
		private final LazyValue<Ingredient> repairMaterial;
		
		private ModItemTier(int harvestLevel, int maxUses, float efficiency, float attackDamage, int enchantability, Supplier<Ingredient> repairMaterial)
		{
			this.harvestLevel = harvestLevel;
			this.maxUses = maxUses;
			this.efficiency = efficiency;
			this.attackDamage = attackDamage;
			this.enchantability = enchantability;
			this.repairMaterial = new LazyValue<>(repairMaterial);
		}

		@Override
		public int getMaxUses() 
		{
			return this.maxUses;
		}

		@Override
		public float getEfficiency() 
		{
			return this.efficiency;
		}

		@Override
		public float getAttackDamage() 
		{
			return this.attackDamage;
		}

		@Override
		public int getHarvestLevel() 
		{
			return this.harvestLevel;
		}

		@Override
		public int getEnchantability() 
		{
			return this.enchantability;
		}

		@Override
		public Ingredient getRepairMaterial() 
		{
			return this.repairMaterial.getValue();
		}
	}
	
	public enum ModArmorMaterial implements IArmorMaterial
	{
		//Boots, Leggings, Chestplate, Helmet
		RUBY(Uplift.MOD_ID + ":ruby", 5, new int[] {2, 4, 5, 2}, 20, "item.armor.equip_iron", 0.0f, () -> {
			return Ingredient.fromItems(ItemInit.ruby);
		}),
		SAPPHIRE(Uplift.MOD_ID + ":sapphire", 9, new int[] {2, 5, 6, 2}, 10, "item.armor.equip_iron", 1.0f, () -> {
			return Ingredient.fromItems(ItemInit.sapphire);
		}),
		CHROME(Uplift.MOD_ID + ":chrome", 8, new int[] {2, 5, 6, 2}, 12, "item.armor.equip_iron", 1.0f, () -> {
			return Ingredient.fromItems(ItemInit.chrome_ingot);
		}),
		TOURMALINE(Uplift.MOD_ID + ":tourmaline", 6, new int[] {3, 5, 6, 3}, 15, "item.armor.equip_iron", 0.0f, () -> {
			return Ingredient.fromItems(ItemInit.tourmaline);
		}),
		SHADOWGLASS(Uplift.MOD_ID + ":shadowglass", 19, new int[] {3, 5, 5, 3}, 10, "item.armor.equip_iron", 2.0f, () -> {
			return Ingredient.fromItems(ItemInit.shadowglass);
		}),
		BURNING_DIAMOND(Uplift.MOD_ID + ":burning_diamond", 17, new int[] {4, 7, 9, 4}, 15, "item.armor.equip_iron", 2.0f, () -> {
			return Ingredient.fromItems(ItemInit.burning_diamond);
		}),
		PLATINUM(Uplift.MOD_ID + ":platinum", 22, new int[] {5, 8, 9, 5}, 10, "item.armor.equip_iron", 3.0f, () -> {
			return Ingredient.fromItems(ItemInit.platinum_ingot);
		}),
		URANIUM(Uplift.MOD_ID + ":uranium", 12, new int[] {3, 6, 8, 3}, 15, "item.armor.equip_iron", 1.0f, () -> {
			return Ingredient.fromItems(ItemInit.uranium_ingot);
		}),
		BLOODSTONE(Uplift.MOD_ID + ":bloodstone", 3, new int[] {6, 6, 6, 6}, 30, "item.armor.equip_iron", 4.0f, () -> {
			return Ingredient.fromItems(ItemInit.bloodstone);
		}),
		ENDER(Uplift.MOD_ID + ":ender", 25, new int[] {3, 6, 8, 3}, 20, "item.armor.equip_iron", 2.0f, () -> {
			return Ingredient.fromItems(ItemInit.ender_gem);
		});
		
		//Multiply MAX_DAMAGE_ARRAY by damageFactor
		private static final int[] MAX_DAMAGE_ARRAY = new int [] {27, 30, 35, 25};
		private final String name;
		private final int maxDamageFactor;
		private final int[] damageReductionAmountArray;
		private final int enchantability;
		private final String equipSound;
		private final float toughness;
		private final LazyValue<Ingredient> repairMaterial;
		
		private ModArmorMaterial(String nameIn, int maxDamageFactorIn, int[] damageReductionAmountIn, int enchantabilityIn, String equipSoundIn, float toughnessIn, Supplier<Ingredient> repairMaterialIn)
		{
			this.name = nameIn;
			this.maxDamageFactor = maxDamageFactorIn;
			this.damageReductionAmountArray = damageReductionAmountIn;
			this.enchantability = enchantabilityIn;
			this.equipSound = equipSoundIn;
			this.toughness = toughnessIn;
			this.repairMaterial = new LazyValue<>(repairMaterialIn);
		}

		@Override
		public int getDurability(EquipmentSlotType slotIn) 
		{
			return MAX_DAMAGE_ARRAY[slotIn.getIndex()] * this.maxDamageFactor;
		}

		@Override
		public int getDamageReductionAmount(EquipmentSlotType slotIn) 
		{
			return this.damageReductionAmountArray[slotIn.getIndex()];
		}

		@Override
		public int getEnchantability() 
		{
			return this.enchantability;
		}

		@Override
		public SoundEvent getSoundEvent() 
		{
			return new SoundEvent(new ResourceLocation(equipSound));
		}

		@OnlyIn(Dist.CLIENT)
		@Override
		public String getName() 
		{
			return this.name;
		}

		@Override
		public float getToughness() 
		{
			return this.toughness;
		}

		@Override
		public Ingredient getRepairMaterial() 
		{
			return this.repairMaterial.getValue();
		}
		
 	}
	
}
