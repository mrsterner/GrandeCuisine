package net.mrsterner.grandecuisine.block;


import net.minecraft.block.*;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

import java.util.Random;


public class HibachiBlock extends HorizontalFacingBlock {

    public static final Property<Integer> POWER = Properties.POWER;
    public static final Property<Integer> AGE;
    private static final VoxelShape EAST_SHAPE;
    private static final VoxelShape WEST_SHAPE;
    private static final VoxelShape SOUTH_SHAPE;
    private static final VoxelShape NORTH_SHAPE;

    //private static final IntProperty AGE;

    public HibachiBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.NORTH).with(AGE, 0).with(POWER, 0));
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(POWER, ctx.getWorld().getReceivedRedstonePower(ctx.getBlockPos())).with(FACING, ctx.getPlayerFacing().getOpposite());
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
        builder.add(new Property[]{AGE});
        builder.add(POWER);
    }

    @Override
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        if (getAge(state) == 1) {
            world.spawnEntity(new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(Items.SALMON)));
        } else if (getAge(state) == 2) {
            world.spawnEntity(new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(Items.COOKED_SALMON)));
        }
        super.onBreak(world, pos, state, player);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (getAge(state) == 0) {
            ItemStack stack = player.getStackInHand(hand);
            if (stack.getItem() == Items.SALMON) {
                setAge(world, pos, state, 1);
                if (!player.isCreative()) {
                    stack.setCount(stack.getCount() - 1);
                }
                return ActionResult.CONSUME;

            }
        } else if (getAge(state) == 1) {
            setAge(world, pos, state, 0);
            world.spawnEntity(new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(Items.SALMON)));
            return ActionResult.SUCCESS;
        } else if (getAge(state) == 2) {
            setAge(world, pos, state, 0);
            world.spawnEntity(new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(Items.COOKED_SALMON)));
            return ActionResult.SUCCESS;
        }
        return ActionResult.FAIL;
    }

    public void setAge(World world, BlockPos pos, BlockState state, int age) {
        world.setBlockState(pos, state.with(AGE, age), 2);
    }

    public int getAge(BlockState state) {
        return (Integer) state.get(AGE);
    }

    public void setPower(World world, BlockPos pos, BlockState state, int power) {
        world.setBlockState(pos, state.with(POWER, power), 10);
    }

    @Override
    public boolean hasRandomTicks(BlockState state) {
        return true;
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        super.scheduledTick(state, world, pos, random);
        if (getAge(state) == 1) {
            if (random.nextInt(8) == 0) {
                world.setBlockState(pos, state.with(AGE, 2), 2);
            }
        }
        if (state.get(POWER) > 0 && !world.isReceivingRedstonePower(pos)) {
            world.setBlockState(pos, state.with(POWER, 0), 2);
        }
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, ShapeContext context) {
        switch (state.get(FACING)) {
            case NORTH:
                return NORTH_SHAPE;
            case EAST:
                return EAST_SHAPE;
            case SOUTH:
                return SOUTH_SHAPE;
            case WEST:
                return WEST_SHAPE;
            default:
                return super.getOutlineShape(state, view, pos, context);
        }
    }

    static {
        VoxelShape shape = createCuboidShape(2, 0, 15, 14, 7, 1);

        EAST_SHAPE = shape;
        NORTH_SHAPE = rotate(Direction.EAST, Direction.NORTH, shape);
        SOUTH_SHAPE = rotate(Direction.EAST, Direction.SOUTH, shape);
        WEST_SHAPE = rotate(Direction.EAST, Direction.WEST, shape);

        AGE = Properties.AGE_2;
    }

    private static VoxelShape rotate(Direction from, Direction to, VoxelShape shape) {
        VoxelShape[] buffer = new VoxelShape[]{shape, VoxelShapes.empty()};

        int times = (to.getHorizontal() - from.getHorizontal() + 4) % 4;
        for (int i = 0; i < times; i++) {
            buffer[0].forEachBox((minX, minY, minZ, maxX, maxY, maxZ) -> buffer[1] = VoxelShapes.union(buffer[1], VoxelShapes.cuboid(1 - maxZ, minY, minX, 1 - minZ, maxY, maxX)));
            buffer[0] = buffer[1];
            buffer[1] = VoxelShapes.empty();
        }

        return buffer[0];
    }

    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {

        double xPos2 = (double)pos.getX() + random.nextDouble()/2 + 0.25D;
        double yPos2 = (double)pos.getY() + random.nextDouble()/2;
        double zPos2 = (double)pos.getZ() + random.nextDouble()/2 + 0.25D;

        switch(getAge(state)){
            case 0:
                setPower(world, pos, state, 0);
                break;
            case 1:

            case 2:
                if (random.nextInt(3) == 0) {
                    // double xPos = (double)pos.getX() + 0.5D;
                    // double yPos = (double)pos.getY() + random.nextDouble();
                    // double zPos = (double)pos.getZ() + 0.5D;
                    world.setBlockState(pos, state.with(POWER, world.getReceivedRedstonePower(pos)), 2);
                    setPower(world, pos, state, 10);
                    world.addParticle(ParticleTypes.FLAME, xPos2, yPos2, zPos2, 0.0D, 0.01D, 0.0D);

                }
                break;
        }}


}








