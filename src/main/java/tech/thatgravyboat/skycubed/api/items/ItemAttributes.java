package tech.thatgravyboat.skycubed.api.items;

import it.unimi.dsi.fastutil.objects.ObjectIntPair;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import tech.thatgravyboat.skycubed.utils.ItemUtils;

import java.util.*;
import java.util.function.BiFunction;

public class ItemAttributes {

    private static final List<SkyCubedItemAttribute<?>> ATTRIBUTES = new ArrayList<>();

    public static final SkyCubedItemAttribute<String> ID = register(item -> getTag(item, "id", CompoundTag::getString));
    public static final SkyCubedItemAttribute<Integer> DRILL_FUEL = register(item -> getTag(item, "drill_fuel", CompoundTag::getInt));
    public static final SkyCubedItemAttribute<Integer> BLASTER_AMMO = register(item -> getTag(item, "ammo", CompoundTag::getInt));
    public static final SkyCubedItemAttribute<ObjectIntPair<String>> RIGHT_CLICK_ABILITY = register(item -> {
        List<String> lore = ItemUtils.getLore(item);
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
    });

    private static <T> SkyCubedItemAttribute<T> register(SkyCubedItemAttribute<T> attribute) {
        ATTRIBUTES.add(attribute);
        return attribute;
    }

    private static <T> T getTag(ItemStack stack, String key, BiFunction<CompoundTag, String, T> mapper) {
        if (!stack.hasTag()) return null;
        CompoundTag extraAttributes = stack.getTagElement("ExtraAttributes");
        if (extraAttributes == null) return null;
        if (!extraAttributes.contains(key)) return null;
        return mapper.apply(extraAttributes, key);
    }

    public static Map<SkyCubedItemAttribute<?>, Object> getAttributes(ItemStack stack) {
        Map<SkyCubedItemAttribute<?>, Object> attributes = new HashMap<>();
        for (SkyCubedItemAttribute<?> attribute : ATTRIBUTES) {
            Object value = attribute.get(stack);
            if (value != null) {
                attributes.put(attribute, value);
            }
        }
        return attributes;
    }
}
