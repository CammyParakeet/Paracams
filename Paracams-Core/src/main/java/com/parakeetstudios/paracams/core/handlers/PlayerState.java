package com.parakeetstudios.paracams.core.handlers;

import com.parakeetstudios.paracams.api.ParacamsAPI;
import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.UUID;

public class PlayerState {

    private final UUID playerID;
    private final Location location;
    private final ItemStack[] inventoryContents;
    private final ItemStack[] armorContents;
    private final GameMode gamemode;

    public PlayerState(Player player) {
        this.playerID = player.getUniqueId();
        this.location = player.getLocation();
        this.inventoryContents = player.getInventory().getContents();
        this.armorContents = player.getInventory().getArmorContents();
        this.gamemode = player.getGameMode();
    }

    public void clearExistence(Player player) {
        if (!player.getUniqueId().equals(playerID)) return;

        player.getInventory().clear();

        for (Player online : Bukkit.getOnlinePlayers()) {
            if (!online.equals(player)) online.hidePlayer(ParacamsAPI.getInstance().getPlugin(), player);
        }
    }

    public void restoreExistence(Player player) {
        if (!player.getUniqueId().equals(playerID)) return;

        player.teleport(location);
        player.getInventory().setContents(inventoryContents);
        player.getInventory().setArmorContents(armorContents);
        player.setGameMode(gamemode);

        for (Player online : Bukkit.getOnlinePlayers()) {
            online.showPlayer(ParacamsAPI.getInstance().getPlugin(), player);
        }
    }

}
