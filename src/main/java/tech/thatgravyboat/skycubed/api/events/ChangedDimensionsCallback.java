package tech.thatgravyboat.skycubed.api.events;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.network.protocol.game.CommonPlayerSpawnInfo;

public interface ChangedDimensionsCallback {

    Event<ChangedDimensionsCallback> EVENT = EventFactory.createArrayBacked(ChangedDimensionsCallback.class, (listeners) -> (info) -> {
        for (ChangedDimensionsCallback listener : listeners) {
            listener.onActionBarMessage(info);
        }
    });

    void onActionBarMessage(CommonPlayerSpawnInfo info);
}
