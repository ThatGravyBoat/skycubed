package tech.thatgravyboat.skycubed.mixin.events;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.GuiMessageTag;
import net.minecraft.client.gui.components.ChatComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MessageSignature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import tech.thatgravyboat.skycubed.api.events.ChatAddCallback;

@Mixin(ChatComponent.class)
public class ChatComponentMixin {

    @WrapOperation(
        method = "addMessage(Lnet/minecraft/network/chat/Component;Lnet/minecraft/network/chat/MessageSignature;Lnet/minecraft/client/GuiMessageTag;)V",
        at = @At(
                value = "INVOKE",
                target = "Lnet/minecraft/client/gui/components/ChatComponent;addMessage(Lnet/minecraft/network/chat/Component;Lnet/minecraft/network/chat/MessageSignature;ILnet/minecraft/client/GuiMessageTag;Z)V"
        )
    )
    private void onAddMessage(ChatComponent instance, Component component, MessageSignature messageSignature, int i, GuiMessageTag guiMessageTag, boolean bl, Operation<Void> original) {
        Component message = ChatAddCallback.EVENT.invoker().onMessage(component);
        if (message != null) {
            original.call(instance, message, messageSignature, i, guiMessageTag, bl);
        }
    }
}
