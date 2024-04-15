package tech.thatgravyboat.skycubed.config.features.notifications;

import com.teamresourceful.resourcefulconfig.api.annotations.Category;
import com.teamresourceful.resourcefulconfig.api.annotations.ConfigEntry;
import com.teamresourceful.resourcefulconfig.api.types.options.EntryType;

@Category("notifications")
public final class NotificationConfig {

    @ConfigEntry(id = "blocksInTheWay", type = EntryType.OBJECT)
    public static final NotificationOptions blocksInTheWay = new NotificationOptions(true, false);

    @ConfigEntry(id = "menuThrottled", type = EntryType.OBJECT)
    public static final NotificationOptions menuThrottled = new NotificationOptions(true, true);
}
