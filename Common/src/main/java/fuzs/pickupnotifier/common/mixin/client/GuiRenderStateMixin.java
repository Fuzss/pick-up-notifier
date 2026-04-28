package fuzs.pickupnotifier.common.mixin.client;

import fuzs.pickupnotifier.common.client.gui.render.state.TransparentGuiItemRenderState;
import fuzs.pickupnotifier.common.client.util.DisplayEntryRenderHelper;
import net.minecraft.client.renderer.state.gui.GuiItemRenderState;
import net.minecraft.client.renderer.state.gui.GuiRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiRenderState.class)
abstract class GuiRenderStateMixin {

    @Inject(method = "addItem", at = @At("HEAD"))
    public void submitItem(GuiItemRenderState renderState, CallbackInfo callback) {
        float alpha = DisplayEntryRenderHelper.getItemStackAlpha();
        if (alpha != 1.0F) {
            TransparentGuiItemRenderState.class.cast(renderState).pickupnotifier$setAlpha(alpha);
        }
    }
}
