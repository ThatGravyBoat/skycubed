package tech.thatgravyboat.skycubed.features.misc.hoppity;

import net.minecraft.world.item.ItemStack;
import tech.thatgravyboat.skycubed.api.items.ItemAttributes;
import tech.thatgravyboat.skycubed.utils.RegexMatcher;
import tech.thatgravyboat.skycubed.utils.RepoPattern;

import java.util.List;
import java.util.regex.Pattern;

public class HoppityCpsModule {

    private static final Pattern CURRENT_RATE = RepoPattern.get("lore.hoppity.current_rate", "\\+(?<rate>[0-9,]+) chocolate per second!");
    private static final Pattern NEW_RATE = RepoPattern.get("lore.hoppity.new_rate", " {2}\\+(?<rate>[0-9,]+) chocolate per second");
    private static final Pattern COST = RepoPattern.get("lore.hoppity.cost", "cost");
    private static final Pattern COST_CHOCOLATE = RepoPattern.get("lore.hoppity.cost_chocolate", "(?<chocolate>[0-9,]+) chocolate");

    public static Float pricePerChocolate(ItemStack stack) {
        if (!HoppityModule.isChocolateFactory()) return null;
        List<String> lore = stack.getCubedAttribute(ItemAttributes.LORE);
        if (lore == null) return null;
        int rate = -1;
        int newRate = -1;
        int cost = -1;
        boolean foundCost = false;
        for (String line : lore) {
            RegexMatcher currentRateMatcher = new RegexMatcher(CURRENT_RATE, line);
            if (currentRateMatcher.found() && rate == -1) {
                rate = currentRateMatcher.integer("rate", -1);
            }

            RegexMatcher newRateMatcher = new RegexMatcher(NEW_RATE, line);
            if (newRateMatcher.found() && newRate == -1) {
                newRate = newRateMatcher.integer("rate", -1);
            }

            RegexMatcher costMatcher = new RegexMatcher(COST_CHOCOLATE, line);
            if (costMatcher.found() && foundCost) {
                cost = costMatcher.integer("chocolate", -1);
                break;
            }

            foundCost = new RegexMatcher(COST, line).found();
        }
        if (rate == -1 || newRate == -1 || cost == -1) return null;
        float diff = newRate - rate;
        return cost / diff;
    }
}
