package tech.thatgravyboat.skycubed.config.features.hud;

import com.teamresourceful.resourcefulconfig.api.annotations.Category;
import com.teamresourceful.resourcefulconfig.api.annotations.Comment;
import com.teamresourceful.resourcefulconfig.api.annotations.ConfigEntry;
import com.teamresourceful.resourcefulconfig.api.annotations.ConfigOption;
import com.teamresourceful.resourcefulconfig.api.types.options.EntryType;

@Category("dialogueBox")
public final class DialogueBoxConfig {

    @ConfigEntry(
            id = "enabled",
            type = EntryType.BOOLEAN,
            translation = "config.skycubed.dialogueBox.enabled"
    )
    @Comment("If true, the dialogue box will be enabled.")
    public static boolean enabled = true;

    @ConfigEntry(
            id = "time",
            type = EntryType.INTEGER,
            translation = "config.skycubed.dialogueBox.time"
    )
    @Comment("The time in milliseconds that the dialogue box will be displayed.")
    @ConfigOption.Range(min = 1000, max = 10000)
    @ConfigOption.Slider
    public static int time = 2500;

    @ConfigEntry(
            id = "maxWidth",
            type = EntryType.INTEGER,
            translation = "config.skycubed.dialogueBox.maxWidth"
    )
    @Comment("The maximum width of the dialogue box.")
    @ConfigOption.Range(min = 196, max = Short.MAX_VALUE)
    public static int maxWidth = 196;
}
