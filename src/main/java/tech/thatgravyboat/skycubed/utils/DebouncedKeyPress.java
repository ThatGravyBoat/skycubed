package tech.thatgravyboat.skycubed.utils;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.Minecraft;

public class DebouncedKeyPress {

    private final int key;
    private long lastPress = 0;

    public DebouncedKeyPress(int key) {
        this.key = key;
    }

    public boolean isDown() {
        if (System.currentTimeMillis() - lastPress < 500) return false;
        if (InputConstants.isKeyDown(Minecraft.getInstance().getWindow().getWindow(), this.key)) {
            this.lastPress = System.currentTimeMillis();
            return true;
        }
        return false;
    }
}
