package io.ngrok.plugin;

import io.ngrok.plugin.api.configurations.MainConfiguration;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    @Getter
    private static Main instance;
    @Getter
    private static MainConfiguration configuration;

    @Override
    public void onEnable() {
        super.onEnable();
        instance = this;
        configuration = new MainConfiguration("configuration", getDataFolder().toString());
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }
}