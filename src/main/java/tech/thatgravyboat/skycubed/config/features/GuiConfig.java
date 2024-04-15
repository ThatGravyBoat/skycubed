package tech.thatgravyboat.skycubed.config.features;

import com.teamresourceful.resourcefulconfig.api.annotations.Category;
import com.teamresourceful.resourcefulconfig.api.annotations.Comment;
import com.teamresourceful.resourcefulconfig.api.annotations.ConfigEntry;
import com.teamresourceful.resourcefulconfig.api.types.options.EntryType;

@Category("gui")
public final class GuiConfig {

    @ConfigEntry(
        id = "hideNullPanes",
        type = EntryType.BOOLEAN,
        translation = "config.skycubed.hideNullPanes"
    )
    @Comment("If true, the null panes in the GUI will be hidden.")
    public static boolean hideNullPanes = true;
}
