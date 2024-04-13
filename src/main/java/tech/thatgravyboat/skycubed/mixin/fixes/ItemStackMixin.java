package tech.thatgravyboat.skycubed.mixin.fixes;

import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import tech.thatgravyboat.skycubed.api.items.SkyCubedItem;
import tech.thatgravyboat.skycubed.api.items.SkyCubedItemAttribute;

@Mixin(ItemStack.class)
public class ItemStackMixin implements SkyCubedItem {

    @Override
    public <T> T getCubedAttribute(SkyCubedItemAttribute<T> attribute) {
        return attribute.get((ItemStack) (Object) this);
    }
}
