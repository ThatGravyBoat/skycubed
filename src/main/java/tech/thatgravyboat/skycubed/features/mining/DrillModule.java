package tech.thatgravyboat.skycubed.features.mining;

import it.unimi.dsi.fastutil.floats.FloatIntPair;
import tech.thatgravyboat.skycubed.api.events.ItemBarCallback;
import tech.thatgravyboat.skycubed.api.items.ItemAttributes;

public class DrillModule {

    public static void init() {
        ItemBarCallback.EVENT.register(item -> {
            Integer drillFuel = item.getCubedAttribute(ItemAttributes.DRILL_FUEL);
            Integer maxDrillFuel = item.getCubedAttribute(ItemAttributes.MAX_DRILL_FUEL);
            if (drillFuel == null || maxDrillFuel == null) return null;
            return FloatIntPair.of((float) drillFuel / (float) maxDrillFuel, 0x00FF00);
        });
    }
}
