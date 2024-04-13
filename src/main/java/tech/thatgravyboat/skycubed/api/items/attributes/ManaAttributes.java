package tech.thatgravyboat.skycubed.api.items.attributes;

import it.unimi.dsi.fastutil.objects.ObjectIntPair;
import net.minecraft.world.item.ItemStack;
import tech.thatgravyboat.skycubed.api.items.ItemAttributes;

import java.util.List;

public class ManaAttributes {

    public static ObjectIntPair<String> getRightClickAbility(ItemStack item) {
        List<String> lore = item.getCubedAttribute(ItemAttributes.LORE);
        if (lore == null) return null;
        int manaCost = -1;
        for (int i = lore.size() - 1; i >= 0; i--) {
            String line = lore.get(i);
            if (line.startsWith("mana cost: ")) {
                manaCost = Integer.parseInt(line.substring(10).trim());
            } else if (line.startsWith("ability: ") && line.endsWith(" right click")) {
                if (manaCost == -1) return null;
                return ObjectIntPair.of(line.substring(9, line.length() - 13), manaCost);
            }
        }
        return null;
    }
}
