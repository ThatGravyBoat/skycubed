package tech.thatgravyboat.skycubed.config;

import com.teamresourceful.resourcefulconfig.api.client.ResourcefulConfigScreen;
import com.teamresourceful.resourcefulconfig.api.loader.Configurator;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;

public class ConfigHandler {

    public static final Configurator CONFIGURATOR = new Configurator("skycubed");

    public static void init() {
        CONFIGURATOR.register(SkyCubedConfig.class);

        ClientCommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> {
            dispatcher.register(ClientCommandManager.literal("skycubed")
                .executes(context -> {
                    Minecraft.getInstance().tell(() -> {
                        Screen screen = ResourcefulConfigScreen.get(null, CONFIGURATOR, SkyCubedConfig.class);
                        Minecraft.getInstance().setScreen(screen);
                    });
                    return 1;
                }));
        });
    }
}
