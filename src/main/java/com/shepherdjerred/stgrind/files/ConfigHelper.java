package com.shepherdjerred.stgrind.files;

import com.shepherdjerred.stgrind.Main;
import org.bukkit.configuration.file.YamlConfiguration;

public class ConfigHelper {

    @SuppressWarnings("deprecation")
    public static void loadConfigs() {
        Main.getInstance().saveDefaultConfig();
        Main.getInstance().getConfig().setDefaults(YamlConfiguration.loadConfiguration(Main.getInstance().getResource("config.yml")));
        Main.getInstance().getConfig().options().copyDefaults(true);
        Main.getInstance().saveConfig();

        Main.debug = Main.getInstance().getConfig().getBoolean("debug");
        FileManager.getInstance().loadFiles();
    }

}
