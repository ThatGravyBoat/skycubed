package tech.thatgravyboat.skycubed.config.features.notifications;

import com.teamresourceful.resourcefulconfig.api.annotations.Comment;
import com.teamresourceful.resourcefulconfig.api.annotations.ConfigEntry;
import com.teamresourceful.resourcefulconfig.api.annotations.ConfigObject;
import com.teamresourceful.resourcefulconfig.api.types.options.EntryType;

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

    public NotificationOptions(boolean hide, boolean showAsToast) {
        this.hideChatMessage = hide;
        this.showAsToast = showAsToast;
    }

    public boolean shouldCheck() {
        return hideChatMessage || showAsToast;
    }
}
