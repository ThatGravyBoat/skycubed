package tech.thatgravyboat.skycubed.mixin.features;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.EffectRenderingInventoryScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import tech.thatgravyboat.skycubed.config.features.hud.HudConfig;

@Mixin(EffectRenderingInventoryScreen.class)
public class EffectRenderingInventoryScreenMixin {

    @Inject(method = "canSeeEffects", at = @At("HEAD"), cancellable = true)
    private void canSeeEffects(CallbackInfoReturnable<Boolean> cir) {
        if (HudConfig.hideEffects) {
            cir.setReturnValue(false);
        }
    }

    @Inject(method = "renderEffects", at = @At("HEAD"), cancellable = true)
    private void renderEffects(GuiGraphics guiGraphics, int i, int j, CallbackInfo ci) {
        if (HudConfig.hideEffects) {
            ci.cancel();
        }
    }
}
