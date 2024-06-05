package de.myronx.nofirearrow.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.ArrowEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.ArrowEntity;

@Environment(EnvType.CLIENT)
public class NoFireArrowClient implements ClientModInitializer {

    public static boolean toggleFire;
    public static boolean renderArrow;

    @Override
    public void onInitializeClient() {
        toggleFire = Config.toggleFire();
        renderArrow = Config.renderArrow();

        EntityRendererRegistry.register(EntityType.ARROW, NoFireArrowRenderer::new);
        NoFireArrowCommand.register();

        ClientLifecycleEvents.CLIENT_STOPPING.register(client -> Config.saveConfig());
    }

    public static class NoFireArrowRenderer extends ArrowEntityRenderer {

        public NoFireArrowRenderer(EntityRendererFactory.Context context) {
            super(context);
        }

        @Override
        public void render(ArrowEntity arrowEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
            if (toggleFire) {
                arrowEntity.setOnFire(false);
            }

            if (renderArrow) {
                super.render(arrowEntity, f, g, matrixStack, vertexConsumerProvider, i);
            }
        }
    }
}
