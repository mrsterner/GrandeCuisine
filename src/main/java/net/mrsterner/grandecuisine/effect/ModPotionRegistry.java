package net.mrsterner.grandecuisine.effect;

import net.minecraft.util.Identifier;

import java.lang.reflect.Field;

public class ModPotionRegistry {

	public static ModPotion crumbling = ModPotion.ModPotionTimed.generateAll("crumbling", ModEffectRegistry.crumbling, 20*60, 20*100, 20*40);
	public static ModPotion fuse = ModPotion.ModPotionTimed.generateAll("fuse", ModEffectRegistry.fuse, 20*20, 20*10, 20*30);
	



	public static void registerAll() {
		try {
			int registered = 0;
			for (Field field: net.mrsterner.grandecuisine.effect.ModPotionRegistry.class.getDeclaredFields()) {
				if (ModPotion.class.isAssignableFrom(field.getType())) {
					Identifier id = new Identifier(LibMod.MOD_ID, field.getName());
					Log.d("Registering potion "+id);
					registered += ((ModPotion) field.get(null)).registerTree(LibMod.MOD_ID, field.getName());
				}
			}
			Log.i("Registered %d potions", registered);
		} catch (Exception e) {
			Log.printAndPropagate(e);
		}
	}

}
