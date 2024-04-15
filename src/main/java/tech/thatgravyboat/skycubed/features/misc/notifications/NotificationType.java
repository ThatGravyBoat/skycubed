package tech.thatgravyboat.skycubed.features.misc.notifications;

import tech.thatgravyboat.skycubed.config.features.notifications.NotificationOptions;

import java.util.regex.Pattern;

public record NotificationType(NotificationOptions options, NotificationIcon icon, String key, Pattern pattern) {

    public boolean shouldCheck() {
        return options.shouldCheck();
    }
}
