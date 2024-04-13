package tech.thatgravyboat.skycubed.mixin.events;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalRef;
import it.unimi.dsi.fastutil.floats.FloatIntPair;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import tech.thatgravyboat.skycubed.api.events.ItemBarCallback;

@Mixin(GuiGraphics.class)
public class GuiGraphicsMixin {

    @WrapOperation(method = "renderItemDecorations(Lnet/minecraft/client/gui/Font;Lnet/minecraft/world/item/ItemStack;IILjava/lang/String;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;isBarVisible()Z"))
    private boolean itemBarVisible(ItemStack instance, Operation<Boolean> original, @Share("bar") LocalRef<FloatIntPair> bar) {
        bar.set(ItemBarCallback.EVENT.invoker().getBar(instance));
        return bar.get() != null || original.call(instance);
    }

    @WrapOperation(method = "renderItemDecorations(Lnet/minecraft/client/gui/Font;Lnet/minecraft/world/item/ItemStack;IILjava/lang/String;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;getBarWidth()I"))
    private int itemBarWidth(ItemStack instance, Operation<Integer> original, @Share("bar") LocalRef<FloatIntPair> bar) {
        if (bar.get() != null) {
            return (int) (bar.get().firstFloat() * 13);
        }
        return original.call(instance);
    }

    @WrapOperation(method = "renderItemDecorations(Lnet/minecraft/client/gui/Font;Lnet/minecraft/world/item/ItemStack;IILjava/lang/String;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;getBarColor()I"))
    private int itemBarColor(ItemStack instance, Operation<Integer> original, @Share("bar") LocalRef<FloatIntPair> bar) {
        if (bar.get() != null) {
            return bar.get().secondInt();
        }
        return original.call(instance);
    }

}
