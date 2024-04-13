package tech.thatgravyboat.skycubed.mixin.fixes;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import tech.thatgravyboat.skycubed.api.items.ItemAttributes;
import tech.thatgravyboat.skycubed.api.items.SkyCubedItem;
import tech.thatgravyboat.skycubed.api.items.SkyCubedItemAttribute;

import java.util.HashMap;
import java.util.Map;

@Mixin(ItemStack.class)
@SuppressWarnings({"unchecked", "AddedMixinMembersNamePattern"})
public class ItemStackMixin implements SkyCubedItem {

    @Unique
    private Map<SkyCubedItemAttribute<?>, Object> attributes = new HashMap<>();

    @Inject(method = "setTag", at = @At("TAIL"))
    private void setTag(CompoundTag compoundTag, CallbackInfo ci) {
        this.attributes = ItemAttributes.getAttributes((ItemStack) (Object) this);
    }

    @Override
    public <T> T getCubedAttribute(SkyCubedItemAttribute<T> attribute) {
        if (this.attributes.containsKey(attribute)) {
            return (T) this.attributes.get(attribute);
        }
        try {
            return attribute.get((ItemStack) (Object) this);
        } catch (Exception e){
            return null;
        }
    }

}
