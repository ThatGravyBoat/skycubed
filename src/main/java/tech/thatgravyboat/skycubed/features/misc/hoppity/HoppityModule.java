package tech.thatgravyboat.skycubed.features.misc.hoppity;

import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import tech.thatgravyboat.skycubed.api.events.ItemTooltipCallback;
import tech.thatgravyboat.skycubed.api.items.ItemAttributes;
import tech.thatgravyboat.skycubed.utils.RepoPattern;
import tech.thatgravyboat.skycubed.utils.TextUtils;

import java.util.List;
import java.util.regex.Pattern;

public class HoppityModule {

    private static final Pattern CHOCOLATE_FACTORY_PATTERN = RepoPattern.get("container.hoppity.chocolatefactory", "chocolate factory");

    public static void init() {
        ItemTooltipCallback.EVENT.register(HoppityModule::onTooltip);
    }

    private static void onTooltip(ItemStack stack, List<Component> tooltip) {
        if (!isChocolateFactory()) return;
        Float pricePerChocolate = stack.getCubedAttribute(ItemAttributes.HOPPITY_PRICE_PER_CHOCOLATE);
        if (pricePerChocolate == null) return;
        tooltip.add(Component.empty());
        tooltip.add(Component.literal("--------------------").withStyle(ChatFormatting.DARK_GRAY, ChatFormatting.BOLD, ChatFormatting.STRIKETHROUGH));
        tooltip.add(
            Component.empty()
                .append(Component.literal("Price per chocolate: ").withStyle(ChatFormatting.GRAY))
                .append(Component.literal(pricePerChocolate.toString()).withStyle(ChatFormatting.GOLD))
        );
    }

    public static boolean isChocolateFactory() {
        Minecraft mc = Minecraft.getInstance();
        if (mc.screen == null) return false;
        String title = TextUtils.toSimpleText(mc.screen.getTitle());
        return CHOCOLATE_FACTORY_PATTERN.matcher(title).find();
    }
}
