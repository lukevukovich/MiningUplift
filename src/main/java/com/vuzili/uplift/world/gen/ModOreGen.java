package com.vuzili.uplift.world.gen;

import com.vuzili.uplift.init.BiomeInit;
import com.vuzili.uplift.init.BlockInit;

import net.minecraft.block.Blocks;
import net.minecraft.block.pattern.BlockMatcher;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.ConfiguredPlacement;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.registries.ForgeRegistries;

public class ModOreGen 
{
	public static void generateOre()
	{
		for(Biome biome : ForgeRegistries.BIOMES)
		{
			if(biome == BiomeInit.ORE_BIOME.get())
			{
				
				ConfiguredPlacement<CountRangeConfig> customConfigGemstone = Placement.COUNT_RANGE.configure(new CountRangeConfig(10, 0, 0, 70));
				biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, BlockInit.gemstone.getDefaultState(), 8)).withPlacement(customConfigGemstone));
				
				ConfiguredPlacement<CountRangeConfig> customConfigRuby = Placement.COUNT_RANGE.configure(new CountRangeConfig(30, 0, 0, 70));
				biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, BlockInit.ruby_ore.getDefaultState(), 10)).withPlacement(customConfigRuby));
			
				ConfiguredPlacement<CountRangeConfig> customConfigSapphire = Placement.COUNT_RANGE.configure(new CountRangeConfig(14, 0, 0, 70));
				biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, BlockInit.sapphire_ore.getDefaultState(), 8)).withPlacement(customConfigSapphire));

				ConfiguredPlacement<CountRangeConfig> customConfigChrome = Placement.COUNT_RANGE.configure(new CountRangeConfig(14, 0, 0, 70));
				biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, BlockInit.chrome_ore.getDefaultState(), 8)).withPlacement(customConfigChrome));
			
				ConfiguredPlacement<CountRangeConfig> customConfigTourmaline = Placement.COUNT_RANGE.configure(new CountRangeConfig(14, 0, 0, 70));
				biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, BlockInit.tourmaline_ore.getDefaultState(), 8)).withPlacement(customConfigTourmaline));
			
				ConfiguredPlacement<CountRangeConfig> customConfigPlatinum = Placement.COUNT_RANGE.configure(new CountRangeConfig(3, 0, 0, 70));
				biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, BlockInit.platinum_ore.getDefaultState(), 4)).withPlacement(customConfigPlatinum));
				
				ConfiguredPlacement<CountRangeConfig> customConfigCoal = Placement.COUNT_RANGE.configure(new CountRangeConfig(50, 0, 0, 70));
				biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, Blocks.COAL_ORE.getDefaultState(), 14)).withPlacement(customConfigCoal));
				
				ConfiguredPlacement<CountRangeConfig> customConfigDiamond = Placement.COUNT_RANGE.configure(new CountRangeConfig(3, 0, 0, 70));
				biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, Blocks.DIAMOND_ORE.getDefaultState(), 8)).withPlacement(customConfigDiamond));

				ConfiguredPlacement<CountRangeConfig> customConfigIron = Placement.COUNT_RANGE.configure(new CountRangeConfig(26, 0, 0, 70));
				biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, Blocks.IRON_ORE.getDefaultState(), 10)).withPlacement(customConfigIron));
				
				ConfiguredPlacement<CountRangeConfig> customConfigGold = Placement.COUNT_RANGE.configure(new CountRangeConfig(12, 0, 0, 70));
				biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, Blocks.GOLD_ORE.getDefaultState(), 8)).withPlacement(customConfigGold));
			
				ConfiguredPlacement<CountRangeConfig> customConfigEmerald = Placement.COUNT_RANGE.configure(new CountRangeConfig(4, 0, 0, 70));
				biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, Blocks.EMERALD_ORE.getDefaultState(), 6)).withPlacement(customConfigEmerald));
				
				ConfiguredPlacement<CountRangeConfig> customConfigLapis = Placement.COUNT_RANGE.configure(new CountRangeConfig(12, 0, 0, 70));
				biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, Blocks.LAPIS_ORE.getDefaultState(), 8)).withPlacement(customConfigLapis));
				
				ConfiguredPlacement<CountRangeConfig> customConfigRedstone = Placement.COUNT_RANGE.configure(new CountRangeConfig(12, 0, 0, 70));
				biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, Blocks.REDSTONE_ORE.getDefaultState(), 10)).withPlacement(customConfigRedstone));
				
				ConfiguredPlacement<CountRangeConfig> customConfigObsidian = Placement.COUNT_RANGE.configure(new CountRangeConfig(12, 0, 0, 70));
				biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, Blocks.OBSIDIAN.getDefaultState(), 10)).withPlacement(customConfigObsidian));
				
				ConfiguredPlacement<CountRangeConfig> customConfigTasmanite = Placement.COUNT_RANGE.configure(new CountRangeConfig(20, 0, 0, 70));
				biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, BlockInit.tasmanite_ore.getDefaultState(), 10)).withPlacement(customConfigTasmanite));
			
				ConfiguredPlacement<CountRangeConfig> customConfigBurningDiamond = Placement.COUNT_RANGE.configure(new CountRangeConfig(2, 0, 0, 70));
				biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, BlockInit.burning_diamond_ore.getDefaultState(), 4)).withPlacement(customConfigBurningDiamond));
				
				ConfiguredPlacement<CountRangeConfig> customConfigOpal = Placement.COUNT_RANGE.configure(new CountRangeConfig(6, 0, 0, 70));
				biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, BlockInit.opal_ore.getDefaultState(), 6)).withPlacement(customConfigOpal));
				
				ConfiguredPlacement<CountRangeConfig> customConfigRoseGold = Placement.COUNT_RANGE.configure(new CountRangeConfig(14, 0, 0, 70));
				biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, BlockInit.rose_gold_ore.getDefaultState(), 6)).withPlacement(customConfigRoseGold));
			
				ConfiguredPlacement<CountRangeConfig> customConfigUranium = Placement.COUNT_RANGE.configure(new CountRangeConfig(6, 0, 0, 70));
				biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, BlockInit.uranium_ore.getDefaultState(), 8)).withPlacement(customConfigUranium));
			}
		
			else if (biome == Biomes.NETHER)
			{
				ConfiguredPlacement<CountRangeConfig> customConfigBloodstone = Placement.COUNT_RANGE.configure(new CountRangeConfig(8, 0, 0, 128));
				biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NETHERRACK, BlockInit.bloodstone_ore.getDefaultState(), 8)).withPlacement(customConfigBloodstone));				
			}
			else if (biome == Biomes.THE_END) {
				ConfiguredPlacement<CountRangeConfig> customConfigEnder = Placement.COUNT_RANGE.configure(new CountRangeConfig(32, 0, 0, 256));
				biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.create("END_STONE", null, new BlockMatcher(Blocks.END_STONE)), BlockInit.ender_ore.getDefaultState(), 12)).withPlacement(customConfigEnder));	
			}
			else {
				
				ConfiguredPlacement<CountRangeConfig> customConfigRuby = Placement.COUNT_RANGE.configure(new CountRangeConfig(22, 0, 0, 64));
				biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, BlockInit.ruby_ore.getDefaultState(), 10)).withPlacement(customConfigRuby));
			
				ConfiguredPlacement<CountRangeConfig> customConfigSapphire = Placement.COUNT_RANGE.configure(new CountRangeConfig(6, 0, 0, 32));
				biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, BlockInit.sapphire_ore.getDefaultState(), 8)).withPlacement(customConfigSapphire));
			
				ConfiguredPlacement<CountRangeConfig> customConfigChrome = Placement.COUNT_RANGE.configure(new CountRangeConfig(6, 0, 0, 32));
				biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, BlockInit.chrome_ore.getDefaultState(), 8)).withPlacement(customConfigChrome));

				ConfiguredPlacement<CountRangeConfig> customConfigTourmaline = Placement.COUNT_RANGE.configure(new CountRangeConfig(6, 0, 0, 32));
				biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, BlockInit.tourmaline_ore.getDefaultState(), 8)).withPlacement(customConfigTourmaline));
			
				ConfiguredPlacement<CountRangeConfig> customConfigPlatinum = Placement.COUNT_RANGE.configure(new CountRangeConfig(1, 0, 0, 8));
				biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, BlockInit.platinum_ore.getDefaultState(), 4)).withPlacement(customConfigPlatinum));
				
				ConfiguredPlacement<CountRangeConfig> customConfigBurningDiamond = Placement.COUNT_RANGE.configure(new CountRangeConfig(1, 0, 0, 12));
				biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, BlockInit.burning_diamond_ore.getDefaultState(), 4)).withPlacement(customConfigBurningDiamond));
				
				ConfiguredPlacement<CountRangeConfig> customConfigTasmanite = Placement.COUNT_RANGE.configure(new CountRangeConfig(12, 0, 0, 128));
				biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, BlockInit.tasmanite_ore.getDefaultState(), 8)).withPlacement(customConfigTasmanite));
				
				ConfiguredPlacement<CountRangeConfig> customConfigOpal = Placement.COUNT_RANGE.configure(new CountRangeConfig(1, 0, 0, 24));
				biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, BlockInit.opal_ore.getDefaultState(), 6)).withPlacement(customConfigOpal));
				
				ConfiguredPlacement<CountRangeConfig> customConfigUranium = Placement.COUNT_RANGE.configure(new CountRangeConfig(4, 46, 0, 256));
				biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, BlockInit.uranium_ore.getDefaultState(), 6)).withPlacement(customConfigUranium));
				
			}
		}
	}
}
