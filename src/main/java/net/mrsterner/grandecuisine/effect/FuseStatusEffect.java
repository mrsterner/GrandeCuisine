package net.mrsterner.grandecuisine.effect;

import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.network.ServerSidePacketRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffectType;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.explosion.Explosion;
import net.mrsterner.grandecuisine.GrandeCuisine;

public class FuseStatusEffect extends ModStatusEffect {

    public FuseStatusEffect(StatusEffectType type, int color, boolean isInstant) {
        super(type, color, isInstant);
    }

    @Override
    public boolean canApplyUpdateEffect(int remainingTicks, int level) {
        if (isInstant()) {
            return true;
        }
        return canApplyEffect(remainingTicks, level);
    }

    @Override
    protected boolean canApplyEffect(int remainingTicks, int level) {
        return remainingTicks == 1;
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int level) {
        if (!entity.world.isClient) {
            entity.world.createExplosion(null, entity.getX(), entity.getY() + 1, entity.getZ(), 0.5f+level, false, Explosion.DestructionType.BREAK);
        }
    }

    @Override
    public void onApplied(LivingEntity livingEntity, AttributeContainer abstractEntityAttributeContainer, int i) {
        System.out.println("Hejon");
        if (livingEntity instanceof PlayerEntity) {
            PacketByteBuf passedData = new PacketByteBuf(Unpooled.buffer());
            int shaderI = 1;
            boolean shaderOn2 = true;

            //passedData.writeBoolean(shaderOn2);
            passedData.writeInt(shaderI);
            ServerSidePacketRegistry.INSTANCE.sendToPlayer((PlayerEntity) livingEntity, GrandeCuisine.PLAY_PARTICLE_PACKET_ID,passedData);
            System.out.println("Hejon_end");
        }

    }
    @Override
    public void onRemoved(LivingEntity livingEntity, AttributeContainer abstractEntityAttributeContainer, int i) {
        System.out.println("Hejremove");
        if (livingEntity instanceof PlayerEntity) {
            PacketByteBuf passedData = new PacketByteBuf(Unpooled.buffer());
            int shaderI = 0;
            boolean shaderOn2 = false;
            //passedData.writeBoolean(shaderOn2);
            passedData.writeInt(shaderI);
            ServerSidePacketRegistry.INSTANCE.sendToPlayer((PlayerEntity) livingEntity, GrandeCuisine.PLAY_PARTICLE_PACKET_ID,passedData);
            System.out.println("Hejremove_end");
        }
    }

}