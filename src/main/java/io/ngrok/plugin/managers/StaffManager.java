package io.ngrok.plugin.managers;

import io.ngrok.plugin.Main;
import io.ngrok.plugin.api.inventory.ModeDataVault;
import io.ngrok.plugin.api.staff.Staff;
import io.ngrok.plugin.utils.StaffUtils;
import io.ngrok.plugin.utils.Utils;
import lombok.Getter;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import java.util.*;

public class StaffManager {
    @Getter
    private static StaffManager manager = new StaffManager();
    @Getter
    private HashMap<UUID, Staff> staffs = new HashMap<>();
    @Getter
    private List<Player> vanished = new ArrayList<>();


    public void enableMode(Player target, boolean vanished) {
        InventoryManager.getManager().saveInventory(target);
        StaffUtils.clearInventory(target);
        StaffUtils.giveModeItems(target);
        StaffUtils.giveGamma(target);
        StaffUtils.enableVanish(target);
        target.setGameMode(GameMode.ADVENTURE);
        target.setAllowFlight(true);
        target.setFlying(true);
        Staff staff = new Staff(target, vanished);
        staffs.put(target.getUniqueId(), staff);
        staff.sendMessage(Utils.format(Main.getConfiguration().getString("messages.staff mode.activated")));
    }

    public void disableMode(Player target) {
        StaffUtils.disableVanish(target);
        staffs.remove(target.getUniqueId());
        StaffUtils.clearInventory(target);
        InventoryManager.getManager().restoreInventory(target);
        StaffUtils.removeGamma(target);
        target.sendMessage(Utils.format(Main.getConfiguration().getString("messages.staff mode.deactivated")));
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
