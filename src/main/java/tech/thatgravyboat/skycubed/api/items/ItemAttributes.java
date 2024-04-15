package tech.thatgravyboat.skycubed.api.items;

import it.unimi.dsi.fastutil.objects.ObjectIntPair;
import it.unimi.dsi.fastutil.objects.Reference2ObjectMap;
import it.unimi.dsi.fastutil.objects.Reference2ObjectOpenHashMap;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import tech.thatgravyboat.skycubed.api.items.attributes.LoreAttributes;
import tech.thatgravyboat.skycubed.api.items.attributes.ManaAttributes;
import tech.thatgravyboat.skycubed.api.items.attributes.MiscAttributes;
import tech.thatgravyboat.skycubed.features.misc.hoppity.HoppityCpsModule;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

public class ItemAttributes {

    private static final List<SkyCubedItemAttribute<?>> ATTRIBUTES = new ArrayList<>();

    public static final SkyCubedItemAttribute<String> ID = register(item -> getTag(item, "id", CompoundTag::getString));
    public static final SkyCubedItemAttribute<List<String>> LORE = register(LoreAttributes::getLore);

    public static final SkyCubedItemAttribute<Boolean> NULL_PANE = register(MiscAttributes::isNullPane);

    public static final SkyCubedItemAttribute<Integer> DRILL_FUEL = register(item -> getTag(item, "drill_fuel", CompoundTag::getInt));
    public static final SkyCubedItemAttribute<Integer> MAX_DRILL_FUEL = register(LoreAttributes::getDrillMaxFuel);
    public static final SkyCubedItemAttribute<Integer> BLASTER_AMMO = register(item -> getTag(item, "ammo", CompoundTag::getInt));
    public static final SkyCubedItemAttribute<Integer> MAX_BLASTER_AMMO = register(LoreAttributes::getBlasterAmmo);
    public static final SkyCubedItemAttribute<Integer> PICKONIMBUS_DURABILITY = register(item -> getTag(item, "pickonimbus_durability", CompoundTag::getInt));
    public static final SkyCubedItemAttribute<ObjectIntPair<String>> RIGHT_CLICK_ABILITY = register(ManaAttributes::getRightClickAbility);

    public static final SkyCubedItemAttribute<Float> HOPPITY_PRICE_PER_CHOCOLATE = register(HoppityCpsModule::pricePerChocolate);

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

    public static Reference2ObjectMap<SkyCubedItemAttribute<?>, Object> getAttributes(ItemStack stack) {
        Reference2ObjectMap<SkyCubedItemAttribute<?>, Object> attributes = new Reference2ObjectOpenHashMap<>();
        for (SkyCubedItemAttribute<?> attribute : ATTRIBUTES) {
            try {
                Object value = attribute.get(stack);
                if (value != null) {
                    attributes.put(attribute, value);
                }
            }catch (Exception e) {
                e.printStackTrace();
                System.out.println("Failed to get attribute " + attribute);
            }
        }
        return attributes;
    }
}
