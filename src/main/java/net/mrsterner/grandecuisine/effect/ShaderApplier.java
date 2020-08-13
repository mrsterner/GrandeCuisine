
package net.mrsterner.grandecuisine.effect;

import ladysnake.satin.api.event.ShaderEffectRenderCallback;
import ladysnake.satin.api.managed.ManagedShaderEffect;
import ladysnake.satin.api.managed.ShaderEffectManager;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.network.ClientSidePacketRegistry;
import net.minecraft.util.Identifier;
import net.mrsterner.grandecuisine.GrandeCuisine;
@Environment(EnvType.CLIENT)
public class ShaderApplier implements ShaderEffectRenderCallback {
    public boolean enabled = false;
    final ManagedShaderEffect GREYSCALE_SHADER = ShaderEffectManager.getInstance().manage(new Identifier("grandecuisine", "shaders/post/wobble2.json"));
    public static final Identifier SHADER_PACKET_ID = new Identifier("example", "particle");
    @Override
    public void renderShaderEffects(float rs) {
        if (enabled)
            GREYSCALE_SHADER.render(rs);
    }
    public void onInitializeClient() {
    ShaderApplier shaderApplier = new ShaderApplier();
    ShaderEffectRenderCallback.EVENT.register(shaderApplier);
    ClientSidePacketRegistry.INSTANCE.register(SHADER_PACKET_ID, (ctx, byteBuf) -> {
        boolean shaderState = byteBuf.readBoolean();
        ctx.getTaskQueue().execute(() -> shaderApplier.enabled = shaderState);
    });
}}