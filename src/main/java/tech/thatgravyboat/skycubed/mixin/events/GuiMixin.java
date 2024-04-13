package tech.thatgravyboat.skycubed.mixin.events;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.teamresourceful.resourcefullib.common.utils.TriState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PlayerRideableJumping;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import tech.thatgravyboat.skycubed.api.events.HudElementRenderCallback;
import tech.thatgravyboat.skycubed.api.events.HudRenderCallback;

@Mixin(Gui.class)
public class GuiMixin {

    @Final
    @Shadow
    private Minecraft minecraft;

    @Unique
    private static boolean renderEvent(HudElementRenderCallback.Element element) {
        TriState state = HudElementRenderCallback.EVENT.invoker().onRender(element);
        return state.isFalse();
    }

    @Inject(method = "renderHotbar", at = @At("HEAD"), cancellable = true)
    private void onHotbarRender(float f, GuiGraphics guiGraphics, CallbackInfo ci) {
        if (renderEvent(HudElementRenderCallback.Element.HOTBAR)) {
            ci.cancel();
        }
    }

    @Inject(method = "renderJumpMeter", at = @At("HEAD"), cancellable = true)
    private void onJumpBarRender(PlayerRideableJumping playerRideableJumping, GuiGraphics guiGraphics, int i, CallbackInfo ci) {
        if (renderEvent(HudElementRenderCallback.Element.JUMP_BAR)) {
            ci.cancel();
        }
    }

    @Inject(method = "renderExperienceBar", at = @At("HEAD"), cancellable = true)
    private void onExperienceBarRender(GuiGraphics guiGraphics, int i, CallbackInfo ci) {
        if (renderEvent(HudElementRenderCallback.Element.EXPERIENCE)) {
            ci.cancel();
        }
    }

    @Inject(method = "displayScoreboardSidebar", at = @At("HEAD"), cancellable = true)
    private void onScoreboardRender(CallbackInfo ci) {
        if (renderEvent(HudElementRenderCallback.Element.SCOREBOARD)) {
            ci.cancel();
        }
    }

    @Inject(method = "renderHearts", at = @At("HEAD"), cancellable = true)
    private void onRenderHearts(GuiGraphics guiGraphics, Player player, int i, int j, int k, int l, float f, int m, int n, int o, boolean bl, CallbackInfo ci) {
        if (renderEvent(HudElementRenderCallback.Element.HEALTH)) {
            ci.cancel();
        }
    }

    @Inject(method = "renderVehicleHealth", at = @At("HEAD"), cancellable = true)
    private void onVehicleHealthRender(GuiGraphics guiGraphics, CallbackInfo ci) {
        if (renderEvent(HudElementRenderCallback.Element.HEALTH)) {
            ci.cancel();
        }
    }

    @Inject(method = "renderEffects", at = @At("HEAD"), cancellable = true)
    private void onEffectsRender(GuiGraphics guiGraphics, CallbackInfo ci) {
        if (renderEvent(HudElementRenderCallback.Element.EFFECTS)) {
            ci.cancel();
        }
    }

    @ModifyConstant(method = "renderPlayerHealth", constant = @Constant(intValue = 10, ordinal = 4))
    private int onArmorRender(int constant) {
        if (renderEvent(HudElementRenderCallback.Element.ARMOR)) {
            return 0;
        }
        return constant;
    }

    @WrapOperation(method = "renderPlayerHealth", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Gui;getVehicleMaxHearts(Lnet/minecraft/world/entity/LivingEntity;)I"))
    private int onFoodRender(Gui instance, LivingEntity livingEntity, Operation<Integer> original) {
        if (renderEvent(HudElementRenderCallback.Element.HUNGER)) {
            return -1;
        }
        return original.call(instance, livingEntity);
    }

    @Inject(method = "renderPlayerHealth", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/profiling/ProfilerFiller;popPush(Ljava/lang/String;)V", ordinal = 2, shift = At.Shift.BEFORE), cancellable = true)
    private void onAirRender(GuiGraphics guiGraphics, CallbackInfo ci) {
        if (renderEvent(HudElementRenderCallback.Element.AIR)) {
            this.minecraft.getProfiler().pop();
            ci.cancel();
        }
    }

    @Inject(
            method = "render",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/mojang/blaze3d/systems/RenderSystem;enableBlend()V",
                    ordinal = 3,
                    shift = At.Shift.BEFORE
            )
    )
    private void onRender(GuiGraphics graphics, float partialTicks, CallbackInfo ci) {
        HudRenderCallback.EVENT.invoker().onHudRender(graphics, partialTicks);
    }

}
