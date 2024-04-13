package tech.thatgravyboat.skycubed.features.hud;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import tech.thatgravyboat.skycubed.SkyCubed;
import tech.thatgravyboat.skycubed.api.events.HudRenderCallback;
import tech.thatgravyboat.skycubed.features.misc.SkyBlockModule;
import tech.thatgravyboat.skycubed.features.stats.PlayerStatsModule;

public class HudBarModule {

    private static final ResourceLocation TEXTURE = new ResourceLocation(SkyCubed.MOD_ID, "textures/gui/bars.png");
    private static float lastManaPercentage = 0f;
    private static float lastHealthPercentage = 0f;

    public static void init() {
        HudRenderCallback.EVENT.register(HudBarModule::render);
    }

    private static void render(GuiGraphics graphics, float partialTicks) {
        if (!SkyBlockModule.isSkyBlock()) return;
        Minecraft mc = Minecraft.getInstance();
        int width = mc.getWindow().getGuiScaledWidth();
        int height = mc.getWindow().getGuiScaledHeight();
        int x = width / 2 - 91;
        int y = height - 37;

        float healthPercentage = Mth.lerp(partialTicks, lastHealthPercentage, PlayerStatsModule.healthPercent());
        float manaPercentage = Mth.lerp(partialTicks, lastManaPercentage, PlayerStatsModule.manaPercent());
        float manaItemPercentage = PlayerStatsModule.itemManaPercent();

        boolean hasAbsorption = healthPercentage > 1f;

        graphics.blit(TEXTURE, x, y, hasAbsorption ? 73 : 0, 10, 73, 5);
        graphics.blit(TEXTURE, x, y, hasAbsorption ? 73 : 0, 15, Mth.lerpInt(healthPercentage, 0, 73), 5);

        graphics.drawString(mc.font, PlayerStatsModule.healthString(), x + 1, y - 10, 0xff5555);

        x = width / 2 + 18;
        graphics.blit(TEXTURE, x, y, 0, 0, 73, 5);
        graphics.blit(TEXTURE, x, y, 73, 0, Mth.lerpInt(manaItemPercentage, 0, 73), 5);
        graphics.blit(TEXTURE, x, y, 0, 5, Mth.lerpInt(manaPercentage, 0, 73), 5);
        graphics.blit(TEXTURE, x, y, 73, 5, Mth.lerpInt(Math.min(manaPercentage, manaItemPercentage), 0, 73), 5);

        graphics.drawString(mc.font, PlayerStatsModule.manaString(), x + 71 - mc.font.width(PlayerStatsModule.manaString()), y - 10, 0x5555ff);

        HudBarModule.lastHealthPercentage = healthPercentage;
        HudBarModule.lastManaPercentage = manaPercentage;
    }


}
