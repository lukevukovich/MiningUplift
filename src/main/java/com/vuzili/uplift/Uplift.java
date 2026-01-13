package com.vuzili.uplift;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.vuzili.uplift.init.BiomeInit;
import com.vuzili.uplift.init.BlockInit;
import com.vuzili.uplift.init.DimensionInit;
import com.vuzili.uplift.init.ItemInit;
import com.vuzili.uplift.init.ModContainerTypes;
import com.vuzili.uplift.init.ModEntityTypes;
import com.vuzili.uplift.init.ModTileEntityTypes;
import com.vuzili.uplift.init.RecipeSerializerInit;
import com.vuzili.uplift.objects.items.ModSpawnEggItem;
import com.vuzili.uplift.world.gen.ModOreGen;
import com.vuzili.uplift.network.NetworkHandler;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.entity.EntityType;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("uplift")
@Mod.EventBusSubscriber(modid = Uplift.MOD_ID, bus = Bus.MOD)
public class Uplift
{
    // Directly reference a log4j logger.
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "uplift";
    public static Uplift instance;
    public static final ResourceLocation UPLIFT_DIM_TYPE = new ResourceLocation(MOD_ID, "the_cave");
    
    public Uplift()
    {
    	final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::setup);
        modEventBus.addListener(this::doClientStuff);
        
        RecipeSerializerInit.RECIPE_SERIALIZERS.register(modEventBus);
        
        ModEntityTypes.ENTITY_TYPES.register(modEventBus);
        
        ModTileEntityTypes.TILE_ENTITY_TYPES.register(modEventBus);
        
        ModContainerTypes.CONTAINER_TYPES.register(modEventBus);
        
        BiomeInit.BIOMES.register(modEventBus);
        
        DimensionInit.MOD_DIMENSIONS.register(modEventBus);
        
        instance = this;
        MinecraftForge.EVENT_BUS.register(this);
        
    }
    
    @SubscribeEvent
    public static void onRegisterBiomes(final RegistryEvent.Register<Biome> event)
    {
    	BiomeInit.registerBiomes();
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        // Initialize networking channel and register packets
        NetworkHandler.register();
    }

    private void doClientStuff(final FMLClientSetupEvent event) 
    {
    	RenderTypeLookup.setRenderLayer(BlockInit.ruby_torch.getBlock(), RenderType.getCutout());
    	RenderTypeLookup.setRenderLayer(BlockInit.burning_diamond_torch.getBlock(), RenderType.getCutout());
    	RenderTypeLookup.setRenderLayer(BlockInit.sapphire_torch.getBlock(), RenderType.getCutout());
    	RenderTypeLookup.setRenderLayer(BlockInit.tourmaline_torch.getBlock(), RenderType.getCutout());
    	RenderTypeLookup.setRenderLayer(BlockInit.opal_torch.getBlock(), RenderType.getCutout());
    	RenderTypeLookup.setRenderLayer(BlockInit.obsidian_torch.getBlock(), RenderType.getCutout());
    	RenderTypeLookup.setRenderLayer(BlockInit.igniter_fire.getBlock(), RenderType.getCutout());
    	RenderTypeLookup.setRenderLayer(BlockInit.tasmanite_torch.getBlock(), RenderType.getCutout());
    	RenderTypeLookup.setRenderLayer(BlockInit.ender_torch.getBlock(), RenderType.getCutout());
    	
		RenderTypeLookup.setRenderLayer(BlockInit.ruby_wall_torch.getBlock(), RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BlockInit.burning_diamond_wall_torch.getBlock(), RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BlockInit.sapphire_wall_torch.getBlock(), RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BlockInit.tourmaline_wall_torch.getBlock(), RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BlockInit.obsidian_wall_torch.getBlock(), RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BlockInit.tasmanite_wall_torch.getBlock(), RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BlockInit.opal_wall_torch.getBlock(), RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BlockInit.ender_wall_torch.getBlock(), RenderType.getCutout());
		
		RenderTypeLookup.setRenderLayer(BlockInit.cave_teleporter.getBlock(), RenderType.getCutout());

    }

    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) 
    {
        
    }
    
    @SubscribeEvent
    public static void loadCompleteEvent(FMLLoadCompleteEvent event)
    {
    	ModOreGen.generateOre();
    }
    
    @SubscribeEvent
    public static void onRegisterEntities(final RegistryEvent.Register<EntityType<?>> event)
    {
    	ModSpawnEggItem.initSpawnEggs();
    }
    
    public static class UpliftItemGroup extends ItemGroup
    {
    	public static final UpliftItemGroup instance = new UpliftItemGroup(ItemGroup.GROUPS.length, "uplifttab");
    	private UpliftItemGroup(int index, String label)
    	{
    		super(index, label);
    	}
    	
    	@Override
    	public ItemStack createIcon()
    	{
    		return new ItemStack(ItemInit.gem_dust);
    	}
    }

}
