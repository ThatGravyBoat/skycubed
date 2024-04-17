package tech.thatgravyboat.skycubed.features;

import tech.thatgravyboat.skycubed.features.hud.HiddenHudModule;
import tech.thatgravyboat.skycubed.features.hud.HudBarModule;
import tech.thatgravyboat.skycubed.features.hud.NpcModule;
import tech.thatgravyboat.skycubed.features.jerry.BlasterModule;
import tech.thatgravyboat.skycubed.features.mining.MiningModule;
import tech.thatgravyboat.skycubed.features.misc.DevModule;
import tech.thatgravyboat.skycubed.features.misc.hoppity.HoppityModule;
import tech.thatgravyboat.skycubed.features.misc.notifications.NotificationModule;
import tech.thatgravyboat.skycubed.features.misc.skyblock.SkyBlockModule;
import tech.thatgravyboat.skycubed.features.stats.PlayerStatsModule;

public class SkyCubedFeatures {

    public static void init() {
        SkyBlockModule.init();

        NotificationModule.init();
        HiddenHudModule.init();
        MiningModule.init();
        BlasterModule.init();
        DevModule.init();
        PlayerStatsModule.init();
        HudBarModule.init();
        HoppityModule.init();
        NpcModule.init();
    }

}
