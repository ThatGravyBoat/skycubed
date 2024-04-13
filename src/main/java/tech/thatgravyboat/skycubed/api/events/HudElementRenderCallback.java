package tech.thatgravyboat.skycubed.api.events;

import com.teamresourceful.resourcefullib.common.utils.TriState;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;

public interface HudElementRenderCallback {

    Event<HudElementRenderCallback> EVENT = EventFactory.createArrayBacked(HudElementRenderCallback.class, (listeners) -> (element) -> {
        TriState result = TriState.UNDEFINED;
        for (HudElementRenderCallback event : listeners) {
            TriState state = event.onRender(element);
            if (event.onRender(element).isDefined()) {
                result = state;
            }
        }
        return result;
    });

    TriState onRender(Element element);

    enum Element {
        ARMOR,
        HEALTH,
        HUNGER,
        AIR,
        EXPERIENCE,
        HOTBAR,
        JUMP_BAR,
        SCOREBOARD,
        EFFECTS,
    }
}
