package tech.thatgravyboat.skycubed.mixin.events;

import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.network.protocol.game.ClientboundRespawnPacket;
import net.minecraft.network.protocol.game.ClientboundSystemChatPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import tech.thatgravyboat.skycubed.api.events.ActionBarCallback;
import tech.thatgravyboat.skycubed.api.events.ChangedDimensionsCallback;

@Mixin(ClientPacketListener.class)
public class ClientPacketListenerMixin {

    @Inject(method = "handleSystemChat", at = @At("TAIL"))
    private void handleSystemChat(ClientboundSystemChatPacket packet, CallbackInfo ci) {
        if (!packet.overlay()) return;
        ActionBarCallback.EVENT.invoker().onActionBarMessage(packet.content());
    }

    @Inject(method = "handleRespawn", at = @At("TAIL"))
    private void handleRespawn(ClientboundRespawnPacket packet, CallbackInfo ci) {
        ChangedDimensionsCallback.EVENT.invoker().onActionBarMessage(packet.commonPlayerSpawnInfo());
    }
}
