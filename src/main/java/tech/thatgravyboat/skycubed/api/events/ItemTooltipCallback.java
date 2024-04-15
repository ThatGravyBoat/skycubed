package tech.thatgravyboat.skycubed.api.events;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public interface ItemTooltipCallback {

    Event<ItemTooltipCallback> EVENT = EventFactory.createArrayBacked(ItemTooltipCallback.class, (listeners) -> (stack, tooltip) -> {
        for (ItemTooltipCallback listener : listeners) {
            listener.onTooltip(stack, tooltip);
        }
    });

    void onTooltip(ItemStack stack, List<Component> tooltip);
}
