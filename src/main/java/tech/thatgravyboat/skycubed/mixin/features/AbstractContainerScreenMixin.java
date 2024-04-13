package tech.thatgravyboat.skycubed.mixin.features;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.world.inventory.Slot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import tech.thatgravyboat.skycubed.config.features.GuiConfig;
import tech.thatgravyboat.skycubed.utils.ItemUtils;

@Mixin(AbstractContainerScreen.class)
public class AbstractContainerScreenMixin {

    @WrapOperation(
            method = "render",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screens/inventory/AbstractContainerScreen;isHovering(Lnet/minecraft/world/inventory/Slot;DD)Z")
    )
    private boolean render(AbstractContainerScreen<?> instance, Slot slot, double d, double e, Operation<Boolean> original) {
        if (GuiConfig.hideNullPanes && slot.hasItem() && ItemUtils.isNullPane(slot.getItem())) {
            return false;
        }
        return original.call(instance, slot, d, e);
    }

    @Inject(
            method = "mouseClicked",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/Util;getMillis()J"),
            cancellable = true
    )
    private void mouseClicked(double d, double e, int i, CallbackInfoReturnable<Boolean> cir, @Local Slot slot) {
        if (GuiConfig.hideNullPanes && slot != null && slot.hasItem() && ItemUtils.isNullPane(slot.getItem())) {
            cir.setReturnValue(true);
        }
    }
}
