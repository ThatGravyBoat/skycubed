package tech.thatgravyboat.skycubed.api.items.attributes;

import it.unimi.dsi.fastutil.objects.ObjectIntPair;
import net.minecraft.world.item.ItemStack;
import tech.thatgravyboat.skycubed.api.items.ItemAttributes;
import tech.thatgravyboat.skycubed.utils.RegexMatcher;
import tech.thatgravyboat.skycubed.utils.RepoPattern;

import java.util.List;
import java.util.regex.Pattern;

public class ManaAttributes {

    private static final Pattern MANA_COST_PATTERN = RepoPattern.get( "item.manacost", "mana cost: (?<cost>[0-9,]+).*");

    public static ObjectIntPair<String> getRightClickAbility(ItemStack item) {
        List<String> lore = item.getCubedAttribute(ItemAttributes.LORE);
        if (lore == null) return null;
        int manaCost = -1;
        for (int i = lore.size() - 1; i >= 0; i--) {
            String line = lore.get(i);
            if (line.startsWith("mana cost: ")) {
                RegexMatcher matcher = RegexMatcher.of(MANA_COST_PATTERN, line);
                manaCost = matcher.integer("cost", 0);
            } else if (line.startsWith("ability: ") && line.endsWith(" right click")) {
                if (manaCost == -1) return null;
                return ObjectIntPair.of(line.substring(9, line.length() - 13), manaCost);
            }
        }
        return null;
    }
}
