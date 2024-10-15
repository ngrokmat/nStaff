package io.ngrok.plugin.managers;

import io.ngrok.plugin.api.inventory.ModeDataVault;
import io.ngrok.plugin.api.staff.Staff;
import lombok.Getter;
import org.bukkit.entity.Player;

import java.util.*;

public class StaffManager {
    @Getter
    private static StaffManager manager = new StaffManager();
    @Getter
    private HashMap<UUID, Staff> staffs = new HashMap<>();

    private final Map<UUID, ModeDataVault> modeUsers = new HashMap<>();
    private final Map<UUID, ModeDataVault> savedInventories = new HashMap<>();

    public void enableMode(Player player, boolean vanished) {

        Staff staff = new Staff(player, vanished);
        staffs.put(player.getUniqueId(), staff);
    }

    public void disableMode(Player player) {
        staffs.remove(player.getUniqueId());
    }

    public Staff getStaff(UUID uuid) {
        for (Staff staff : staffs.values()) {
            if (staff.getPlayer().getUniqueId().equals(uuid)) {
                return staff;
            }
        }
        return null;
    }
}
