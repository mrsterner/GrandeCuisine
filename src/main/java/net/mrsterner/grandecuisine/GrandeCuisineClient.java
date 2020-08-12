package net.mrsterner.grandecuisine;

import com.google.common.collect.ImmutableList;
import ladysnake.satin.api.event.ShaderEffectRenderCallback;
import ladysnake.satin.api.managed.ManagedShaderEffect;
import ladysnake.satin.api.managed.ShaderEffectManager;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.Environment;
import net.fabricmc.api.EnvType;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.FabricKeyBinding;
import net.fabricmc.fabric.api.client.keybinding.KeyBindingRegistry;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.event.client.ClientTickCallback;
import net.fabricmc.fabric.api.network.ClientSidePacketRegistry;
import net.minecraft.client.util.InputUtil;
import net.minecraft.util.Identifier;
import net.mrsterner.grandecuisine.client.renderer.CrabEntityRenderer;
import org.lwjgl.glfw.GLFW;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Handler;

@Environment(EnvType.CLIENT)
public class GrandeCuisineClient implements ClientModInitializer {

    int shaderIndex = 0;
    boolean shadersOn = false;
    boolean shadersOn2 = false;
    int shaderI = 0;
    Collection<Identifier> ids;
    List<ManagedShaderEffect> shaders;

    private static final ManagedShaderEffect GREYSCALE_SHADER = ShaderEffectManager.getInstance().manage(new Identifier("grandecuisine", "shaders/post/wobble2.json"));
    private static final ManagedShaderEffect SHADER = ShaderEffectManager.getInstance().manage(new Identifier("grandecuisine", "shaders/post/vampirevision.json"));

    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.INSTANCE.register(GrandeCuisine.CRAB, (dispatcher, context) -> {
            return new CrabEntityRenderer(dispatcher);
        });




        ClientSidePacketRegistry.INSTANCE.register(GrandeCuisine.PLAY_PARTICLE_PACKET_ID,
                (packetContext, attachedData) -> {
                    int shaderI = (attachedData.readInt());
                    packetContext.getTaskQueue().execute(() -> {
/*
                        ClientTickCallback.EVENT.register(ts -> {
                            if (shaderI==1) {
                                shadersOn = !shadersOn;
                                System.out.println("boolean");
                            }
                        });
 */
                    });
                    if (shaderI==1) {
                        shadersOn2 = true;
                        System.out.println("boolean");
                    }else if(shaderI==0){
                        shadersOn2 = false;
                    }


                        System.out.println("shader_start");

                        ShaderEffectRenderCallback.EVENT.register(rs -> {
                                if(shadersOn2) {
                                     GREYSCALE_SHADER.render(rs);
                                     System.out.println("shader_loop");
                                }


                    });
                });






        List<String> ids = ImmutableList.of("shaders/post/vampirevision.json",
                "shaders/post/wobble.json");
        List<ManagedShaderEffect> shaders = new ArrayList<ManagedShaderEffect>();
        for (String id : ids) {
            final ManagedShaderEffect add = ShaderEffectManager.getInstance().manage(new Identifier(id));
            shaders.add(add);
        }
        ShaderEffectRenderCallback.EVENT.register(rs -> {
            if (shadersOn) {
                shaders.get(shaderIndex).render(rs);
            }
        });

        FabricKeyBinding toggleShader = FabricKeyBinding.Builder
                .create(new Identifier("snm", "toggle"), InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_F6, "Secrets No More")
                .build();

        KeyBindingRegistry.INSTANCE.register(toggleShader);

        ClientTickCallback.EVENT.register(ts -> {
            if (toggleShader.wasPressed()) {
                shadersOn = !shadersOn;
            }
        });

        FabricKeyBinding cycleShaderUp = FabricKeyBinding.Builder.create(new Identifier("snm", "cycleup"),
                InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_KP_ADD, "Secrets No More").build();

        KeyBindingRegistry.INSTANCE.register(cycleShaderUp);

        FabricKeyBinding cycleShaderDown = FabricKeyBinding.Builder.create(new Identifier("snm", "cycledown"),
                InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_KP_SUBTRACT, "Secrets No More").build();

        KeyBindingRegistry.INSTANCE.register(cycleShaderDown);

        ClientTickCallback.EVENT.register(csu -> {
            if (cycleShaderUp.wasPressed()) {
                if (shaderIndex + 2 > shaders.size()) {
                    shaderIndex = 0;
                } else if (shaderIndex < 0) {
                    shaderIndex = shaders.size() - 1;
                } else {
                    shaderIndex += 1;
                }
                ;
            }
        });

        ClientTickCallback.EVENT.register(csd -> {
            if (cycleShaderDown.wasPressed()) {
                if (shaderIndex > shaders.size()) {
                    shaderIndex = 0;
                } else if (shaderIndex - 1 < 0) {
                    shaderIndex = shaders.size() - 1;
                } else {
                    shaderIndex -= 1;
                }
            }
        });



    }

}




