package io.ngrok.plugin.api.inventory;

import java.io.File;
import java.io.IOException;
import java.util.*;

import io.ngrok.plugin.Main;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

public class InventorySerializer {
    private File file;

    private YamlConfiguration inventory;

    private final Main INSTANCE = Main.getInstance();

    private final UUID uuid;

    public InventorySerializer(UUID uuid) {
        this.uuid = uuid;
        this.file = new File(this.INSTANCE.getDataFolder(), "Inventories/" + uuid.toString() + ".yml");
        createFile();
    }

    private void createFile() {
        File folder = new File(this.INSTANCE.getDataFolder(), "Inventories");
        if (!folder.exists())
            folder.mkdir();
        if (!this.file.exists())
            try {
                this.file.createNewFile();
                this.inventory = YamlConfiguration.loadConfiguration(this.file);
                this.inventory.createSection("Inventory");
                this.inventory.createSection("Armor");
                String[] tmp = Bukkit.getServer().getVersion().split("MC: ");
                String version = tmp[tmp.length - 1].substring(0, 3);
                if (!version.equalsIgnoreCase("1.8") || !version.equalsIgnoreCase("1.7"))
                    this.inventory.createSection("OffHand");
                this.inventory.save(this.file);
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    public boolean shouldLoad() {
        this.file = new File(this.INSTANCE.getDataFolder() + File.separator + "Inventories" + File.separator + this.uuid.toString() + ".yml");
        if (this.file.exists()) {
            Player p = Bukkit.getPlayer(this.uuid);
            Inventory inv = Bukkit.createInventory(p, InventoryType.PLAYER);
            HashMap<String, ItemStack> items = getContents();
            for (String num : items.keySet())
                inv.setItem(Integer.parseInt(num), items.get(num));
            assert p != null;
            return areInvsSame(p.getInventory(), inv);
        }
        return false;
    }

    public void deleteFile() {
        this.file = new File(this.INSTANCE.getDataFolder() + File.separator + "Inventories" + File.separator + this.uuid.toString() + ".yml");
        if (this.file.exists())
            this.file.delete();
    }

    public void save(ItemStack[] items, ItemStack[] armor, float xp) {
        this.inventory = YamlConfiguration.loadConfiguration(this.file);
        int i;
        for (i = 0; i <= items.length - 1; i++)
            this.inventory.set("Inventory." + i, items[i]);
        for (i = 0; i <= armor.length - 1; i++)
            this.inventory.set("Armor." + i, armor[i]);
        this.inventory.set("Xp", xp);
        try {
            this.inventory.save(this.file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void save(HashMap<Integer, ItemStack> items, ItemStack[] armor, float xp) {
        this.inventory = YamlConfiguration.loadConfiguration(this.file);
        for (Integer integer : items.keySet()) {
            int j = integer;
            this.inventory.set("Inventory." + j, items.get(j));
        }
        for (int i = 0; i <= armor.length - 1; i++) {
            if (armor[i] != null)
                this.inventory.set("Armor." + i, armor[i]);
        }
        this.inventory.set("Xp", xp);
        try {
            this.inventory.save(this.file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void save(HashMap<Integer, ItemStack> items, ItemStack[] armor, ItemStack[] offHand, float xp) {
        this.inventory = YamlConfiguration.loadConfiguration(this.file);
        for (int j : items.keySet()) {
            this.inventory.set("Inventory." + j, items.get(j));
        }
        int i;
        for (i = 0; i <= armor.length - 1; i++)
            this.inventory.set("Armor." + i, armor[i]);
        for (i = 0; i <= offHand.length - 1; i++)
            this.inventory.set("OffHand." + i, offHand[i]);
        this.inventory.set("Xp", xp);
        try {
            this.inventory.save(this.file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public HashMap<String, ItemStack> getContents() {
        HashMap<String, ItemStack> items = new HashMap<>();
        this.inventory = YamlConfiguration.loadConfiguration(this.file);
        for (String num : Objects.requireNonNull(this.inventory.getConfigurationSection("Inventory")).getKeys(false))
            items.put(num, this.inventory.getItemStack("Inventory." + num));
        return items;
    }

    public ItemStack[] getArmor() {
        ArrayList<ItemStack> items = new ArrayList<>();
        this.inventory = YamlConfiguration.loadConfiguration(this.file);
        for (String num : Objects.requireNonNull(this.inventory.getConfigurationSection("Armor")).getKeys(false))
            items.add(this.inventory.getItemStack("Armor." + num));
        return items.toArray(new ItemStack[0]);
    }

    public ItemStack[] getOffHand() {
        ArrayList<ItemStack> items = new ArrayList<>();
        this.inventory = YamlConfiguration.loadConfiguration(this.file);
        for (String num : Objects.requireNonNull(this.inventory.getConfigurationSection("OffHand")).getKeys(false))
            items.add(this.inventory.getItemStack("OffHand." + num));
        return items.toArray(new ItemStack[0]);
    }

    public float getXp() {
        this.inventory = YamlConfiguration.loadConfiguration(this.file);
        return (float)this.inventory.getDouble("Xp");
    }

    private boolean areInvsSame(Inventory pInv, Inventory inv) {
        for (int i = 0; i < pInv.getSize(); i++) {
            if (pInv.getItem(i) != null && inv.getItem(i) != null &&
                    !Objects.equals(pInv.getItem(i), inv.getItem(i)))
                return false;
        }
        return true;
    }
}
