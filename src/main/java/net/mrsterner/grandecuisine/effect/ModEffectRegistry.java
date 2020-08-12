package net.mrsterner.grandecuisine.effect;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.lang.reflect.Field;

public class ModEffectRegistry {

    public static final int MAGNETISM_COLOR = 0xb8b8b8;

    public static ModStatusEffect crumbling = new CrumblingStatusEffect(StatusEffectType.NEUTRAL, 0x794044, false);
    public static ModStatusEffect fuse = new FuseStatusEffect(StatusEffectType.HARMFUL, 0xcc3333, false);

    //Instant


    public static void registerAll() {

        try {
            int registered = 0;
            for (Field field:ModEffectRegistry.class.getDeclaredFields()) {
                if (ModStatusEffect.class.isAssignableFrom(field.getType())) {
                    Identifier id = new Identifier(LibMod.MOD_ID, field.getName());
                    Registry.register(Registry.STATUS_EFFECT, id, ((ModStatusEffect) field.get(null)).onRegister());
                    Log.d("Registered potion "+id);
                    registered++;
                }
            }
            Log.i("Registered %d status effects", registered);

        } catch (Exception e) {
            Log.printAndPropagate(e);
        }

    }



}
