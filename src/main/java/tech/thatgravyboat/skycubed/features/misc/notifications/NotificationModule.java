package tech.thatgravyboat.skycubed.features.misc.notifications;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import org.intellij.lang.annotations.Language;
import tech.thatgravyboat.skycubed.api.events.ChatAddCallback;
import tech.thatgravyboat.skycubed.config.features.notifications.NotificationConfig;
import tech.thatgravyboat.skycubed.config.features.notifications.NotificationOptions;
import tech.thatgravyboat.skycubed.features.misc.SkyBlockModule;
import tech.thatgravyboat.skycubed.utils.RepoPattern;
import tech.thatgravyboat.skycubed.utils.TextUtils;

import java.util.List;

public class NotificationModule {

    private static final NotificationOptions TOAST_ONLY = new NotificationOptions(true, true);

    private static final List<NotificationType> CONFIGURED_NOTIFICATIONS = List.of(
        single(NotificationConfig.blocksInTheWay, NotificationIcon.WARNING, "notifications.blocksInTheWay", "there are blocks in the way!"),
        single(NotificationConfig.menuThrottled, NotificationIcon.ERROR, "notifications.menuThrottled", "this menu has been throttled! please slow down..."),
        unique(TOAST_ONLY, null, "notifications.hoppityTimeTower", "time tower! .*"),
        unique(TOAST_ONLY, null, "notifications.hoppityBarnCapacity", "your rabbit barn capacity has been increased to [0-9,]+ rabbits!")
    );

    public static void init() {
        ChatAddCallback.EVENT.register(NotificationModule::onNewMessage);
    }

    private static Component onNewMessage(Component message) {
        if (!SkyBlockModule.isSkyBlock()) return message;
        String text = TextUtils.toSimpleText(message);
        for (var type : CONFIGURED_NOTIFICATIONS) {
            if (type.shouldCheck() && type.pattern().matcher(text).find()) {
                if (type.options().showAsToast) {
                    NotificationToast.add(Minecraft.getInstance().getToasts(), type.icon(), type.key(), message, 2250);
                }
                return type.options().hideChatMessage ? null : message;
            }
        }
        return message;
    }

    private static NotificationType single(NotificationOptions options, NotificationIcon icon, String key, @Language("RegExp") String pattern) {
        return new NotificationType(options, icon, key, RepoPattern.get(key, pattern));
    }

    private static NotificationType unique(NotificationOptions options, NotificationIcon icon, String key, @Language("RegExp") String pattern) {
        return new NotificationType(options, icon, null, RepoPattern.get(key, pattern));
    }
}
