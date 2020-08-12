package net.mrsterner.grandecuisine;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.client.options.KeyBinding;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffectType;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.mrsterner.grandecuisine.block.BarrelBlock;
import net.mrsterner.grandecuisine.block.HibachiBlock;
import net.mrsterner.grandecuisine.blockentity.BarrelBlockEntity;
import net.mrsterner.grandecuisine.blockentity.HibachiEntity;
import net.mrsterner.grandecuisine.effect.FuseStatusEffect;
import net.mrsterner.grandecuisine.effect.ModEffectRegistry;
import net.mrsterner.grandecuisine.effect.ModPotionRegistry;
import net.mrsterner.grandecuisine.effect.ModStatusEffect;
import net.mrsterner.grandecuisine.entity.CrabEntity;
import net.mrsterner.grandecuisine.items.*;

import java.lang.reflect.Field;

import static net.mrsterner.grandecuisine.block.HibachiBlock.POWER;

public class GrandeCuisine implements ModInitializer {

    public static final String MOD_ID = "grandecuisine";

    public static final ItemGroup GRANDE_CUISINE_TAB = FabricItemGroupBuilder.build(
            new Identifier("grandecuisine", "general"),
            () -> new ItemStack(GrandeCuisine.SHIITAKE));
    //ITEMS
    public static final Item GUI = new GuiItem(new Item.Settings().group(GrandeCuisine.GRANDE_CUISINE_TAB));

    public static final DrugItem DRUG =  new DrugItem(new Item.Settings().group(GrandeCuisine.GRANDE_CUISINE_TAB));
    //public static final PotionItem DRUG =  new PotionItem(new Item.Settings().group(GrandeCuisine.GRANDE_CUISINE_TAB));

