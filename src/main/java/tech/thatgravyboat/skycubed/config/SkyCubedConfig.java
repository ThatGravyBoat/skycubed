package tech.thatgravyboat.skycubed.config;

import com.teamresourceful.resourcefulconfig.api.annotations.Config;
import com.teamresourceful.resourcefulconfig.api.annotations.ConfigInfo;
import tech.thatgravyboat.skycubed.config.features.DevConfig;
import tech.thatgravyboat.skycubed.config.features.GuiConfig;
import tech.thatgravyboat.skycubed.config.features.hud.HudConfig;
import tech.thatgravyboat.skycubed.config.features.notifications.NotificationConfig;

@Config(
        value = "skycubed",
        categories = {
                HudConfig.class,
                GuiConfig.class,
                NotificationConfig.class,
                DevConfig.class
        }
)
@ConfigInfo(
        title = "Sky³",
        description = "A Hypixel Skyblock mod for Fabric.",
        links = {
                @ConfigInfo.Link(
                        value = "https://thatgravyboat.tech",
                        text = "Website",
                        icon = "code-2"
                )
        }
)
@ConfigInfo.Gradient(value = "45deg", first = "#7F4DEE", second = "#E7797A")
public final class SkyCubedConfig {

}
