package io.ngrok.plugin.api.staff;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

@Getter
public class Staff {
    Player player; // Staff
    HashMap<Player, ItemStack> frozenPlayers; // frozen players
    @Setter
    Player examiningPlayer;
    boolean vanished; // vanished or not
    boolean isExamining;

    public Staff(Player player, boolean vanished) {
        this.player = player;
        this.vanished = vanished;
        this.isExamining = false;
        this.frozenPlayers = null;
        this.examiningPlayer = null;
    }

    public void sendMessage(String message) {
        player.sendMessage(message);
    }

    public String getName() {
        return player.getName();
    }
}
