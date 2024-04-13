package tech.thatgravyboat.skycubed.features.jerry;

import it.unimi.dsi.fastutil.floats.FloatIntPair;
import net.minecraft.world.item.ItemStack;
import tech.thatgravyboat.skycubed.api.events.ItemBarCallback;
import tech.thatgravyboat.skycubed.api.items.DefaultItemAttributes;
import tech.thatgravyboat.skycubed.utils.ItemUtils;
import tech.thatgravyboat.skycubed.utils.NumberUtils;

import java.util.List;

public class BlasterModule {

    private static float getMaxAmmo(ItemStack stack) {
        List<String> lore = ItemUtils.getLore(stack);
        for (int i = lore.size() - 1; i >= 0; i--) {
            String line = lore.get(i);
            if (line.startsWith("snowballs: ")) {
                String[] split = line.split("/");
                if (split.length != 2) return Float.MAX_VALUE;
                return NumberUtils.toInt(split[1]);
            }
        }
        return Float.MAX_VALUE;
    }

    public static void init() {
        ItemBarCallback.EVENT.register(item -> {
            Integer ammo = item.getCubedAttribute(DefaultItemAttributes.BLASTER_AMMO);
            if (ammo == null) return null;
            float fuel = ammo / getMaxAmmo(item);
            return FloatIntPair.of(fuel, 0xFEFEFE);
        });
    }
}
