package tech.thatgravyboat.skycubed.features.hud;

import com.teamresourceful.resourcefullib.common.utils.TriState;
import net.minecraft.client.Minecraft;
import net.minecraft.world.item.ItemStack;
import tech.thatgravyboat.skycubed.api.events.HudElementRenderCallback;
import tech.thatgravyboat.skycubed.api.events.HudRenderCallback;
import tech.thatgravyboat.skycubed.api.items.DefaultItemAttributes;
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
            String skyblockId = heldItem.getCubedAttribute(DefaultItemAttributes.ID);
            graphics.drawString(Minecraft.getInstance().font, String.valueOf(skyblockId), 10, 10, 0xFFFFFF);
        });
    }
}
