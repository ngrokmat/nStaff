package io.ngrok.plugin.managers;

import io.ngrok.plugin.Main;
import io.ngrok.plugin.api.freeze.FreezeTask;
import io.ngrok.plugin.api.staff.Staff;
import io.ngrok.plugin.utils.Cooldowns;
import io.ngrok.plugin.utils.Utils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FreezeManager {
    public static FreezeManager manager = new FreezeManager();
    protected HashMap<Player, FreezeTask> freezeTasks = new HashMap<>();

    public List<Player> getFrozenPlayers() {
        List<Player> frozenPlayers = new ArrayList<>();
        for (Staff staff : StaffManager.getManager().getStaffs().values()) {
            frozenPlayers.addAll(staff.getFrozenPlayers().keySet());
        }
        return frozenPlayers;
    }

    public boolean isFrozen(Player target) {
        List<Player> frozenPlayers = getFrozenPlayers();
        for (Player frozenPlayer : frozenPlayers) {
            if (frozenPlayer.equals(target)) {
                return true;
            }
        }
        return false;
    }

    public void freeze(Staff staff, Player target) {
        FreezeTask task = new FreezeTask(target);
        int cooldown = Main.getConfiguration().getInt("configuration.screen share.message.cooldown");
        task.runTaskTimer(Main.getInstance(), 0L, 20L * cooldown);
        freezeTasks.put(target, task);
        staff.getFrozenPlayers().put(target, target.getInventory().getHelmet());
        target.getInventory().setHelmet(new ItemStack(Material.ICE));
        if (Main.getConfiguration().getBoolean("messages.screen share.frozen.configuration.shout")) {
            for (Staff staffs : StaffManager.getManager().getStaffs().values()) {
                staffs.sendMessage(Utils.format(Main.getConfiguration().getString("messages.screen share.frozen.message")
                        .replace("%target%", target.getName())
                        .replace("%staff%", staff.getName())));
            }
        } else {
            staff.sendMessage(Utils.format(Main.getConfiguration().getString("messages.screen share.frozen.message")
                    .replace("%target%", target.getName())
                    .replace("%staff%", staff.getName())));
        }
        Cooldowns.addCooldown("interactFreeze", staff.getPlayer(), 1);
    }

    public void unfreeze(Staff staff, Player target) {
        if (getFrozenPlayers().contains(target) && isFrozen(target)) {
            if (freezeTasks.containsKey(target)) {
                freezeTasks.get(target).cancel();
                freezeTasks.remove(target);
            }
            ItemStack helmet = staff.getFrozenPlayers().get(target);
            target.getInventory().setHelmet(helmet);
            staff.getFrozenPlayers().remove(target);
            if (Main.getConfiguration().getBoolean("messages.screen share.unfrozen.configuration.shout")) {
                for (Staff staffs : StaffManager.getManager().getStaffs().values()) {
                    staffs.sendMessage(Utils.format(Main.getConfiguration().getString("messages.screen share.unfrozen.message")
                            .replace("%target", target.getName())
                            .replace("%staff%", staff.getName())));
                }
            } else {
                staff.sendMessage(Utils.format(Main.getConfiguration().getString("messages.screen share.unfrozen.message")
                        .replace("%target%", target.getName())
                        .replace("%staff%", staff.getName())));
            }
            Cooldowns.addCooldown("interactFreeze", staff.getPlayer(), 1);
        }
    }
}
