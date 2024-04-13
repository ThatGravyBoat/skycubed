package tech.thatgravyboat.skycubed.api.items;

import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

public interface SkyCubedItemAttribute<T> {

    @Nullable
    T get(ItemStack stack);
}
