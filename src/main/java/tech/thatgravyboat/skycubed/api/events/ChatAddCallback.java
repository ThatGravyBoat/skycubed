package tech.thatgravyboat.skycubed.api.events;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.Nullable;

public interface ChatAddCallback {

    Event<ChatAddCallback> EVENT = EventFactory.createArrayBacked(ChatAddCallback.class, (listeners) -> (message) -> {
        Component newMessage = message;
        for (ChatAddCallback listener : listeners) {
            newMessage = listener.onMessage(message);
            if (newMessage == null) {
                return null;
            }
        }
        return newMessage;
    });

    @Nullable
    Component onMessage(Component message);
}
