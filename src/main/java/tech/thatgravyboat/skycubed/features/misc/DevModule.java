package tech.thatgravyboat.skycubed.features.misc;

import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.Optionull;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import tech.thatgravyboat.skycubed.config.features.DevConfig;
import tech.thatgravyboat.skycubed.mixin.accessor.AbstractContainerScreenAccessor;
import tech.thatgravyboat.skycubed.utils.DebouncedKeyPress;

public class DevModule {

    private static final DebouncedKeyPress ALT_KEY = new DebouncedKeyPress(InputConstants.KEY_LALT);

    public static void init() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player == null) return;
            if (!DevConfig.devMode) return;
            if (!ALT_KEY.isDown()) return;
            CompoundTag tag = null;
            if (client.screen instanceof AbstractContainerScreenAccessor accessor) {
                ItemStack stack = Optionull.map(accessor.getHoveredSlot(), Slot::getItem);
                if (stack != null && stack.hasTag()) {
                    tag = stack.getTag();
                    if (tag.contains("ExtraAttributes") && !tag.getCompound("ExtraAttributes").isEmpty()) {
                        tag = tag.getCompound("ExtraAttributes");
                    }
                }
            } else if (client.crosshairPickEntity != null) {
                tag = client.crosshairPickEntity.saveWithoutId(new CompoundTag());
            }
            if (tag == null) return;
            System.out.println("\n" + NbtUtils.prettyPrint(tag, true));
        });
    }
}
