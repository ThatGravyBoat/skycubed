package tech.thatgravyboat.skycubed.config.features.notifications;

import com.teamresourceful.resourcefulconfig.api.annotations.Category;
import com.teamresourceful.resourcefulconfig.api.annotations.ConfigEntry;
import com.teamresourceful.resourcefulconfig.api.annotations.ConfigOption;
import com.teamresourceful.resourcefulconfig.api.types.options.EntryType;
import tech.thatgravyboat.skycubed.features.misc.notifications.NotificationIcon;

@Category("notifications")
public final class NotificationConfig {

    @ConfigEntry(id = "blocksInTheWay", type = EntryType.OBJECT)
    public static final NotificationOptions blocksInTheWay = new NotificationOptions(true, false, NotificationIcon.WARNING);

    @ConfigEntry(id = "menuThrottled", type = EntryType.OBJECT)
    public static final NotificationOptions menuThrottled = new NotificationOptions(true, true, NotificationIcon.ERROR);

    @ConfigOption.Separator("Hoppity")
    @ConfigEntry(id = "hoppityTimeTower", type = EntryType.OBJECT)
    public static final NotificationOptions hoppityTimeTower = new NotificationOptions(true, true, NotificationIcon.NONE);

    @ConfigEntry(id = "hoppityBarnCapacity", type = EntryType.OBJECT)
    public static final NotificationOptions hoppityBarnCapacity = new NotificationOptions(true, true, NotificationIcon.NONE);

    @ConfigEntry(id = "hoppityFoundRabbit", type = EntryType.OBJECT)
    public static final NotificationOptions hoppityFoundRabbit = new NotificationOptions(true, true, NotificationIcon.NONE);

    @ConfigEntry(id = "hoppityNewRabbit", type = EntryType.OBJECT)
    public static final NotificationOptions hoppityNewRabbit = new NotificationOptions(true, false, NotificationIcon.NONE);

    @ConfigEntry(id = "hoppityRabbitPromoted", type = EntryType.OBJECT)
    public static final NotificationOptions hoppityRabbitPromoted = new NotificationOptions(true, true, NotificationIcon.NONE);

    @ConfigEntry(id = "hoppityNotEnoughChocolate", type = EntryType.OBJECT)
    public static final NotificationOptions hoppityNotEnoughChocolate = new NotificationOptions(true, true, NotificationIcon.ERROR);

    @ConfigOption.Separator("Lobby Messages")
    @ConfigEntry(id = "transferredForAfk", type = EntryType.OBJECT)
    public static final NotificationOptions transferredForAfk = new NotificationOptions(true, false, NotificationIcon.NONE);

    @ConfigEntry(id = "sendingToServer", type = EntryType.OBJECT)
    public static final NotificationOptions sendingToServer = new NotificationOptions(true, true, NotificationIcon.NONE);

    @ConfigEntry(id = "lobbyIsFull", type = EntryType.OBJECT)
    public static final NotificationOptions lobbyIsFull = new NotificationOptions(true, true, NotificationIcon.NONE);

}
