package io.ngrok.plugin.api.staff;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;

import java.util.List;

@Getter
public class Staff {
    Player player; // Staff
    List<Player> frozenPlayers; // frozen players
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
}
