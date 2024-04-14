package tech.thatgravyboat.skycubed.mixin.events;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.client.multiplayer.chat.ChatListener;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundRespawnPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import tech.thatgravyboat.skycubed.api.events.ActionBarCallback;
import tech.thatgravyboat.skycubed.api.events.ChangedDimensionsCallback;

@Mixin(ClientPacketListener.class)
public class ClientPacketListenerMixin {

    @WrapOperation(method = "handleSystemChat", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/multiplayer/chat/ChatListener;handleSystemMessage(Lnet/minecraft/network/chat/Component;Z)V"))
    private void handleSystemChat(ChatListener instance, Component message, boolean overlay, Operation<Void> original) {
        if (overlay) {
            original.call(instance, ActionBarCallback.EVENT.invoker().onActionBarMessage(message), true);
        } else {
            original.call(instance, message, false);
        }
    }

    @Inject(method = "handleRespawn", at = @At("TAIL"))
    private void handleRespawn(ClientboundRespawnPacket packet, CallbackInfo ci) {
        ChangedDimensionsCallback.EVENT.invoker().onActionBarMessage(packet.commonPlayerSpawnInfo());
    }
}
