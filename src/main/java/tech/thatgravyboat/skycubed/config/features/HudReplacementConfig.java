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

    @ConfigEntry(
            id = "replace-vanilla-hud",
            type = EntryType.BOOLEAN,
            translation = "config.skycubed.replaceVanillaHud"
    )
    @Comment("Changed the vanilla HUD to custom bars.")
    public static boolean replaceVanillaHud = true;

    @ConfigEntry(
            id = "show-effective-health",
            type = EntryType.BOOLEAN,
            translation = "config.skycubed.showEffectiveHealth"
    )
    @Comment("Shows health as effective health instead of raw health.")
    public static boolean showEffectiveHealth = false;
}
