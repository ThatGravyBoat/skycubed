package tech.thatgravyboat.skycubed.config.features.notifications;

import com.teamresourceful.resourcefulconfig.api.annotations.Comment;
import com.teamresourceful.resourcefulconfig.api.annotations.ConfigEntry;
import com.teamresourceful.resourcefulconfig.api.annotations.ConfigObject;
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

    public NotificationOptions(boolean hide, boolean showAsToast, NotificationIcon toastIcon) {
        this.hideChatMessage = hide;
        this.showAsToast = showAsToast;
        this.toastIcon = toastIcon;
    }

    public boolean shouldCheck() {
        return hideChatMessage || showAsToast;
    }
}
