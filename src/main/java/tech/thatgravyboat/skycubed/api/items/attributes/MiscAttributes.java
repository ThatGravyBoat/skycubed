package tech.thatgravyboat.skycubed.api.items.attributes;

import it.unimi.dsi.fastutil.objects.ReferenceOpenHashSet;
import it.unimi.dsi.fastutil.objects.ReferenceSet;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import tech.thatgravyboat.skycubed.api.items.ItemAttributes;

public class MiscAttributes {

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

    public static Boolean isNullPane(ItemStack item) {
        if (!GLASS_PANES.contains(item.getItem())) return false;
        if (item.getCubedAttribute(ItemAttributes.ID) != null) return false;
        String name = ChatFormatting.stripFormatting(item.getHoverName().getString());
        return name != null && name.isBlank();
    }
}
