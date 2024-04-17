package tech.thatgravyboat.skycubed.features.misc.skyblock;

import com.teamresourceful.resourcefullib.common.utils.Scheduling;
import net.minecraft.network.chat.Component;
import tech.thatgravyboat.hypixelapi.api.ApiRequester;
import tech.thatgravyboat.hypixelapi.api.events.ErrorCallback;
import tech.thatgravyboat.hypixelapi.api.events.LocationCallbackV1;
import tech.thatgravyboat.skycubed.api.events.ChangedDimensionsCallback;
import tech.thatgravyboat.skycubed.config.features.DevConfig;
import tech.thatgravyboat.skycubed.utils.TextUtils;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class SkyBlockModule {

    private static boolean isSkyBlock = false;
    private static IslandType island = IslandType.UNKNOWN;
    private static int requestTimer = 1;
    private static ScheduledFuture<?> locationRequest;

    public static void init() {
        LocationCallbackV1.EVENT.register((env, proxy, server, type, lobby, mode, map) -> {
            isSkyBlock = "SKYBLOCK".equals(type);
            if ("LIMBO".equals(map) && requestTimer < 10) {
                requestLocation(5 * requestTimer);
            }
            if (isSkyBlock) {
                island = IslandType.fromId(mode);
            }

            if (DevConfig.devMode) {
                System.out.printf("%s %s %s %s %s %s %s\n", env, proxy, server, type, lobby, mode, map);
            }
        });
        ErrorCallback.EVENT.register((error) -> TextUtils.sendToChat(Component.literal("Error: " + error)));
        ChangedDimensionsCallback.EVENT.register((info) -> {
            requestLocation(2);
            SkyBlockModule.requestTimer = 1;
        });
    }

    private static void requestLocation(int time) {
        if (locationRequest != null && !locationRequest.isDone()) return;
        requestTimer++;
        requestTimer = Math.min(10, requestTimer);
        locationRequest = Scheduling.schedule(ApiRequester::location, time, TimeUnit.SECONDS);
    }

    public static boolean isSkyBlock() {
        return isSkyBlock;
    }

    public static IslandType getIsland() {
        return island;
    }
}
