package net.ntrdeal.ntrdeals_items.client.render;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.RenderPhase;
import net.minecraft.client.render.VertexFormat;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.client.render.block.entity.EndPortalBlockEntityRenderer;

@Environment(EnvType.CLIENT)
public class ModRenderLayer extends RenderPhase {
    public ModRenderLayer(String name, Runnable beginAction, Runnable endAction) {
        super(name, beginAction, endAction);
    }

    public static final RenderLayer COSMOLITE_OVERLAY = RenderLayer.of("cosmolite_overlay",
            VertexFormats.POSITION_TEXTURE,
            VertexFormat.DrawMode.QUADS,
            1536,
            RenderLayer.MultiPhaseParameters.builder()
                    .program(RenderPhase.END_GATEWAY_PROGRAM)
                    .texture(RenderPhase.Textures.create()
                            .add(EndPortalBlockEntityRenderer.SKY_TEXTURE, false, false)
                            .add(EndPortalBlockEntityRenderer.PORTAL_TEXTURE, false, false)
                            .build())
                    .writeMaskState(RenderPhase.COLOR_MASK)
                    .cull(RenderPhase.DISABLE_CULLING)
                    .depthTest(RenderPhase.EQUAL_DEPTH_TEST)
                    .transparency(RenderPhase.LIGHTNING_TRANSPARENCY)
                    .layering(RenderPhase.VIEW_OFFSET_Z_LAYERING)
                    .build(false));
}
