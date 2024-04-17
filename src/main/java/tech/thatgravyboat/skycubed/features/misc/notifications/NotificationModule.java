package tech.thatgravyboat.skycubed.features.misc.notifications;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import org.intellij.lang.annotations.Language;
import tech.thatgravyboat.skycubed.api.events.ChatAddCallback;
import tech.thatgravyboat.skycubed.config.features.notifications.NotificationConfig;
import tech.thatgravyboat.skycubed.config.features.notifications.NotificationOptions;
import tech.thatgravyboat.skycubed.features.misc.skyblock.SkyBlockModule;
import tech.thatgravyboat.skycubed.utils.RepoPattern;
import tech.thatgravyboat.skycubed.utils.TextUtils;

import java.util.List;

public class NotificationModule {

    private static final List<NotificationType> CONFIGURED_NOTIFICATIONS = List.of(
        single(NotificationConfig.blocksInTheWay, "notifications.blocksInTheWay", "there are blocks in the way!"),
        single(NotificationConfig.menuThrottled, "notifications.menuThrottled", "this menu has been throttled! please slow down..."),
        single(NotificationConfig.sendingToServer, "notifications.sendingToServer", "(sending to server .*|warping\\.{3})"),
        single(NotificationConfig.lobbyIsFull, "notifications.lobbyIsFull", "that lobby is currently full! try again later."),
        single(NotificationConfig.transferredForAfk, "notifications.transferredForAfk", "you are being transferred to .* for being afk!"),

        unique(NotificationConfig.hoppityTimeTower, "notifications.hoppityTimeTower", "time tower! .*"),
        unique(NotificationConfig.hoppityBarnCapacity, "notifications.hoppityBarnCapacity", "your rabbit barn capacity has been increased to [0-9,]+ rabbits!"),
        unique(NotificationConfig.hoppityFoundRabbit, "notifications.hoppityFoundRabbit", "hoppity's hunt you found .*"),
        unique(NotificationConfig.hoppityNewRabbit, "notifications.hoppityNewRabbit", "new rabbit! .*"),
        single(NotificationConfig.hoppityRabbitPromoted, "notifications.hoppityRabbitPromoted", "rabbit .* has been promoted to .*"),
        single(NotificationConfig.hoppityNotEnoughChocolate, "notifications.hoppityNotEnoughChocolate", "you don't have enough chocolate!")
    );

    public static void init() {
        ChatAddCallback.EVENT.register(NotificationModule::onNewMessage);
    }

    private static Component onNewMessage(Component message) {
        if (!SkyBlockModule.isSkyBlock()) return message;
        String text = TextUtils.toSimpleText(message);
        for (var type : CONFIGURED_NOTIFICATIONS) {
            if (type.shouldCheck() && type.pattern().matcher(text).matches()) {
                NotificationOptions options = type.options();
                if (options.showAsToast) {
                    NotificationToast.add(Minecraft.getInstance().getToasts(), options.toastIcon, type.key(), message, options.toastDuration);
                }
                return options.hideChatMessage ? null : message;
            }
        }
        return message;
    }

    private static NotificationType single(NotificationOptions options, String key, @Language("RegExp") String pattern) {
        return new NotificationType(options, key, RepoPattern.get(key, pattern));
    }

    private static NotificationType unique(NotificationOptions options, String key, @Language("RegExp") String pattern) {
        return new NotificationType(options, null, RepoPattern.get(key, pattern));
    }
}
