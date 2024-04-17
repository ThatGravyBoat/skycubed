package tech.thatgravyboat.skycubed.features.hud;

import com.teamresourceful.resourcefullib.common.utils.TriState;
import tech.thatgravyboat.skycubed.api.events.HudElementRenderCallback;
import tech.thatgravyboat.skycubed.config.features.hud.HudConfig;
import tech.thatgravyboat.skycubed.features.misc.skyblock.SkyBlockModule;

public class HiddenHudModule {

    public static void init() {
        HudElementRenderCallback.EVENT.register(element -> {
            if (SkyBlockModule.isSkyBlock() && HudConfig.hideEffects && element == HudElementRenderCallback.Element.EFFECTS) {
                return TriState.FALSE;
            }
            return TriState.UNDEFINED;
        });
    }
}
