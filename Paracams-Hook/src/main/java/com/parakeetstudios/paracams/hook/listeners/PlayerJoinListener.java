package com.parakeetstudios.paracams.hook.listeners;

import com.parakeetstudios.paracams.api.ParacamsAPI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

// This is to hide any Player that is currently attached to a camera from this joined player.
public class PlayerJoinListener implements Listener {

    @EventHandler
    public void HidePlayersInCamera(PlayerJoinEvent e) {

        //TODO some kind of setting might be needed to toggle this?
        // eg in case of if we still want some kind of player -> player interaction
        final Player joinedPlayer = e.getPlayer();
        ParacamsAPI.getInstance().getCameraRegistry().getAllCameras().forEach(camera -> {
            camera.getAttachedPlayers().keySet().forEach(p -> {
                joinedPlayer.hidePlayer(ParacamsAPI.getInstance().getPlugin(), p);
            });
        });

    }

}
