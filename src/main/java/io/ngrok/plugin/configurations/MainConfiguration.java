package io.ngrok.plugin.configurations;

import io.ngrok.plugin.api.configuration.ConfigurationFile;
import org.bukkit.configuration.file.YamlConfiguration;

import java.util.Arrays;

public class MainConfiguration extends ConfigurationFile {
    public MainConfiguration(String configName, String dir) {
        super(configName, dir);
        if (isFirstTime()) {
            YamlConfiguration configuration = getConfiguration();

            configuration.options().header("The option enable on join will only work if you have an database configured, it will work on every server u have nStaff installed.");

            configuration.addDefault("configuration.prefix", "&eStaff &8⮚");

            configuration.addDefault("configuration.staff mode.enable on join.enabled", false);
            configuration.addDefault("configuration.staff mode.enable on join.mysql.host", "host");
            configuration.addDefault("configuration.staff mode.enable on join.mysql.port", "0000");
            configuration.addDefault("configuration.staff mode.enable on join.mysql.database", "database");
            configuration.addDefault("configuration.staff mode.enable on join.mysql.username", "username");
            configuration.addDefault("configuration.staff mode.enable on join.mysql.password", "password");
            configuration.addDefault("configuration.staff mode.enable on join.mysql.configuration.connectionPoolSize", 10);
            configuration.addDefault("configuration.staff mode.enable on join.mysql.configuration.connectionPoolIdle", 10);
            configuration.addDefault("configuration.staff mode.enable on join.mysql.configuration.connectionPoolTimeout", 5000);
            configuration.addDefault("configuration.staff mode.enable on join.mysql.configuration.connectionPoolLifetime", 1800000);

            configuration.addDefault("configuration.chat mention.enabled", false);
            configuration.addDefault("configuration.chat mention.sound.enabled", true);
            configuration.addDefault("configuration.chat mention.sound.name", "ANVIL_LAND");
            configuration.addDefault("configuration.chat mention.volume", 10);
            configuration.addDefault("configuration.chat mention.pitch", 10);

            configuration.addDefault("configuration.examine inventory.item separator.item stack", "160:15");
            configuration.addDefault("configuration.examine inventory.inventory title", "&eViewing inventory of &d%player%");
            configuration.addDefault("configuration.examine inventory.ender chest name", "&eView EnderChest of &d%player%");

            configuration.addDefault("configuration.examine inventory.ender chest.inventory title", "&eViewing EnderChest of &d%player%");

            configuration.addDefault("configuration.miner menu.inventory title", "&eMiner Menu");
            configuration.addDefault("configuration.miner menu.y axis level", 30);

            configuration.addDefault("configuration.screen share.sound.enabled", true);
            configuration.addDefault("configuration.screen share.sound.name", "ANVIL_LAND");
            configuration.addDefault("configuration.screen share.sound.volume", 10);
            configuration.addDefault("configuration.screen share.sound.pitch", 10);
            configuration.addDefault("configuration.screen share.message.cooldown", 5);
            configuration.addDefault("configuration.screen share.configuration.logout commands.enabled", false);
            configuration.addDefault("configuration.screen share.configuration.logout commands.list", Arrays.asList("/tempban %player% 7d Logout in SS", "/anothercommand"));

            configuration.addDefault("messages.no permission", "%prefix% &cYou don't have permission to do that.");
            configuration.addDefault("messages.user not found", "%prefix% &cUser not found.");

            configuration.addDefault("messages.staff mode.activated", "%prefix% &7Your staff mode has been &aactivated&7.");
            configuration.addDefault("messages.staff mode.deactivated", "%prefix% &7Your staff mode has been &cdeactivated&7.");

            configuration.addDefault("messages.chat mention", "&a%player% &ementioned you in the chat!");

            configuration.addDefault("messages.vanish.activated", "%prefix% &7Your vanish has been &aactivated&7.");
            configuration.addDefault("messages.vanish.deactivated", "%prefix% &7Your vanish has been &cdeactivated&7.");

            configuration.addDefault("messages.screen share.message", Arrays.asList("", "&cYOU'RE FROZEN", "&cFOLLOW THE STAFF INSTRUCTIONS", "&4&lDON'T DISCONNECT", ""));
            configuration.addDefault("messages.screen share.cannot freeze", "%prefix% &cYou can't freeze that player.");
            configuration.addDefault("messages.screen share.frozen.message", "%prefix% &a%target% &7has been frozen by &e%staff%");
            configuration.addDefault("messages.screen share.frozen.configuration.shout", false);
            configuration.addDefault("messages.screen share.unfrozen.message", "%prefix% &a%target% &7has been unfrozen by &e%staff%");
            configuration.addDefault("messages.screen share.unfrozen.configuration.shout", false);
            configuration.addDefault("messages.screen share.user logout", "%prefix% &e%player% &cwent offline while frozen.");
        }
    }
}
