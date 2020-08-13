package net.mrsterner.grandecuisine;

import ladysnake.satin.api.event.ShaderEffectRenderCallback;
import ladysnake.satin.api.managed.ManagedShaderEffect;
import ladysnake.satin.api.managed.ShaderEffectManager;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.Environment;
import net.fabricmc.api.EnvType;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.network.ClientSidePacketRegistry;
import net.minecraft.util.Identifier;
import net.mrsterner.grandecuisine.client.renderer.CrabEntityRenderer;
import net.mrsterner.grandecuisine.effect.ShaderApplier;

@Environment(EnvType.CLIENT)
public class GrandeCuisineClient implements ClientModInitializer, ShaderEffectRenderCallback {

    public boolean enabled = false;
    public boolean shaderState = false;

    private static final ManagedShaderEffect GREYSCALE_SHADER = ShaderEffectManager.getInstance().manage(new Identifier("grandecuisine", "shaders/post/wobble2.json"));

    @Override
    public void onInitializeClient() {
        //ShaderEffectRenderCallback.EVENT.register((ShaderEffectRenderCallback) ShaderApplier.EVENT);
        EntityRendererRegistry.INSTANCE.register(GrandeCuisine.CRAB, (dispatcher, context) -> {
            return new CrabEntityRenderer(dispatcher);
        });

        ShaderApplier renderShaderEffects = new ShaderApplier();
        ShaderEffectRenderCallback.EVENT.register(renderShaderEffects);
        ClientSidePacketRegistry.INSTANCE.register(GrandeCuisine.SHADER_PACKET_ID,(packetContext, attachedData)->

        {
            boolean shaderState = attachedData.readBoolean();
            packetContext.getTaskQueue().execute(() -> renderShaderEffects.enabled = shaderState);
        });

}

    @Override
    public void renderShaderEffects(float tickDelta) {
        if (shaderState)
            GREYSCALE_SHADER.render(tickDelta);
    }
}





