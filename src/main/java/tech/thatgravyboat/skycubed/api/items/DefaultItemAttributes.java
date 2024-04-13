package tech.thatgravyboat.skycubed.api.items;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.function.BiFunction;

public class DefaultItemAttributes {

    public static final SkyCubedItemAttribute<@Nullable String> ID = item -> getTag(item, "id", CompoundTag::getString);
    public static final SkyCubedItemAttribute<@Nullable Integer> DRILL_FUEL = item -> getTag(item, "drill_fuel", CompoundTag::getInt);
    public static final SkyCubedItemAttribute<@Nullable Integer> BLASTER_AMMO = item -> getTag(item, "ammo", CompoundTag::getInt);

    private static <T> T getTag(ItemStack stack, String key, BiFunction<CompoundTag, String, T> mapper) {
        if (!stack.hasTag()) return null;
        CompoundTag extraAttributes = stack.getTagElement("ExtraAttributes");
        if (extraAttributes == null) return null;
        if (!extraAttributes.contains(key)) return null;
        return mapper.apply(extraAttributes, key);
    }
}
