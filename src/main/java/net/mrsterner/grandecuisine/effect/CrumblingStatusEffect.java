package net.mrsterner.grandecuisine.effect;

import com.google.common.collect.Lists;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffectType;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

import java.util.ArrayList;

public class CrumblingStatusEffect extends ModStatusEffect {

	public CrumblingStatusEffect(StatusEffectType type, int color, boolean isInstant) {
		super(type, color, isInstant);
	}

	@Override
	protected boolean canApplyEffect(int remainingTicks, int level) {
		return true;
	}

	@Override
	public void applyUpdateEffect(LivingEntity entity, int level) {
		if (!entity.world.isClient) {
			ModPotion fuse = ModPotion.ModPotionTimed.generateAll("fuse", ModEffectRegistry.fuse, 20*20, 20*10, 20*30);
			StatusEffectInstance slowness = new StatusEffectInstance(StatusEffects.SLOWNESS, 200, 1, false, true, true);

		}
	}
	public void applyInstantEffect(Entity source, Entity attacker, LivingEntity target, int amplifier, double d) {
		if (target instanceof ServerPlayerEntity) {
			ServerPlayerEntity player = (ServerPlayerEntity) target;
			BlockPos respawnPos = player.getSpawnPointPosition();
			if (respawnPos != null) {
				PlayerEntity.findRespawnPosition((ServerWorld) target.world, respawnPos, player.isSpawnPointSet(), true).ifPresent(v3d -> {
					player.requestTeleport(v3d.x, v3d.y, v3d.z);
				});
			}
		}
	}

}
