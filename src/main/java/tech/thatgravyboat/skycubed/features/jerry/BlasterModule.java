package tech.thatgravyboat.skycubed.features.jerry;

import it.unimi.dsi.fastutil.floats.FloatIntPair;
import tech.thatgravyboat.skycubed.api.events.ItemBarCallback;
import tech.thatgravyboat.skycubed.api.items.ItemAttributes;

public class BlasterModule {

    public static void init() {
        ItemBarCallback.EVENT.register(item -> {
            Integer ammo = item.getCubedAttribute(ItemAttributes.BLASTER_AMMO);
            Integer maxAmmo = item.getCubedAttribute(ItemAttributes.MAX_BLASTER_AMMO);
            if (ammo == null || maxAmmo == null) return null;
            return FloatIntPair.of((float) ammo / (float) maxAmmo, 0xFEFEFE);
        });
    }
}
