package net.mrsterner.grandecuisine.items;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.mrsterner.grandecuisine.gui.ExampleGui;
import net.mrsterner.grandecuisine.gui.ExampleScreen;

public class GuiItem extends Item {
    public GuiItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        MinecraftClient.getInstance().openScreen(new ExampleScreen(new ExampleGui()));
        return super.use(world, user, hand);
    }
}
