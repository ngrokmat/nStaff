package io.ngrok.plugin.utils;

import io.ngrok.plugin.Main;
import io.ngrok.plugin.api.inventory.ModeDataVault;
import lombok.Getter;
import lombok.experimental.UtilityClass;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

@UtilityClass
public class Utils {

    public static String format(String message) {
        message = message.replace("%prefix%", Objects.requireNonNull(Main.getConfiguration().getString("configuration.prefix")));
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static void playSound(Player target, String sound, int volume, int pitch) {
        try {
            target.playSound(target.getLocation(), Sound.valueOf(sound), volume, pitch);
        } catch (IllegalArgumentException|ArrayIndexOutOfBoundsException|NullPointerException ignored) {
        }
    }
}
