package tech.thatgravyboat.skycubed.api.events;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.network.chat.Component;

public interface ActionBarCallback {

    Event<ActionBarCallback> EVENT = EventFactory.createArrayBacked(ActionBarCallback.class, (listeners) -> (message) -> {
        for (ActionBarCallback listener : listeners) {
            listener.onActionBarMessage(message);
        }
    });

    void onActionBarMessage(Component message);
}
