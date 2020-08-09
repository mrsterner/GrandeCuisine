package net.mrsterner.grandecuisine.blockentity;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.CompoundTag;
import net.mrsterner.grandecuisine.GrandeCuisine;

public class HibachiEntity extends BlockEntity {

    private int number = 7;

    public HibachiEntity() {
        super(GrandeCuisine.HIBACHI_ENTITY);
    }

    // Serialize the BlockEntity
    public CompoundTag toTag(CompoundTag tag) {
        super.toTag(tag);

        // Save the current value of the number to the tag
        tag.putInt("number", number);

        return tag;
    }


}