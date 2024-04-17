package tech.thatgravyboat.skycubed.config.features.hud;

import com.teamresourceful.resourcefulconfig.api.annotations.Category;
import com.teamresourceful.resourcefulconfig.api.annotations.Comment;
import com.teamresourceful.resourcefulconfig.api.annotations.ConfigEntry;
import com.teamresourceful.resourcefulconfig.api.types.options.EntryType;

@Category(value = "hud", categories = {
        HudReplacementConfig.class,
        DialogueBoxConfig.class
})
public final class HudConfig {

    @ConfigEntry(
        id = "effects",
        type = EntryType.BOOLEAN,
        translation = "config.skycubed.effects"
    )
    @Comment("If true, effects will be hidden from the HUD.")
    public static boolean hideEffects = true;


}
