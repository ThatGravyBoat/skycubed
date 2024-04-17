package tech.thatgravyboat.skycubed.features.hud;

import com.mojang.datafixers.util.Pair;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.FormattedText;
import tech.thatgravyboat.skycubed.api.events.ChangedDimensionsCallback;
import tech.thatgravyboat.skycubed.api.events.ChatAddCallback;
import tech.thatgravyboat.skycubed.api.events.HudRenderCallback;
import tech.thatgravyboat.skycubed.config.features.hud.DialogueBoxConfig;
import tech.thatgravyboat.skycubed.features.misc.skyblock.SkyBlockModule;
import tech.thatgravyboat.skycubed.utils.RepoPattern;
import tech.thatgravyboat.skycubed.utils.TextUtils;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.regex.Pattern;

public class NpcModule {

    private static final Pattern PATTERN = RepoPattern.get("npc.regex", "\\[npc] (?<name>[a-z0-9_ ]+): (?<content>.*)", Pattern.CASE_INSENSITIVE);

    private static final int PADDING = 4;

    private static final Queue<Pair<String, String>> MESSAGES = new ArrayDeque<>();
    private static Pair<String, String> currentMessage;
    private static long lastMessage = 0;

    public static void init() {
        HudRenderCallback.EVENT.register(NpcModule::render);
        ChangedDimensionsCallback.EVENT.register(ignored -> hide());
        ChatAddCallback.EVENT.register(NpcModule::onMessage);
    }

    private static Component onMessage(Component message) {
        if (!SkyBlockModule.isSkyBlock()) return message;
        if (!DialogueBoxConfig.enabled) return message;
        var match = PATTERN.matcher(TextUtils.toText(message));
        if (match.matches()) {
            MESSAGES.add(Pair.of(match.group("name"), match.group("content")));
            return null;
        }
        return message;
    }

    private static void hide() {
        MESSAGES.clear();
    }

    private static void render(GuiGraphics graphics, float partialTicks) {
        if (!SkyBlockModule.isSkyBlock()) return;
        if (currentMessage == null && MESSAGES.isEmpty()) return;
        if (!DialogueBoxConfig.enabled) return;
        Font font = Minecraft.getInstance().font;
        if (System.currentTimeMillis() - lastMessage > DialogueBoxConfig.time || currentMessage == null) {
            currentMessage = MESSAGES.poll();
            lastMessage = System.currentTimeMillis();
        }
        if (currentMessage == null) return;

        var content = currentMessage.getSecond();
        var name = currentMessage.getFirst();

        var lines = font.split(FormattedText.of(content), DialogueBoxConfig.maxWidth - PADDING * 2);
        int height = (lines.size() + 1) * font.lineHeight + PADDING * 3;
        int contentWidth = lines.stream().mapToInt(font::width).max().orElse(0);
        int width = Math.max(contentWidth, font.width(name)) + PADDING * 2;

        int x = (graphics.guiWidth() - width) / 2;
        int y = graphics.guiHeight() - height - 75;

        graphics.fill(x + 1, y, x + width - 1, y + 1, 0x80000000);
        graphics.fill(x, y + 1, x + width, y + height - 1, 0x80000000);
        graphics.fill(x + 1, y + height - 1, x + width - 1, y + height, 0x80000000);

        graphics.drawString(font, name, x + PADDING, y + PADDING, 0xffff55, false);
        for (int i = 0; i < lines.size(); i++) {
            graphics.drawString(font, lines.get(i), x + PADDING, y + (font.lineHeight + PADDING * 2) + font.lineHeight * i, 0xFFFFFF, false);
        }
    }
}
