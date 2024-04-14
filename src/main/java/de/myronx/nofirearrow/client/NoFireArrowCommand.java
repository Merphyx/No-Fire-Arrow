package de.myronx.nofirearrow.client;

import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

public class NoFireArrowCommand extends NoFireArrowClient {

    public static void register() {
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> {
            dispatcher.register(ClientCommandManager.literal("nfa")
                    .executes(context -> {
                        togglefire = !togglefire;
                        Text message = togglefire ? Text.literal("§7Feuerpfeile: §cdeaktiviert") : Text.literal("§7Feuerpfeile: §aaktiviert");
                        MinecraftClient.getInstance().inGameHud.setOverlayMessage(message, false);
                        return 1;
                    }));
        });
    }
}
