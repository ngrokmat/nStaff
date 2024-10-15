package io.ngrok.plugin.managers;

import io.ngrok.plugin.api.staff.Staff;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class FreezeManager {
    public static FreezeManager manager = new FreezeManager();

    public List<Player> getFrozenPlayers() {
        List<Player> frozenPlayers = new ArrayList<>();
        for (Staff staff : StaffManager.getManager().getStaffs().values()) {
            frozenPlayers.addAll(staff.getFrozenPlayers());
        }
        return frozenPlayers;
    }

    public boolean isFrozen(Player player) {
        List<Player> frozenPlayers = getFrozenPlayers();
        for (Player frozenPlayer : frozenPlayers) {
            if (frozenPlayer.equals(player)) {
                return true;
            }
        }
        return false;
    }

    public void freeze(Player player) {

    }

    public void unfreeze(Player player) {

    }
}
