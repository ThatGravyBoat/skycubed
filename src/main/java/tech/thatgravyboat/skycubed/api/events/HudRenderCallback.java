package tech.thatgravyboat.skycubed.api.events;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.client.gui.GuiGraphics;

public interface HudRenderCallback {
    Event<HudRenderCallback> EVENT = EventFactory.createArrayBacked(HudRenderCallback.class, (listeners) -> (matrixStack, delta) -> {
        for (HudRenderCallback event : listeners) {
            event.onHudRender(matrixStack, delta);
        }
    });

    void onHudRender(GuiGraphics graphics, float partialTicks);
}
