package tech.thatgravyboat.skycubed.features;

import tech.thatgravyboat.skycubed.features.hud.HiddenHudModule;
import tech.thatgravyboat.skycubed.features.hud.HudBarModule;
import tech.thatgravyboat.skycubed.features.jerry.BlasterModule;
import tech.thatgravyboat.skycubed.features.mining.MiningModule;
import tech.thatgravyboat.skycubed.features.misc.DevModule;
import tech.thatgravyboat.skycubed.features.misc.SkyBlockModule;
import tech.thatgravyboat.skycubed.features.stats.PlayerStatsModule;

public class SkyCubedFeatures {

    public static void init() {
        SkyBlockModule.init();

        HiddenHudModule.init();
        MiningModule.init();
        BlasterModule.init();
        DevModule.init();
        PlayerStatsModule.init();
        HudBarModule.init();
    }

}
