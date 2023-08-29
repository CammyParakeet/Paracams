package com.parakeetstudios.paracams.core.handlers;

import com.parakeetstudios.paracams.api.ParacamsAPI;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class PlayerHandlers {

    public static void HideViewsFromSpectatorHandler(Player player) {
        ParacamsAPI.getInstance().getCameraRegistry().hideAllViewHandlesFromPlayer(player, ParacamsAPI.getInstance().getPlugin());
    }

}
