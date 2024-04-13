package tech.thatgravyboat.skycubed.api.items.attributes;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import tech.thatgravyboat.skycubed.api.items.ItemAttributes;
import tech.thatgravyboat.skycubed.utils.NumberUtils;
import tech.thatgravyboat.skycubed.utils.TextUtils;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;

public class LoreAttributes {

    public static List<String> getLore(ItemStack stack) {
        CompoundTag display = stack.getTagElement(ItemStack.TAG_DISPLAY);
        if (display == null) return List.of();
        try {
            return display.getList(ItemStack.TAG_LORE, 8).stream()
                    .map(Tag::getAsString)
                    .map(Component.Serializer::fromJson)
                    .filter(Objects::nonNull)
                    .map(TextUtils::toSimpleText)
                    .toList();
        }catch (Exception e) {
            return List.of();
        }
    }

    public static Integer getDrillMaxFuel(ItemStack stack) {
        return findInLore(stack, "fuel: ", true, line -> {
            String[] split = line.split("/");
            if (split.length != 2) return null;
            return NumberUtils.toInt(split[1]);
        });
    }

    public static Integer getBlasterAmmo(ItemStack stack) {
        return findInLore(stack, "snowballs: ", true, line -> {
            String[] split = line.split("/");
            if (split.length != 2) return null;
            return NumberUtils.toInt(split[1]);
        });
    }

    private static <T> T findInLore(ItemStack item, String prefix, boolean reverse, Function<String, T> mapper) {
        List<String> lore = item.getCubedAttribute(ItemAttributes.LORE);
        if (lore == null) return null;
        try {
            for (int i = reverse ? lore.size() - 1 : 0; reverse ? i >= 0 : i < lore.size(); i += reverse ? -1 : 1) {
                String line = lore.get(i);
                if (line.startsWith(prefix)) {
                    return mapper.apply(line.substring(prefix.length()));
                }
            }
        } catch (Exception ignored) {}
        return null;
    }
}
