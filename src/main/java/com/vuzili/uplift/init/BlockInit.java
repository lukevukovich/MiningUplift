package com.vuzili.uplift.init;

import com.vuzili.uplift.Uplift;
import com.vuzili.uplift.Uplift.UpliftItemGroup;
import com.vuzili.uplift.objects.blocks.BlockXp;
import com.vuzili.uplift.objects.blocks.BurningDiamondBlockFuel;
import com.vuzili.uplift.objects.blocks.BurningDiamondFlammable;
import com.vuzili.uplift.objects.blocks.CavePortal;
import com.vuzili.uplift.objects.blocks.GemCrusher;
import com.vuzili.uplift.objects.blocks.IgniterFire;
import com.vuzili.uplift.objects.blocks.SmelterFurnaceBlock;
import com.vuzili.uplift.objects.blocks.TasmaniteBlockFuel;
import com.vuzili.uplift.objects.blocks.Torch;
import com.vuzili.uplift.objects.blocks.UnlitSmelterFurnaceBlock;
import com.vuzili.uplift.objects.blocks.WallTorch;
import com.vuzili.uplift.objects.items.TorchItem;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(Uplift.MOD_ID)
@EventBusSubscriber(modid = Uplift.MOD_ID, bus = Bus.MOD)
public class BlockInit 
{
	//Ruby
	public static final Block ruby_ore = null;
	public static final Block ruby_block = null;
	public static final Block ruby_torch = null;
	public static final Block ruby_wall_torch = null;

	//Burning Diamond
	public static final Block burning_diamond_block = null;
	public static final Block burning_diamond_ore = null;
	public static final Block burning_diamond_torch = null;
	public static final Block burning_diamond_wall_torch = null;

	//Sapphire
	public static final Block sapphire_ore = null;
	public static final Block sapphire_block = null;
	public static final Block sapphire_torch = null;
	public static final Block sapphire_wall_torch = null;
	
	//Amethyst
	public static final Block amethyst_ore = null;
	public static final Block amethyst_block = null;
	public static final Block amethyst_torch = null;
	public static final Block amethyst_wall_torch = null;
	
	//Obsidian
	public static final Block obsidian_block = null;
	public static final Block obsidian_torch = null;
	public static final Block obsidian_wall_torch = null;
	
	//Smelter
	public static final Block smelter = null;
	public static final Block unlit_smelter = null;

	//Platinum
	public static final Block platinum_ore = null;
	public static final Block platinum_block = null;
	
	//Crusher
	public static final Block gem_crusher = null;
	
	//Uranium
	public static final Block uranium_ore = null;
	public static final Block uranium_block = null;
	
	//Tasmanite
	public static final Block tasmanite_ore = null;
	public static final Block tasmanite_block = null;
	public static final Block tasmanite_torch = null;
	public static final Block tasmanite_wall_torch = null;
	
	//Bloodstone
	public static final Block bloodstone_ore = null;
	public static final Block bloodstone_block = null;
	
	//Ender
	public static final Block ender_ore = null;
	public static final Block ender_block = null;
	public static final Block ender_torch = null;
	public static final Block ender_wall_torch = null;

	//Chrome
	public static final Block chrome_ore = null;
	public static final Block chrome_block = null;

	//Opal
	public static final Block opal_ore = null;
	public static final Block opal_block = null;
	public static final Block opal_torch = null;
	public static final Block opal_wall_torch = null;
	
	//Rose Gold
	public static final Block rose_gold_ore = null;
	public static final Block rose_gold_block = null;
	
	//Gem
	public static final Block gemstone = null;
	
	//Fire
	public static final Block igniter_fire = null;
	
	//Portal
	public static final Block cave_portal = null;


	@SubscribeEvent
	public static void registerBlocks(final RegistryEvent.Register<Block> event)
	{
		//Ruby
		event.getRegistry().register(new BlockXp(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0f, 3.0f).sound(SoundType.STONE).harvestLevel(1).harvestTool(ToolType.PICKAXE)).setRegistryName("ruby_ore"));
		event.getRegistry().register(new Block(Block.Properties.create(Material.IRON).hardnessAndResistance(5.0f, 6.0f).sound(SoundType.METAL).harvestLevel(2).harvestTool(ToolType.PICKAXE)).setRegistryName("ruby_block"));
		event.getRegistry().register(new Torch(Block.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement().hardnessAndResistance(0.0f).lightValue(14).sound(SoundType.WOOD)).setRegistryName("ruby_torch"));
		event.getRegistry().register(new WallTorch(Block.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement().hardnessAndResistance(0.0f).lightValue(14).sound(SoundType.WOOD)).setRegistryName("ruby_wall_torch"));

