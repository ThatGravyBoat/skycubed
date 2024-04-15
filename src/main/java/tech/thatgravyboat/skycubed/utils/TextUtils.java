package tech.thatgravyboat.skycubed.utils;

import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;

import java.util.Locale;

public class TextUtils {

    public static String toSimpleText(Component component) {
        return ChatFormatting.stripFormatting(component.getString().stripTrailing().toLowerCase(Locale.ROOT));
    }

    public static void sendToChat(Component component) {
        Minecraft.getInstance().gui.getChat().addMessage(component);
    }
}
