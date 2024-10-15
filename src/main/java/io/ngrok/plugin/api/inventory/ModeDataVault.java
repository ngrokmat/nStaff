package io.ngrok.plugin.api.inventory;

import java.util.HashMap;
import java.util.UUID;

import lombok.Getter;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;

public class ModeDataVault {
    @Getter
    private UUID uuid;

    private HashMap<Integer, ItemStack> items;

    @Getter
    private ItemStack[] armor;

    @Getter
    private ItemStack[] offHand;

    @Getter
    private Location previousLocation;

    private boolean hasFlight;

    @Getter
    private GameMode gameMode;

    @Getter
    private float xp;

    public ModeDataVault(UUID uuid, HashMap<Integer, ItemStack> itemHash, ItemStack[] armor, ItemStack[] offHand, Location previousLocation, float xp, boolean hasFlight, GameMode gameMode) {
        this.uuid = uuid;
        this.previousLocation = previousLocation;
        this.hasFlight = hasFlight;
        this.gameMode = gameMode;
        this.offHand = offHand;
        InventorySerializer save = new InventorySerializer(uuid);
        save.save(itemHash, armor, offHand, xp);
    }

    public ModeDataVault(UUID uuid, HashMap<Integer, ItemStack> itemHash, ItemStack[] armor, Location previousLocation, boolean hasFlight, GameMode gameMode) {
        this.xp = this.xp;
        InventorySerializer save = new InventorySerializer(uuid);
        save.save(itemHash, armor, this.offHand, this.xp);
    }

    public ModeDataVault(UUID uuid, HashMap<Integer, ItemStack> itemHash, ItemStack[] armor, Location previousLocation, float xp, boolean hasFlight, GameMode gameMode) {
        this.uuid = uuid;
        this.previousLocation = previousLocation;
        this.hasFlight = hasFlight;
        this.gameMode = gameMode;
        InventorySerializer save = new InventorySerializer(uuid);
        save.save(itemHash, armor, xp);
    }

    public ModeDataVault(UUID uuid, HashMap<Integer, ItemStack> items, ItemStack[] armor, ItemStack[] offHand, float xp, GameMode gameMode) {
        this.uuid = uuid;
        this.items = items;
        this.armor = armor;
        this.offHand = offHand;
        this.xp = xp;
        this.gameMode = gameMode;
        InventorySerializer save = new InventorySerializer(uuid);
        save.save(items, armor, this.xp);
    }

    public ModeDataVault(UUID uuid, HashMap<Integer, ItemStack> itemHash, ItemStack[] armor, ItemStack[] offHand) {
        this.xp = this.xp;
        this.items = itemHash;
        this.armor = armor;
        this.offHand = offHand;
        this.uuid = uuid;
        InventorySerializer save = new InventorySerializer(uuid);
        save.save(itemHash, armor, this.xp);
    }

    public ModeDataVault(UUID uuid, HashMap<Integer, ItemStack> items, ItemStack[] armor, float xp) {
        this.uuid = uuid;
        this.items = items;
        this.armor = armor;
        this.xp = xp;
    }

    public ModeDataVault(UUID uuid, HashMap<Integer, ItemStack> itemHash, ItemStack[] armor, ItemStack[] offHand, float xp) {
        this.uuid = uuid;
        this.offHand = offHand;
        this.items = itemHash;
        this.armor = armor;
        this.xp = xp;
    }

    public HashMap<String, ItemStack> getItems() {
        InventorySerializer serializer = new InventorySerializer(this.uuid);
        return serializer.getContents();
    }

    public HashMap<Integer, ItemStack> getInventory() {
        return this.items;
    }

    public boolean hasFlight() {
        return this.hasFlight;
    }
}
