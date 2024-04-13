package tech.thatgravyboat.skycubed.mixin.fixes;

import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import tech.thatgravyboat.skycubed.api.items.SkyCubedItemAttribute;
import tech.thatgravyboat.skycubed.api.items.SkyCubedItemHolder;

@Mixin(Slot.class)
public abstract class SlotMixin implements SkyCubedItemHolder {

    @Shadow public abstract boolean hasItem();

    @Shadow public abstract ItemStack getItem();

    @Override
    public <T> @Nullable T getCubedAttribute(SkyCubedItemAttribute<T> attribute) {
        return hasItem() ? getItem().getCubedAttribute(attribute) : null;
    }

    @Override
    public boolean hasCubedAttribute(SkyCubedItemAttribute<Boolean> attribute) {
        return hasItem() && getItem().hasCubedAttribute(attribute);
    }
}
