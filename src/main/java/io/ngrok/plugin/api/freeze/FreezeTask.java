package io.ngrok.plugin.api.freeze;

import io.ngrok.plugin.Main;
import io.ngrok.plugin.utils.Utils;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

public class FreezeTask extends BukkitRunnable {
    private Player target;

    public FreezeTask(Player target) {
        this.target = target;
    }

    @Override
    public void run() {
        String sound = Main.getConfiguration().getString("configuration.screen share.sound.name");
        int volume = Main.getConfiguration().getInt("configuration.screen share.sound.volume");
        int pitch = Main.getConfiguration().getInt("configuration.screen share.sound.pitch");
        Utils.playSound(target, sound, volume, pitch);
        List<String> lines = Main.getConfiguration().getStringList("messages.screen share.message");
        for (String line : lines) {
            line = Utils.format(line);
            target.sendMessage(line);
        }
    }
}
