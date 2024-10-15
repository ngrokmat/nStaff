package io.ngrok.plugin.utils;

import io.ngrok.plugin.Main;
import io.ngrok.plugin.managers.StaffManager;
import io.ngrok.plugin.utils.item.ItemBuilder;
import lombok.experimental.UtilityClass;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

@UtilityClass
public class StaffUtils {

    public static void clearInventory(Player player) {
        player.getInventory().clear();
        player.getInventory().setHelmet(null);
        player.getInventory().setChestplate(null);
        player.getInventory().setLeggings(null);
        player.getInventory().setBoots(null);
    }

    public static void giveModeItems(Player player) {

    }

    public static void giveGamma(Player player) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, Integer.MAX_VALUE, 0), true);
    }

    public static void removeGamma(Player player) {
        player.removePotionEffect(PotionEffectType.NIGHT_VISION);
    }

    public static void changeVanishItem(Player player, String to) {
        if (to.equalsIgnoreCase("GREEN")) {
            enableVanish(player);
            ItemBuilder item = new ItemBuilder(Material.valueOf(Main.getInstance().getConfig().getString("Items.Vanish Item.Enabled.Item Material")), 1);
            item.setDisplayName(Main.getInstance().getConfig().getString("Items.Vanish Item.Enabled.Item Name"));
            item.setLore(Main.getInstance().getConfig().getStringList("Items.Vanish Item.Item Lore"));
            player.getInventory().setItem(Main.getInstance().getConfig().getInt("Items.Vanish Item.Slot") - 1, item.build());
        } else if (to.equalsIgnoreCase("GRAY")) {
            disableVanish(player);
            ItemBuilder item = new ItemBuilder(Material.valueOf(Main.getInstance().getConfig().getString("Items.Vanish Item.Disabled.Item Material")), 1);
            item.setDisplayName(Main.getInstance().getConfig().getString("Items.Vanish Item.Disabled.Item Name"));
            item.setLore(Main.getInstance().getConfig().getStringList("Items.Vanish Item.Item Lore"));
            player.getInventory().setItem(Main.getInstance().getConfig().getInt("Items.Vanish Item.Slot") - 1, item.build());
        }
    }

    public static void enableVanish(Player staff) {
        StaffManager.getManager().getVanished().add(staff);
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (!player.hasPermission("nstaff.staff")) {
                player.hidePlayer(staff);
            } else {
                player.showPlayer(staff);
            }
        }
        staff.sendMessage(Utils.format(Main.getConfiguration().getString("messages.vanish.activated")));
    }

    public static void disableVanish(Player staff) {
        StaffManager.getManager().getVanished().remove(staff);
        Bukkit.getOnlinePlayers().forEach(player -> player.showPlayer(staff));
        staff.sendMessage(Utils.format(Main.getConfiguration().getString("messages.vanish.deactivated")));
    }
}
