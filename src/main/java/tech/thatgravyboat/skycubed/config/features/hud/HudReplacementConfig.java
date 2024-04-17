package tech.thatgravyboat.skycubed.config.features.hud;

import com.teamresourceful.resourcefulconfig.api.annotations.Category;
import com.teamresourceful.resourcefulconfig.api.annotations.Comment;
import com.teamresourceful.resourcefulconfig.api.annotations.ConfigEntry;
import com.teamresourceful.resourcefulconfig.api.types.options.EntryType;

@Category("hudReplacement")
public final class HudReplacementConfig {

    @ConfigEntry(
            id = "replaceVanillaHud",
            type = EntryType.BOOLEAN,
            translation = "config.skycubed.hudReplacement.replaceVanillaHud"
    )
    @Comment("Changed the vanilla HUD to custom bars.")
    public static boolean replaceVanillaHud = true;

    @ConfigEntry(
            id = "showEffectiveHealth",
            type = EntryType.BOOLEAN,
            translation = "config.skycubed.hudReplacement.showEffectiveHealth"
    )
    @Comment("Shows health as effective health instead of raw health.")
    public static boolean showEffectiveHealth = false;
}
