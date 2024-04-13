package tech.thatgravyboat.skycubed.config.features;

import com.teamresourceful.resourcefulconfig.api.annotations.Category;
import com.teamresourceful.resourcefulconfig.api.annotations.Comment;
import com.teamresourceful.resourcefulconfig.api.annotations.ConfigEntry;
import com.teamresourceful.resourcefulconfig.api.types.options.EntryType;

@Category("hud-replacement")
public final class HudReplacementConfig {

    @ConfigEntry(
        id = "effects",
        type = EntryType.BOOLEAN,
        translation = "config.skycubed.effects"
    )
    @Comment("If true, effects will be hidden from the HUD.")
    public static boolean hideEffects = true;
}
