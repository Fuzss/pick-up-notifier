package fuzs.pickupnotifier.mixin.client;

import fuzs.pickupnotifier.client.gui.render.state.TransparentGuiItemRenderState;
import net.minecraft.client.renderer.state.gui.GuiItemRenderState;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(GuiItemRenderState.class)
abstract class GuiItemRenderStateMixin implements TransparentGuiItemRenderState {
    @Unique
    @Nullable
    private Float pickupnotifier$alpha;

    @Override
    public float pickupnotifier$getAlpha() {
        return this.pickupnotifier$alpha != null ? this.pickupnotifier$alpha : 1.0F;
    }

    @Override
    public void pickupnotifier$setAlpha(float alpha) {
        this.pickupnotifier$alpha = alpha;
    }
}
