package com.shepherdjerred.stgrind;

import com.shepherdjerred.stgrind.commands.MainExecutor;
import com.shepherdjerred.stgrind.files.ConfigHelper;
import com.shepherdjerred.stgrind.listeners.*;
import com.shepherdjerred.stgrind.metrics.MetricsLite;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public class Main extends JavaPlugin {

    public static boolean debug;

    private static Main instance;

    public Main() {
        instance = this;
    }

    public static Main getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {

        ConfigHelper.loadConfigs();

        this.getCommand("stgrind").setExecutor(new MainExecutor());

        getServer().getPluginManager().registerEvents(new CobbleListener(), this);
        getServer().getPluginManager().registerEvents(new GolemListener(), this);
        getServer().getPluginManager().registerEvents(new GrindListener(), this);
        getServer().getPluginManager().registerEvents(new PigmenListener(), this);
        getServer().getPluginManager().registerEvents(new VillagerListener(), this);

        CobbleListener.removeOld();
        GrindListener.removeOld();

        try {
            MetricsLite metrics = new MetricsLite(this);
            metrics.start();
        } catch (IOException e) {

        }

    }

}
