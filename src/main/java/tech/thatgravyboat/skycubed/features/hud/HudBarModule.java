package tech.thatgravyboat.skycubed.features.hud;

import com.teamresourceful.resourcefullib.common.utils.TriState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import tech.thatgravyboat.skycubed.SkyCubed;
import tech.thatgravyboat.skycubed.api.events.HudElementRenderCallback;
import tech.thatgravyboat.skycubed.api.events.HudRenderCallback;
import tech.thatgravyboat.skycubed.config.features.hud.HudReplacementConfig;
import tech.thatgravyboat.skycubed.features.misc.skyblock.IslandType;
import tech.thatgravyboat.skycubed.features.misc.skyblock.SkyBlockModule;
import tech.thatgravyboat.skycubed.features.stats.PlayerStatsModule;

import java.util.EnumSet;
import java.util.Set;

public class HudBarModule {

    private static final ResourceLocation TEXTURE = new ResourceLocation(SkyCubed.MOD_ID, "textures/gui/bars.png");
    private static final Set<HudElementRenderCallback.Element> HIDDEN_ELEMENTS = EnumSet.of(
            HudElementRenderCallback.Element.ARMOR,
            HudElementRenderCallback.Element.HEALTH,
            HudElementRenderCallback.Element.HUNGER,
            HudElementRenderCallback.Element.AIR
    );

    private static float lastManaPercentage = 0f;
    private static float lastHealthPercentage = 0f;

    public static void init() {
        HudRenderCallback.EVENT.register(HudBarModule::render);
        HudElementRenderCallback.EVENT.register(element -> {
            if (HIDDEN_ELEMENTS.contains(element) && HudBarModule.shouldReplaceHud()) {
                return TriState.FALSE;
            }
            return TriState.UNDEFINED;
        });
    }

    private static void render(GuiGraphics graphics, float partialTicks) {
        if (!shouldReplaceHud()) return;
        Minecraft mc = Minecraft.getInstance();
        int width = mc.getWindow().getGuiScaledWidth();
        int height = mc.getWindow().getGuiScaledHeight();
        int x = width / 2 - 91;
        int y = height - 37;

        float healthPercentage = Mth.lerp(partialTicks, lastHealthPercentage, PlayerStatsModule.healthPercent(HudReplacementConfig.showEffectiveHealth));
        float manaPercentage = Mth.lerp(partialTicks, lastManaPercentage, PlayerStatsModule.manaPercent());
        float manaItemPercentage = PlayerStatsModule.itemManaPercent();

        boolean hasAbsorption = healthPercentage > 1f;

        graphics.blit(TEXTURE, x, y, hasAbsorption ? 73 : 0, 10, 73, 5);
        graphics.blit(TEXTURE, x, y, hasAbsorption ? 73 : 0, 15, Mth.lerpInt(healthPercentage, 0, 73), 5);

        graphics.drawString(mc.font, PlayerStatsModule.healthString(HudReplacementConfig.showEffectiveHealth), x + 1, y - 10, 0xff5555);

        x = width / 2 + 18;
        graphics.blit(TEXTURE, x, y, 0, 0, 73, 5);
        graphics.blit(TEXTURE, x, y, 73, 0, Mth.lerpInt(manaItemPercentage, 0, 73), 5);
        graphics.blit(TEXTURE, x, y, 0, 5, Mth.lerpInt(manaPercentage, 0, 73), 5);
        graphics.blit(TEXTURE, x, y, 73, 5, Mth.lerpInt(Math.min(manaPercentage, manaItemPercentage), 0, 73), 5);

        graphics.drawString(mc.font, PlayerStatsModule.manaString(), x + 71 - mc.font.width(PlayerStatsModule.manaString()), y - 10, 0x5555ff);

        HudBarModule.lastHealthPercentage = healthPercentage;
        HudBarModule.lastManaPercentage = manaPercentage;
    }

    public static boolean shouldReplaceHud() {
        return HudReplacementConfig.replaceVanillaHud && SkyBlockModule.isSkyBlock() && SkyBlockModule.getIsland() != IslandType.RIFT;
    }


}
