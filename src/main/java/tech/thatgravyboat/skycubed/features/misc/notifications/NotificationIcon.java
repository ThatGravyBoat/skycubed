package tech.thatgravyboat.skycubed.features.misc.notifications;

import net.minecraft.resources.ResourceLocation;
import tech.thatgravyboat.skycubed.SkyCubed;

public enum NotificationIcon {
    WARNING(new ResourceLocation(SkyCubed.MOD_ID, "warning")),
    INFO(new ResourceLocation(SkyCubed.MOD_ID, "info")),
    ERROR(new ResourceLocation(SkyCubed.MOD_ID, "error"));

    private final ResourceLocation location;

    NotificationIcon(ResourceLocation location) {
        this.location = location;
    }

    public ResourceLocation location() {
        return location;
    }
}
