package io.ngrok.plugin;

import io.ngrok.plugin.api.staff.Staff;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.scheduler.BukkitRunnable;

public class Test {
    public void updateExamineInventory(Staff staff, Inventory inventory) {
        new BukkitRunnable() {
            @Override
            public void run() {
                if (staff.getPlayer().getOpenInventory().getTopInventory() == inventory) { // Check if the menu is still open
                    // update inventory using Staff#getExaminingPlayer();
                } else {
                    staff.setExaminingPlayer(null);
                    cancel(); // cancel the task if the menu is no longer open
                }
            }
        }.runTaskTimer(Main.getInstance(), 0, 20);
    }
}
