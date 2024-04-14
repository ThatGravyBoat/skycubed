package tech.thatgravyboat.skycubed.mixin.fixes;

import it.unimi.dsi.fastutil.objects.Reference2ObjectMap;
import it.unimi.dsi.fastutil.objects.Reference2ObjectOpenHashMap;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import tech.thatgravyboat.skycubed.api.items.ItemAttributes;
import tech.thatgravyboat.skycubed.api.items.SkyCubedItemAttribute;
import tech.thatgravyboat.skycubed.api.items.SkyCubedItemHolder;

@Mixin(ItemStack.class)
@SuppressWarnings({"unchecked", "AddedMixinMembersNamePattern"})
public class ItemStackMixin implements SkyCubedItemHolder {

    @Unique
    private Reference2ObjectMap<SkyCubedItemAttribute<?>, Object> attributes = new Reference2ObjectOpenHashMap<>();

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
