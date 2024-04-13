package tech.thatgravyboat.skycubed.config.features;

import com.teamresourceful.resourcefulconfig.api.annotations.Category;
import com.teamresourceful.resourcefulconfig.api.annotations.Comment;
import com.teamresourceful.resourcefulconfig.api.annotations.ConfigEntry;
import com.teamresourceful.resourcefulconfig.api.types.options.EntryType;

@Category("dev")
public final class DevConfig {

    @ConfigEntry(
        id = "devMode",
        type = EntryType.BOOLEAN,
        translation = "config.skycubed.devMode"
    )
    @Comment("If true, the mod will be in development mode.")
    public static boolean devMode = false;
}