    public static final Item CRABCLAW =  new Item(new Item.Settings().group(GrandeCuisine.GRANDE_CUISINE_TAB).food(new FoodComponent.Builder().hunger(1).build()));
    public static final Item SHIITAKE =  new Item(new Item.Settings().group(GrandeCuisine.GRANDE_CUISINE_TAB).food(new FoodComponent.Builder().hunger(1).build()));
    public static final Item GOLDEN_SHIITAKE =  new Item(new Item.Settings().group(GrandeCuisine.GRANDE_CUISINE_TAB).food(new FoodComponent.Builder().hunger(1).build()));
    public static final Item GOLDEN_MINI_SHIITAKE =  new Item(new Item.Settings().group(GrandeCuisine.GRANDE_CUISINE_TAB).food(new FoodComponent.Builder().hunger(1).build()));
    public static final Item MINI_SHIITAKE =  new Item(new Item.Settings().group(GrandeCuisine.GRANDE_CUISINE_TAB).food(new FoodComponent.Builder().hunger(1).build()));
    public static final Item EGG_RAMEN =  new Item(new Item.Settings().group(GrandeCuisine.GRANDE_CUISINE_TAB).food(new FoodComponent.Builder().hunger(1).build()));
    public static final Item SAKE =  new Item(new Item.Settings().group(GrandeCuisine.GRANDE_CUISINE_TAB).food(new FoodComponent.Builder().hunger(1).build()));
    public static final Item SALT =  new Item(new Item.Settings().group(GrandeCuisine.GRANDE_CUISINE_TAB));
    public static final Item SOY_SAUCE =  new Item(new Item.Settings().group(GrandeCuisine.GRANDE_CUISINE_TAB).food(new FoodComponent.Builder().hunger(1).build()));
    public static final Item SOYBEAN =  new Item(new Item.Settings().group(GrandeCuisine.GRANDE_CUISINE_TAB).food(new FoodComponent.Builder().hunger(1).build()));
    //ENTITY
    public static final EntityType<CrabEntity> CRAB = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier("grandecuisine", "crab"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, CrabEntity::new).dimensions(EntityDimensions.fixed(0.75f, 0.75f)).build());

    public static BlockEntityType<BarrelBlockEntity> BARREL_BLOCK_ENTITY;
    public static final HibachiBlock HIBACHI = new HibachiBlock(Block.Settings.of(Material.STONE).lightLevel((state) -> state.get(POWER)));
    public static BlockEntityType<HibachiEntity> HIBACHI_ENTITY;
    public static final BarrelBlock BARREL_BLOCK = new BarrelBlock(FabricBlockSettings.of(Material.METAL).hardness(4.0f));
   //public static BlockEntityType<BarrelBlockEntity> BARREL_BLOCK_ENTITY;
    private static KeyBinding keyBinding;


    public static final Identifier PLAY_PARTICLE_PACKET_ID = new Identifier("example", "particle");

    public static final StatusEffect WILTING = new StatusEffect(StatusEffectType.NEUTRAL, 0x768238) {
        @Override
        public void applyUpdateEffect(LivingEntity entity, int amplifier) {
            if(1==1) {}
        }
        @Override
        public boolean canApplyUpdateEffect(int duration, int amplifier) {
            int tickDelayBetweenHurts = 10 >> amplifier;
            return tickDelayBetweenHurts <= 0 || duration % tickDelayBetweenHurts == 0;
        }
    };

    @Override
    public void onInitialize() {
        ModEffectRegistry.registerAll();
        ModPotionRegistry.registerAll();
        //ITEMS
        Registry.register(Registry.ITEM, new Identifier("grandecuisine", "gui"), GUI);

        Registry.register(Registry.ITEM, new Identifier("grandecuisine", "drug"), DRUG);


        Registry.register(Registry.ITEM, new Identifier("grandecuisine", "crabclaw"), CRABCLAW);
        Registry.register(Registry.ITEM, new Identifier("grandecuisine", "shiitake"), SHIITAKE);
        Registry.register(Registry.ITEM, new Identifier("grandecuisine", "golden_shiitake"), GOLDEN_SHIITAKE);
        Registry.register(Registry.ITEM, new Identifier("grandecuisine", "golden_mini_shiitake"), GOLDEN_MINI_SHIITAKE);
        Registry.register(Registry.ITEM, new Identifier("grandecuisine", "mini_shiitake"), MINI_SHIITAKE);
        Registry.register(Registry.ITEM, new Identifier("grandecuisine", "mini_egg_ramen"), EGG_RAMEN);
        Registry.register(Registry.ITEM, new Identifier("grandecuisine", "sake"), SAKE);
        Registry.register(Registry.ITEM, new Identifier("grandecuisine", "salt"), SALT);
        Registry.register(Registry.ITEM, new Identifier("grandecuisine", "soy_sauce"), SOY_SAUCE);
        Registry.register(Registry.ITEM, new Identifier("grandecuisine", "soybean"), SOYBEAN);

        Registry.register(Registry.ITEM, new Identifier("grandecuisine", "hibachi"), new BlockItem(HIBACHI, new Item.Settings().group(GrandeCuisine.GRANDE_CUISINE_TAB)));
        Registry.register(Registry.ITEM, new Identifier("grandecuisine", "barrelblock"), new BlockItem(BARREL_BLOCK, new Item.Settings().group(GrandeCuisine.GRANDE_CUISINE_TAB)));
        Registry.register(Registry.ITEM, new Identifier("grandecuisine", "spawn_crab"), new SpawnEggItem(CRAB, 2956072, 1445648, new Item.Settings().group(ItemGroup.MISC)));
        //BLOCKS
        Registry.register(Registry.BLOCK, new Identifier("grandecuisine", "hibachi"), HIBACHI);
        Registry.register(Registry.BLOCK, new Identifier("grandecuisine", "barrelblock"), BARREL_BLOCK);
        //ENTITY
        FabricDefaultAttributeRegistry.register(CRAB, CrabEntity.createMobAttributes());
        HIBACHI_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, "grandecuisine", BlockEntityType.Builder.create(HibachiEntity::new, HIBACHI).build(null));
        BARREL_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, "grandecuisine:demo", BlockEntityType.Builder.create(BarrelBlockEntity::new, BARREL_BLOCK).build(null));
        //RENDER
        BlockRenderLayerMap.INSTANCE.putBlock(GrandeCuisine.HIBACHI, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(GrandeCuisine.BARREL_BLOCK, RenderLayer.getCutout());



    }


}
