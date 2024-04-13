package tech.thatgravyboat.skycubed.features.hud;

import com.teamresourceful.resourcefullib.common.utils.TriState;
import net.minecraft.client.Minecraft;
import net.minecraft.world.item.ItemStack;
import tech.thatgravyboat.skycubed.api.events.HudElementRenderCallback;
import tech.thatgravyboat.skycubed.api.events.HudRenderCallback;
import tech.thatgravyboat.skycubed.api.items.ItemAttributes;
import tech.thatgravyboat.skycubed.config.features.HudReplacementConfig;
import tech.thatgravyboat.skycubed.features.misc.SkyBlockModule;

import java.util.EnumSet;
import java.util.Set;

public class HiddenHudModule {

    private static final Set<HudElementRenderCallback.Element> HIDDEN_ELEMENTS = EnumSet.of(
        HudElementRenderCallback.Element.ARMOR,
        HudElementRenderCallback.Element.HEALTH,
        HudElementRenderCallback.Element.HUNGER,
        HudElementRenderCallback.Element.AIR
    );

    public static void init() {
        HudElementRenderCallback.EVENT.register(element -> {
            if (!SkyBlockModule.isSkyBlock()) return TriState.UNDEFINED;
            if (HudReplacementConfig.hideEffects && element == HudElementRenderCallback.Element.EFFECTS) {
                return TriState.FALSE;
            }
            if (HIDDEN_ELEMENTS.contains(element)) {
                return TriState.FALSE;
            }
            return TriState.UNDEFINED;
        });

        HudRenderCallback.EVENT.register((graphics, partialTicks) -> {
            if (Minecraft.getInstance().player == null) return;
            if (!SkyBlockModule.isSkyBlock()) return;
            ItemStack heldItem = Minecraft.getInstance().player.getMainHandItem();
            var ability = heldItem.getCubedAttribute(ItemAttributes.RIGHT_CLICK_ABILITY);
            graphics.drawString(Minecraft.getInstance().font, String.valueOf(ability), 10, 10, 0xFFFFFF);
        });
    }
}
