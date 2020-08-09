package net.mrsterner.grandecuisine.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.ai.pathing.PathNodeType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import net.minecraft.world.biome.Biome;
import org.jetbrains.annotations.Nullable;

/*
 * Our Cube Entity extends MobEntityWithAi, which extends MobEntity, which extends LivingEntity.
 *
 * LivingEntity has health and can deal damage.
 * MobEntity has movement controls and AI capabilities.
 * MobEntityWithAi has pathfinding favor and slightly tweaked leash behavior.
 */
public class CrabEntity extends AnimalEntity {

    public CrabEntity(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
        this.stepHeight = 1.0F;
        this.experiencePoints = 30;
        this.setPathfindingPenalty(PathNodeType.LAVA, 8.0F);
    }

    public static DefaultAttributeContainer.Builder createCrabAttributes() {
        return PassiveEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 4.0D)
                .add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 0.5D)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 1.0D)
                .add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, 1D)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 10.0D);
    }
    @Override
    public void initGoals() {
        super.initGoals();
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(5, new WanderAroundGoal(this, 0.2D));
    }

    @Override
    public boolean canSpawn(WorldAccess world, SpawnReason spawnReason) {
        // seed for testing: 6211790508050301845
        BlockPos down = getBlockPos().down();
        boolean ret = !world.getDifficulty().equals(Difficulty.PEACEFUL);
        ret = ret && this.getY() >= 50f && this.getY() <= 70f;
        ret = ret && world.getBiome(down).getCategory() == Biome.Category.BEACH;
        // this last condition is basically super.super.canSpawn()
        ret = ret && (spawnReason == SpawnReason.SPAWNER || world.getBlockState(down).allowsSpawning(world, down, getType()));
        return ret;
    }





    @Override
    public @Nullable PassiveEntity createChild(PassiveEntity mate) {
        return null;
    }
}
