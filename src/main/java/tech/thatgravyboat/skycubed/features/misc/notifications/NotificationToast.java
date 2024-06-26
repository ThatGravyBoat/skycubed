package tech.thatgravyboat.skycubed.features.misc.notifications;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.toasts.Toast;
import net.minecraft.client.gui.components.toasts.ToastComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FormattedCharSequence;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tech.thatgravyboat.skycubed.SkyCubed;

import java.util.List;
import java.util.Objects;

public class NotificationToast implements Toast {

    private static final ResourceLocation BACKGROUND = new ResourceLocation(SkyCubed.MOD_ID, "notification");

    private final NotificationIcon icon;
    private List<FormattedCharSequence> text;
    private final String id;
    private final long displayTime;
    private long removalTime = -1;
    private boolean replaced;

    public NotificationToast(NotificationIcon icon, List<FormattedCharSequence> text, String id, long displayTime) {
        this.icon = icon;
        this.text = text;
        this.id = id;
        this.displayTime = displayTime;
    }

    @Override
    public int width() {
        Font font = Minecraft.getInstance().font;
        return Math.min(200, text.stream().mapToInt(font::width).max().orElse(200)) + 8 + (this.icon != NotificationIcon.NONE ? 40 : 0);
    }

    @Override
    public int height() {
        return Math.max(7 + this.text.size() * 10, 32);
    }

    @Override
    public @NotNull Object getToken() {
        return Objects.requireNonNullElse(this.id, NO_TOKEN);
    }

    @Override
    public @NotNull Toast.Visibility render(GuiGraphics graphics, ToastComponent toastComponent, long l) {
        if (this.removalTime == -1 || this.replaced) {
            this.removalTime = System.currentTimeMillis() + this.displayTime;
            this.replaced = false;
        }
        Font font = toastComponent.getMinecraft().font;
        graphics.blitSprite(BACKGROUND, 0, 0, this.width(), this.height());

        int y = 1 + this.height() / 2 - this.text.size() * 5;
        int x = 4 + (this.icon != NotificationIcon.NONE ? 32 : 0);

        if (this.icon != NotificationIcon.NONE) {
            graphics.blitSprite(this.icon.location(), 2, 2, 28, 28);
        }

        for (int line = 0; line < this.text.size(); ++line) {
            graphics.drawString(font, this.text.get(line), x, y + line * 10, -1, false);
        }

        return this.removalTime <= System.currentTimeMillis() ? Toast.Visibility.HIDE : Toast.Visibility.SHOW;
    }

    public void replace(List<FormattedCharSequence> text) {
        this.text = text;
        this.replaced = true;
    }

    public static void add(ToastComponent component, NotificationIcon icon, @Nullable String id, Component text, long time) {
        Font font = component.getMinecraft().font;
        List<FormattedCharSequence> list = font.split(text, icon != NotificationIcon.NONE ? 152 : 192);
        if (id != null) {
            NotificationToast existing = component.getToast(NotificationToast.class, id);
            if (existing != null) {
                existing.replace(list);
                return;
            }
        }
        component.addToast(new NotificationToast(icon, list, id, time));
    }

}