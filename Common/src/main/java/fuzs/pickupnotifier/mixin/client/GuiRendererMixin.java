package fuzs.pickupnotifier.mixin.client;

import fuzs.pickupnotifier.client.gui.render.state.TransparentGuiItemRenderState;
import net.minecraft.client.gui.render.GuiItemAtlas;
import net.minecraft.client.gui.render.GuiRenderer;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.client.renderer.state.gui.GuiItemRenderState;
import net.minecraft.util.ARGB;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

/**
 * @see <a
 *         href="https://github.com/Crendgrim/AutoHUD/blob/main/src/main/java/mod/crend/autohud/mixin/GuiRendererMixin.java">GuiRendererMixin.java</a>
 */
@Mixin(GuiRenderer.class)
abstract class GuiRendererMixin {

    @ModifyArgs(method = "submitBlitFromItemAtlas",
                at = @At(value = "INVOKE",
                         target = "Lnet/minecraft/client/renderer/state/gui/BlitRenderState;<init>(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/client/gui/render/TextureSetup;Lorg/joml/Matrix3x2f;IIIIFFFFILnet/minecraft/client/gui/navigation/ScreenRectangle;Lnet/minecraft/client/gui/navigation/ScreenRectangle;)V"))
    private void submitBlitFromItemAtlas(Args args, GuiItemRenderState itemState, GuiItemAtlas.SlotView slotView) {
        float alpha = TransparentGuiItemRenderState.class.cast(itemState).pickupnotifier$getAlpha();
        if (alpha != 1.0F) {
            args.set(0, RenderPipelines.GUI_TEXTURED);
            args.set(11, ARGB.color(alpha, args.get(11)));
        }
    }
}
