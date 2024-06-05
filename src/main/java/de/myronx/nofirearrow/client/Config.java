package de.myronx.nofirearrow.client;

import net.fabricmc.loader.api.FabricLoader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public class Config {
    private static final Path CONFIG_FILE = FabricLoader.getInstance().getConfigDir().resolve("nofirearrow.properties");
    private static final Properties PROPERTIES = new Properties();

    static {
        loadConfig();
    }

    public static boolean toggleFire() {
        return Boolean.parseBoolean(PROPERTIES.getProperty("toggleFire", "true"));
    }

    public static boolean renderArrow() {
        return Boolean.parseBoolean(PROPERTIES.getProperty("renderArrow", "true"));
    }

    public static void saveConfig() {
        PROPERTIES.setProperty("toggleFire", String.valueOf(NoFireArrowClient.toggleFire));
        PROPERTIES.setProperty("renderArrow", String.valueOf(NoFireArrowClient.renderArrow));

        try {
            PROPERTIES.store(Files.newOutputStream(CONFIG_FILE), null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void loadConfig() {
        if (Files.exists(CONFIG_FILE)) {
            try {
                PROPERTIES.load(Files.newInputStream(CONFIG_FILE));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}