package io.ngrok.plugin.managers;

import io.ngrok.plugin.api.inventory.InventorySerializer;
import io.ngrok.plugin.api.inventory.ModeDataVault;
import io.ngrok.plugin.utils.Utils;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class InventoryManager {
    @Getter
    private static InventoryManager manager = new InventoryManager();

    private final Map<UUID, ModeDataVault> modeUsers = new HashMap<>();
    private final Map<UUID, ModeDataVault> savedInventories = new HashMap<>();

    public static HashMap<Integer, ItemStack> getContents(Player p) {
        HashMap<Integer, ItemStack> itemHash = new HashMap<>();
        for (int i = 0; i <= 35; i++) {
            if (p.getInventory().getItem(i) != null) {
                itemHash.put(i, p.getInventory().getItem(i));
            }
        }
        return itemHash;
    }

    public void saveInventory(Player player) {
        ModeDataVault dataVault;
        dataVault = new ModeDataVault(player.getUniqueId(), getContents(player), player.getInventory().getArmorContents(), null, player.getExp(), player.getGameMode());
        modeUsers.put(player.getUniqueId(), dataVault);
        savedInventories.put(player.getUniqueId(), dataVault);
    }

    private static void getItems(Player p, ModeDataVault modeDataVault) {
        if (modeDataVault != null) {
            HashMap<Integer, ItemStack> items = modeDataVault.getInventory();
            for (int num : items.keySet()) {
                p.getInventory().setItem(num, items.get(num));
            }
        }
    }

    public void restoreInventory(Player player) {
        ModeDataVault modeDataVault = savedInventories.get(player.getUniqueId());
        getItems(player, modeDataVault);
        player.getInventory().setArmorContents(modeDataVault.getArmor());
        player.setExp(modeDataVault.getXp());
        if(modeDataVault.getGameMode() == GameMode.ADVENTURE) {
            player.setGameMode(GameMode.SURVIVAL);
        } else {
            player.setGameMode(modeDataVault.getGameMode());
        }
        savedInventories.remove(player.getUniqueId());
        modeUsers.remove(player.getUniqueId());
        InventorySerializer serializer = new InventorySerializer(player.getUniqueId());
        serializer.deleteFile();
    }

    public static void loadInventory(Player p) {
        InventorySerializer serializer = new InventorySerializer(p.getUniqueId());
        if (serializer.shouldLoad()) {
            try {
                HashMap<String, ItemStack> items = serializer.getContents();
                for (String num : items.keySet())
                    p.getInventory().setItem(Integer.parseInt(num), items.get(num));
                serializer.deleteFile();
            } catch (Exception exception) {
                Bukkit.getConsoleSender().sendMessage(Utils.format("&c[nStaff] An error occurred while loading an inventory: " + exception.getCause()));
                Bukkit.getConsoleSender().sendMessage(Utils.format("&c[nStaff] Player: " + p.getName()));
            }
        }
    }
}