		//Burning Diamond
		event.getRegistry().register(new BurningDiamondFlammable(Block.Properties.create(Material.IRON).hardnessAndResistance(5.0f, 6.0f).sound(SoundType.METAL).harvestLevel(2).harvestTool(ToolType.PICKAXE).lightValue(10)).setRegistryName("burning_diamond_block"));
		event.getRegistry().register(new BlockXp(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0f, 3.0f).sound(SoundType.STONE).harvestLevel(3).harvestTool(ToolType.PICKAXE).lightValue(5)).setRegistryName("burning_diamond_ore"));
		event.getRegistry().register(new Torch(Block.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement().hardnessAndResistance(0.0f).lightValue(14).sound(SoundType.WOOD)).setRegistryName("burning_diamond_torch"));		
		event.getRegistry().register(new WallTorch(Block.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement().hardnessAndResistance(0.0f).lightValue(14).sound(SoundType.WOOD)).setRegistryName("burning_diamond_wall_torch"));
		
		//Sapphire
		event.getRegistry().register(new BlockXp(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0f, 3.0f).sound(SoundType.STONE).harvestLevel(2).harvestTool(ToolType.PICKAXE)).setRegistryName("sapphire_ore"));
		event.getRegistry().register(new Block(Block.Properties.create(Material.IRON).hardnessAndResistance(5.0f, 6.0f).sound(SoundType.METAL).harvestLevel(2).harvestTool(ToolType.PICKAXE)).setRegistryName("sapphire_block"));
		event.getRegistry().register(new Torch(Block.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement().hardnessAndResistance(0.0f).lightValue(14).sound(SoundType.WOOD)).setRegistryName("sapphire_torch"));
		event.getRegistry().register(new WallTorch(Block.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement().hardnessAndResistance(0.0f).lightValue(14).sound(SoundType.WOOD)).setRegistryName("sapphire_wall_torch"));
		
		//Amethyst
		event.getRegistry().register(new BlockXp(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0f, 3.0f).sound(SoundType.STONE).harvestLevel(2).harvestTool(ToolType.PICKAXE)).setRegistryName("amethyst_ore"));
		event.getRegistry().register(new Block(Block.Properties.create(Material.IRON).hardnessAndResistance(5.0f, 6.0f).sound(SoundType.METAL).harvestLevel(2).harvestTool(ToolType.PICKAXE)).setRegistryName("amethyst_block"));
		event.getRegistry().register(new Torch(Block.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement().hardnessAndResistance(0.0f).lightValue(14).sound(SoundType.WOOD)).setRegistryName("amethyst_torch"));
		event.getRegistry().register(new WallTorch(Block.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement().hardnessAndResistance(0.0f).lightValue(14).sound(SoundType.WOOD)).setRegistryName("amethyst_wall_torch"));
		
		//Obsidian
		event.getRegistry().register(new Block(Block.Properties.create(Material.IRON).hardnessAndResistance(50f, 1200f).sound(SoundType.METAL).harvestLevel(2).harvestTool(ToolType.PICKAXE)).setRegistryName("obsidian_block"));
		event.getRegistry().register(new Torch(Block.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement().hardnessAndResistance(0.0f).lightValue(14).sound(SoundType.WOOD)).setRegistryName("obsidian_torch"));
		event.getRegistry().register(new WallTorch(Block.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement().hardnessAndResistance(0.0f).lightValue(14).sound(SoundType.WOOD)).setRegistryName("obsidian_wall_torch"));

		//Smelter
		event.getRegistry().register(new SmelterFurnaceBlock(Block.Properties.from(Blocks.FURNACE).lightValue(12).hardnessAndResistance(50f, 1200f)).setRegistryName("smelter"));
		event.getRegistry().register(new UnlitSmelterFurnaceBlock(Block.Properties.from(Blocks.FURNACE).lightValue(0)).setRegistryName("unlit_smelter"));
		
		//Platinum
		event.getRegistry().register(new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0f, 3.0f).sound(SoundType.STONE).harvestLevel(4).harvestTool(ToolType.PICKAXE)).setRegistryName("platinum_ore"));
		event.getRegistry().register(new Block(Block.Properties.create(Material.IRON).hardnessAndResistance(5.0f, 6.0f).sound(SoundType.METAL).harvestLevel(2).harvestTool(ToolType.PICKAXE)).setRegistryName("platinum_block"));
		
		//Crusher
		event.getRegistry().register(new GemCrusher(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.5f, 3.0f).sound(SoundType.STONE).harvestLevel(1).harvestTool(ToolType.PICKAXE)).setRegistryName("gem_crusher"));
		
		//Uranium
		event.getRegistry().register(new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0f, 3.0f).sound(SoundType.STONE).harvestLevel(3).harvestTool(ToolType.PICKAXE)).setRegistryName("uranium_ore"));
		event.getRegistry().register(new Block(Block.Properties.create(Material.IRON).hardnessAndResistance(5.0f, 6.0f).sound(SoundType.METAL).harvestLevel(2).harvestTool(ToolType.PICKAXE)).setRegistryName("uranium_block"));
		
		//Tasmanite
		event.getRegistry().register(new BlockXp(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0f, 3.0f).sound(SoundType.STONE).harvestLevel(2).harvestTool(ToolType.PICKAXE)).setRegistryName("tasmanite_ore"));
		event.getRegistry().register(new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(5.0f, 6.0f).sound(SoundType.STONE).harvestLevel(2).harvestTool(ToolType.PICKAXE).lightValue(15)).setRegistryName("tasmanite_block"));
		event.getRegistry().register(new Torch(Block.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement().hardnessAndResistance(0.0f).lightValue(14).sound(SoundType.WOOD)).setRegistryName("tasmanite_torch"));
		event.getRegistry().register(new WallTorch(Block.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement().hardnessAndResistance(0.0f).lightValue(14).sound(SoundType.WOOD)).setRegistryName("tasmanite_wall_torch"));

		//Bloodstone
		event.getRegistry().register(new BlockXp(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0f, 3.0f).sound(SoundType.STONE).harvestLevel(2).harvestTool(ToolType.PICKAXE)).setRegistryName("bloodstone_ore"));
		event.getRegistry().register(new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(5.0f, 6.0f).sound(SoundType.STONE).harvestLevel(2).harvestTool(ToolType.PICKAXE)).setRegistryName("bloodstone_block"));
		
		//Ender
		event.getRegistry().register(new BlockXp(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0f, 3.0f).sound(SoundType.STONE).harvestLevel(3).harvestTool(ToolType.PICKAXE)).setRegistryName("ender_ore"));
		event.getRegistry().register(new Block(Block.Properties.create(Material.IRON).hardnessAndResistance(5.0f, 6.0f).sound(SoundType.METAL).harvestLevel(2).harvestTool(ToolType.PICKAXE)).setRegistryName("ender_block"));
		event.getRegistry().register(new Torch(Block.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement().hardnessAndResistance(0.0f).lightValue(14).sound(SoundType.WOOD)).setRegistryName("ender_torch"));
		event.getRegistry().register(new WallTorch(Block.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement().hardnessAndResistance(0.0f).lightValue(14).sound(SoundType.WOOD)).setRegistryName("ender_wall_torch"));

		//Chrome
		event.getRegistry().register(new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0f, 3.0f).sound(SoundType.STONE).harvestLevel(2).harvestTool(ToolType.PICKAXE)).setRegistryName("chrome_ore"));
		event.getRegistry().register(new Block(Block.Properties.create(Material.IRON).hardnessAndResistance(5.0f, 6.0f).sound(SoundType.METAL).harvestLevel(2).lightValue(10).harvestTool(ToolType.PICKAXE)).setRegistryName("chrome_block"));
		
		//Opal
		event.getRegistry().register(new BlockXp(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0f, 3.0f).sound(SoundType.STONE).harvestLevel(3).harvestTool(ToolType.PICKAXE)).setRegistryName("opal_ore"));
		event.getRegistry().register(new Block(Block.Properties.create(Material.IRON).hardnessAndResistance(5.0f, 6.0f).sound(SoundType.METAL).harvestLevel(2).harvestTool(ToolType.PICKAXE)).setRegistryName("opal_block"));
		event.getRegistry().register(new Torch(Block.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement().hardnessAndResistance(0.0f).lightValue(14).sound(SoundType.WOOD)).setRegistryName("opal_torch"));
		event.getRegistry().register(new WallTorch(Block.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement().hardnessAndResistance(0.0f).lightValue(14).sound(SoundType.WOOD)).setRegistryName("opal_wall_torch"));

		//Rose Gold
		event.getRegistry().register(new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0f, 3.0f).sound(SoundType.STONE).harvestLevel(2).harvestTool(ToolType.PICKAXE)).setRegistryName("rose_gold_ore"));
		event.getRegistry().register(new Block(Block.Properties.create(Material.IRON).hardnessAndResistance(5.0f, 6.0f).sound(SoundType.METAL).harvestLevel(2).harvestTool(ToolType.PICKAXE)).setRegistryName("rose_gold_block"));

		//Gem
		event.getRegistry().register(new Block(Block.Properties.from(Blocks.GLOWSTONE)).setRegistryName("gemstone"));
		
		//Fire
		event.getRegistry().register(new IgniterFire(Block.Properties.create(Material.FIRE, MaterialColor.TNT).doesNotBlockMovement().tickRandomly().hardnessAndResistance(0.0f).lightValue(15).noDrops().sound(SoundTypeInit.NONE)).setRegistryName("igniter_fire"));
		
		//Teleporter (Portal-like)
		event.getRegistry().register(new CavePortal(Block.Properties.create(Material.PORTAL)
			.doesNotBlockMovement()
			.hardnessAndResistance(-1.0F)
			.noDrops())
			.setRegistryName("cave_portal"));

	}
	
	@SubscribeEvent
	public static void registerBlockItems(final RegistryEvent.Register<Item> event)
	{
		//Ruby
		event.getRegistry().register(new BlockItem(ruby_ore, new Item.Properties().group(UpliftItemGroup.instance)).setRegistryName("ruby_ore"));
		event.getRegistry().register(new BlockItem(ruby_block, new Item.Properties().group(UpliftItemGroup.instance)).setRegistryName("ruby_block"));
		event.getRegistry().register(new TorchItem(ruby_torch, ruby_wall_torch, new Item.Properties().group(UpliftItemGroup.instance)).setRegistryName("ruby_torch"));

		//Burning Diamond
		event.getRegistry().register(new BurningDiamondBlockFuel(burning_diamond_block, new Item.Properties().group(UpliftItemGroup.instance)).setRegistryName("burning_diamond_block"));
		event.getRegistry().register(new BlockItem(burning_diamond_ore, new Item.Properties().group(UpliftItemGroup.instance)).setRegistryName("burning_diamond_ore"));
		event.getRegistry().register(new TorchItem(burning_diamond_torch, burning_diamond_wall_torch, new Item.Properties().group(UpliftItemGroup.instance)).setRegistryName("burning_diamond_torch"));
		
		//Sapphire
		event.getRegistry().register(new BlockItem(sapphire_ore, new Item.Properties().group(UpliftItemGroup.instance)).setRegistryName("sapphire_ore"));
		event.getRegistry().register(new BlockItem(sapphire_block, new Item.Properties().group(UpliftItemGroup.instance)).setRegistryName("sapphire_block"));
		event.getRegistry().register(new TorchItem(sapphire_torch, sapphire_wall_torch, new Item.Properties().group(UpliftItemGroup.instance)).setRegistryName("sapphire_torch"));
		
		//Amethyst
		event.getRegistry().register(new BlockItem(amethyst_ore, new Item.Properties().group(UpliftItemGroup.instance)).setRegistryName("amethyst_ore"));
		event.getRegistry().register(new BlockItem(amethyst_block, new Item.Properties().group(UpliftItemGroup.instance)).setRegistryName("amethyst_block"));
		event.getRegistry().register(new TorchItem(amethyst_torch, amethyst_wall_torch, new Item.Properties().group(UpliftItemGroup.instance)).setRegistryName("amethyst_torch"));
		
		//Obsidian
		event.getRegistry().register(new BlockItem(obsidian_block, new Item.Properties().group(UpliftItemGroup.instance)).setRegistryName("obsidian_block"));
		event.getRegistry().register(new TorchItem(obsidian_torch, obsidian_wall_torch, new Item.Properties().group(UpliftItemGroup.instance)).setRegistryName("obsidian_torch"));
		
		//Smelter
		event.getRegistry().register(new BlockItem(smelter, new Item.Properties()).setRegistryName("smelter"));
		event.getRegistry().register(new BlockItem(unlit_smelter, new Item.Properties().group(UpliftItemGroup.instance)).setRegistryName("unlit_smelter"));

		//Platinum
		event.getRegistry().register(new BlockItem(platinum_ore, new Item.Properties().group(UpliftItemGroup.instance)).setRegistryName("platinum_ore"));
		event.getRegistry().register(new BlockItem(platinum_block, new Item.Properties().group(UpliftItemGroup.instance)).setRegistryName("platinum_block"));
		
		//Crusher
		event.getRegistry().register(new BlockItem(gem_crusher, new Item.Properties().group(UpliftItemGroup.instance)).setRegistryName("gem_crusher"));
		
		//Uranium
		event.getRegistry().register(new BlockItem(uranium_ore, new Item.Properties().group(UpliftItemGroup.instance)).setRegistryName("uranium_ore"));
		event.getRegistry().register(new BlockItem(uranium_block, new Item.Properties().group(UpliftItemGroup.instance)).setRegistryName("uranium_block"));
		
		//Tasmanite
		event.getRegistry().register(new BlockItem(tasmanite_ore, new Item.Properties().group(UpliftItemGroup.instance)).setRegistryName("tasmanite_ore"));
		event.getRegistry().register(new TasmaniteBlockFuel(tasmanite_block, new Item.Properties().group(UpliftItemGroup.instance)).setRegistryName("tasmanite_block"));
		event.getRegistry().register(new TorchItem(tasmanite_torch, tasmanite_wall_torch, new Item.Properties().group(UpliftItemGroup.instance)).setRegistryName("tasmanite_torch"));
		
		//Bloodstone
		event.getRegistry().register(new BlockItem(bloodstone_ore, new Item.Properties().group(UpliftItemGroup.instance)).setRegistryName("bloodstone_ore"));
		event.getRegistry().register(new TasmaniteBlockFuel(bloodstone_block, new Item.Properties().group(UpliftItemGroup.instance)).setRegistryName("bloodstone_block"));
		
		//Ender
		event.getRegistry().register(new BlockItem(ender_ore, new Item.Properties().group(UpliftItemGroup.instance)).setRegistryName("ender_ore"));
		event.getRegistry().register(new TasmaniteBlockFuel(ender_block, new Item.Properties().group(UpliftItemGroup.instance)).setRegistryName("ender_block"));
		event.getRegistry().register(new TorchItem(ender_torch, ender_wall_torch, new Item.Properties().group(UpliftItemGroup.instance)).setRegistryName("ender_torch"));

		//Chrome
		event.getRegistry().register(new BlockItem(chrome_ore, new Item.Properties().group(UpliftItemGroup.instance)).setRegistryName("chrome_ore"));
		event.getRegistry().register(new BlockItem(chrome_block, new Item.Properties().group(UpliftItemGroup.instance)).setRegistryName("chrome_block"));

		//Opal
		event.getRegistry().register(new BlockItem(opal_ore, new Item.Properties().group(UpliftItemGroup.instance)).setRegistryName("opal_ore"));
		event.getRegistry().register(new BlockItem(opal_block, new Item.Properties().group(UpliftItemGroup.instance)).setRegistryName("opal_block"));
		event.getRegistry().register(new TorchItem(opal_torch, opal_wall_torch, new Item.Properties().group(UpliftItemGroup.instance)).setRegistryName("opal_torch"));
		
		//Rose Gold
		event.getRegistry().register(new BlockItem(rose_gold_ore, new Item.Properties().group(UpliftItemGroup.instance)).setRegistryName("rose_gold_ore"));
		event.getRegistry().register(new BlockItem(rose_gold_block, new Item.Properties().group(UpliftItemGroup.instance)).setRegistryName("rose_gold_block"));
		
		//Gem
		event.getRegistry().register(new BlockItem(gemstone, new Item.Properties().group(UpliftItemGroup.instance)).setRegistryName("gemstone"));
		
		//Fire
		event.getRegistry().register(new BlockItem(igniter_fire, new Item.Properties()).setRegistryName("igniter_fire"));
		
		//Portal
		event.getRegistry().register(new BlockItem(cave_portal, new Item.Properties()).setRegistryName("cave_portal"));
		
	}

}
