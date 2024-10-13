package io.ngrok.plugin.utils;

import io.ngrok.plugin.Main;
import io.ngrok.plugin.api.inventory.ModeDataVault;
import lombok.Getter;
import lombok.experimental.UtilityClass;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.*;

@UtilityClass
public class Utils {
    @Getter
    public static List<Player> staffs = new ArrayList<>();
    @Getter
    public static List<Player> vanished = new ArrayList<>();
    @Getter
    public static HashMap<Player, ItemStack> frozenPlayers = new HashMap<>();
    @Getter
    public static HashMap<Player, Player> inExamine = new HashMap<>();

    private static final Map<UUID, ModeDataVault> modeUsers = new HashMap<>();
    private static final Map<UUID, ModeDataVault> savedInventories = new HashMap<>();

    public static String format(String message) {
        message = message.replace("%prefix%", Objects.requireNonNull(Main.getConfiguration().getString("configuration.prefix")));
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}
