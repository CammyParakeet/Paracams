package com.parakeetstudios.paracams.hook.listeners;

import com.destroystokyo.paper.event.player.PlayerStopSpectatingEntityEvent;
import com.parakeetstudios.paracams.core.utils.Paralog;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerGameModeChangeEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import static com.parakeetstudios.paracams.core.handlers.PlayerHandlers.HideViewsFromSpectatorHandler;
import static com.parakeetstudios.paracams.core.handlers.PlayerHandlers.ShowDisplaysToPlayer;

public class PlayerSpectatorListeners implements Listener {

    @EventHandler
    public void PlayerEntersSpectatorListener(PlayerGameModeChangeEvent e) {
        if (e.getNewGameMode() == GameMode.SPECTATOR) {
            HideViewsFromSpectatorHandler(e.getPlayer());
        }
    }

    @EventHandler
    public void PlayerJoinInSpectatorListener(PlayerJoinEvent e) {
        if (e.getPlayer().getGameMode() == GameMode.SPECTATOR) {
            HideViewsFromSpectatorHandler(e.getPlayer());
        }
    }

    //TODO leaves spectator simple handler - no permissions
    @EventHandler
    public void PlayerStopsSpectatingListener(PlayerStopSpectatingEntityEvent e) {
        if (e.getSpectatorTarget().getScoreboardTags().contains("ParacamView")) {
            //TODO proper setup for this permission?
            if (!e.getPlayer().hasPermission("allowedToLeave?")) {
                // cancel the player leaving
                e.setCancelled(true);
                // reset the spectator target to stop glitching
                e.getPlayer().setSpectatorTarget(e.getSpectatorTarget());
                return; // return to stop the display showing
            }
            //TODO only show if they have a permission? Or a setting
            ShowDisplaysToPlayer(e.getPlayer());
        }
    }

}
