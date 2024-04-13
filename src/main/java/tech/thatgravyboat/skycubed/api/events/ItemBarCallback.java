package tech.thatgravyboat.skycubed.api.events;

import it.unimi.dsi.fastutil.floats.FloatIntPair;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.world.item.ItemStack;

public interface ItemBarCallback {

    Event<ItemBarCallback> EVENT = EventFactory.createArrayBacked(ItemBarCallback.class, (listeners) -> (stack) -> {
        for (ItemBarCallback listener : listeners) {
            FloatIntPair bar = listener.getBar(stack);
            if (bar != null) {
                return bar;
            }
        }
        return null;
    });

    FloatIntPair getBar(ItemStack stack);
}
