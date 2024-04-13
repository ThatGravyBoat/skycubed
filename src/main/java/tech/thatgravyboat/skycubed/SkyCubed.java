package tech.thatgravyboat.skycubed;

import net.fabricmc.api.ModInitializer;
import tech.thatgravyboat.skycubed.config.ConfigHandler;
import tech.thatgravyboat.skycubed.features.SkyCubedFeatures;
import tech.thatgravyboat.skycubed.utils.RepoPattern;

public class SkyCubed implements ModInitializer {

    public static final String MOD_ID = "skycubed";

    @Override
    public void onInitialize() {
        RepoPattern.init();
        ConfigHandler.init();
        SkyCubedFeatures.init();
    }
}
