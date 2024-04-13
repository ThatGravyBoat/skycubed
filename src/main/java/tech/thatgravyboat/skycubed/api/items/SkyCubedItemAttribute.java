package tech.thatgravyboat.skycubed.api.items;

import net.minecraft.world.item.ItemStack;

public interface SkyCubedItemAttribute<T> {

    T get(ItemStack stack);
}
