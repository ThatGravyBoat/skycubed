package tech.thatgravyboat.skycubed.utils;

import it.unimi.dsi.fastutil.objects.ReferenceOpenHashSet;
import it.unimi.dsi.fastutil.objects.ReferenceSet;
import net.minecraft.Util;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import tech.thatgravyboat.skycubed.api.items.ItemAttributes;

import java.util.List;
import java.util.Objects;

public class ItemUtils {

    private static final ReferenceSet<Item> GLASS_PANES = Util.make(new ReferenceOpenHashSet<>(), set -> {
        set.add(Items.BLACK_STAINED_GLASS_PANE);
        set.add(Items.BLUE_STAINED_GLASS_PANE);
        set.add(Items.BROWN_STAINED_GLASS_PANE);
        set.add(Items.CYAN_STAINED_GLASS_PANE);
        set.add(Items.GRAY_STAINED_GLASS_PANE);
        set.add(Items.GREEN_STAINED_GLASS_PANE);
        set.add(Items.LIGHT_BLUE_STAINED_GLASS_PANE);
        set.add(Items.LIGHT_GRAY_STAINED_GLASS_PANE);
        set.add(Items.LIME_STAINED_GLASS_PANE);
        set.add(Items.MAGENTA_STAINED_GLASS_PANE);
        set.add(Items.ORANGE_STAINED_GLASS_PANE);
        set.add(Items.PINK_STAINED_GLASS_PANE);
        set.add(Items.PURPLE_STAINED_GLASS_PANE);
        set.add(Items.RED_STAINED_GLASS_PANE);
        set.add(Items.WHITE_STAINED_GLASS_PANE);
        set.add(Items.YELLOW_STAINED_GLASS_PANE);
    });

    public static boolean isNullPane(ItemStack stack) {
        if (!GLASS_PANES.contains(stack.getItem())) return false;
        if (stack.getCubedAttribute(ItemAttributes.ID) != null) return false;
        List<String> lore = getLore(stack);
        if (lore.isEmpty()) return true;
        return lore.stream().allMatch(String::isBlank);
    }

    public static List<String> getLore(ItemStack stack) {
        CompoundTag display = stack.getTagElement(ItemStack.TAG_DISPLAY);
        if (display == null) return List.of();
        try {
            return display.getList(ItemStack.TAG_LORE, 8).stream()
                    .map(Tag::getAsString)
                    .map(Component.Serializer::fromJson)
                    .filter(Objects::nonNull)
                    .map(TextUtils::toSimpleText)
                    .toList();
        }catch (Exception e) {
            return List.of();
        }
    }
}
