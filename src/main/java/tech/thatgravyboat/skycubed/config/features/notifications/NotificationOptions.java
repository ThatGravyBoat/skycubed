package tech.thatgravyboat.skycubed.config.features.notifications;

import com.teamresourceful.resourcefulconfig.api.annotations.Comment;
import com.teamresourceful.resourcefulconfig.api.annotations.ConfigEntry;
import com.teamresourceful.resourcefulconfig.api.annotations.ConfigObject;
import com.teamresourceful.resourcefulconfig.api.annotations.ConfigOption;
import com.teamresourceful.resourcefulconfig.api.types.options.EntryType;
import tech.thatgravyboat.skycubed.features.misc.notifications.NotificationIcon;

@ConfigObject
public class NotificationOptions {

    @ConfigEntry(
        id = "hideChatMessage",
        type = EntryType.BOOLEAN
    )
    @Comment("Whether to hide notifications.")
    public boolean hideChatMessage;

    @ConfigOption.Separator(value = "Toast Options", description = "Options for toast notifications.")
    @ConfigEntry(
        id = "showAsToast",
        type = EntryType.BOOLEAN
    )
    @Comment("Whether to show notifications as a toast.")
    public boolean showAsToast;

    @ConfigEntry(
            id = "toastIcon",
            type = EntryType.ENUM
    )
    @Comment("The icon to use for the toast.")
    public NotificationIcon toastIcon;

    @ConfigEntry(
        id = "toastDuration",
        type = EntryType.INTEGER
    )
    @Comment("The duration of the toast in milliseconds.")
    @ConfigOption.Range(min = 0, max = 30000)
    @ConfigOption.Slider
    public int toastDuration;

    public NotificationOptions(boolean hide, boolean showAsToast, NotificationIcon toastIcon, int toastDuration) {
        this.hideChatMessage = hide;
        this.showAsToast = showAsToast;
        this.toastIcon = toastIcon;
        this.toastDuration = toastDuration;
    }

    public NotificationOptions(boolean hide, boolean showAsToast, NotificationIcon toastIcon) {
        this(hide, showAsToast, toastIcon, 2250);
    }

    public boolean shouldCheck() {
        return hideChatMessage || showAsToast;
    }
}
