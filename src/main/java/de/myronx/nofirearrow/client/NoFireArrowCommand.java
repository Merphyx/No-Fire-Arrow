package de.myronx.nofirearrow.client;

import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

public class NoFireArrowCommand extends NoFireArrowClient {

    public static void register() {
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> {
            dispatcher.register(ClientCommandManager.literal("nfa")
                    .then(ClientCommandManager.literal("nofire")
                            .executes(context -> {
                                toggleFire = !toggleFire;
                                Text message = toggleFire ? Text.literal("§7Feuerpfeile: §cdeaktiviert") : Text.literal("§7Feuerpfeile: §aaktiviert");
                                MinecraftClient.getInstance().inGameHud.setOverlayMessage(message, false);
                                return 1;
                            })
                    )
                    .then(ClientCommandManager.literal("render")
                            .executes(context -> {
                                renderArrow = !renderArrow;
                                Text message = renderArrow ? Text.literal("§7Pfeile: §aaktiviert") : Text.literal("§7Pfeile: §cdeaktiviert");
                                MinecraftClient.getInstance().inGameHud.setOverlayMessage(message, false);
                                return 1;
                            })
                    )
            );
        });
    }
}
