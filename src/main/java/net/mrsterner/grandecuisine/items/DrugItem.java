package net.mrsterner.grandecuisine.items;


import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PotionItem;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionUtil;
import net.minecraft.potion.Potions;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;


import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class DrugItem extends PotionItem {


    public DrugItem(Settings settings) {
        super(settings);
    }


    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {

        PlayerEntity playerEntity = user instanceof PlayerEntity ? (PlayerEntity)user : null;
        if (playerEntity instanceof ServerPlayerEntity) {
            Criteria.CONSUME_ITEM.trigger((ServerPlayerEntity)playerEntity, stack);
        }

        if (!world.isClient) {
            PotionUtil.getPotionEffects(stack).forEach(statusEffectInstance -> {
                if (statusEffectInstance.getEffectType().isInstant()) {
                    statusEffectInstance.getEffectType().applyInstantEffect(playerEntity, playerEntity, user, statusEffectInstance.getAmplifier(), 1.0D);
                } else {
                    user.addStatusEffect(new StatusEffectInstance(statusEffectInstance));
                }
            });
        }
        if (playerEntity != null) {
            playerEntity.incrementStat(Stats.USED.getOrCreateStat(this));
            if (!playerEntity.abilities.creativeMode) {
                stack.decrement(1);
            }
        }
        return stack;
    }
    public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
        if (this.isIn(group)) {
            Iterator<Potion> iterator = Registry.POTION.iterator();
            while(iterator.hasNext()) {
                Potion potion = iterator.next();
                if (potion != Potions.EMPTY) {
                    stacks.add(PotionUtil.setPotion(new ItemStack(this), potion));
                }
            }
        }

    }




        

    



}
