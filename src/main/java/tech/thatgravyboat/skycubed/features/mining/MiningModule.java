package tech.thatgravyboat.skycubed.features.mining;

import it.unimi.dsi.fastutil.floats.FloatIntPair;
import net.minecraft.network.chat.Component;
import tech.thatgravyboat.skycubed.api.events.ActionBarCallback;
import tech.thatgravyboat.skycubed.api.events.ItemBarCallback;
import tech.thatgravyboat.skycubed.api.items.ItemAttributes;

import java.util.regex.Pattern;

public class MiningModule {

    private static final Pattern DRILL_FUEL_PATTERN = Pattern.compile("§.(?<drillfuel>[0-9,]+)/(?<maxdrillfuel>[0-9,]+k) Drill Fuel");

    public static void init() {
        ItemBarCallback.EVENT.register(item -> {
            Integer drillFuel = item.getCubedAttribute(ItemAttributes.DRILL_FUEL);
            Integer maxDrillFuel = item.getCubedAttribute(ItemAttributes.MAX_DRILL_FUEL);
            if (drillFuel != null && maxDrillFuel != null) {
                return FloatIntPair.of((float) drillFuel / (float) maxDrillFuel, 0x00AA00);
            }
            Integer pickonimbusDurability = item.getCubedAttribute(ItemAttributes.PICKONIMBUS_DURABILITY);
            if (pickonimbusDurability != null && pickonimbusDurability != 5000) {
                return FloatIntPair.of((float) pickonimbusDurability / 5000, 0x00FF00);
            }
            return null;
        });
        ActionBarCallback.EVENT.register((bar) -> {
            var matcher = DRILL_FUEL_PATTERN.matcher(bar.getString());
            if (matcher.find()) {
                return Component.literal(matcher.replaceAll("").trim());
            }
            return bar;
        });
    }
}
