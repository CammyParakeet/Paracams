package com.parakeetstudios.paracams.core.handlers;

import com.parakeetstudios.paracams.api.ParacamsAPI;
import com.parakeetstudios.paracams.core.utils.NMSUtils;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundSetCameraPacket;
import net.minecraft.network.protocol.game.ServerboundSwingPacket;
import net.minecraft.server.network.ServerPlayerConnection;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;


public class PlayerHandlers {

    public static void HideViewsFromSpectatorHandler(Player player) {
        ParacamsAPI.getInstance().getCameraRegistry().hideAllViewHandlesFromPlayer(player);
    }

    public static void ShowDisplaysToPlayer(Player player) {
        ParacamsAPI.getInstance().getCameraRegistry().showAllCamerasToPlayer(player);
    }

    public static void setCamera(Player player, Entity posEntity, Plugin plugin, long delay) {
        new BukkitRunnable() {
            @Override
            public void run() {
                player.setInvisible(true);
                final ClientboundSetCameraPacket packet = new ClientboundSetCameraPacket(NMSUtils.getEntityHandle(posEntity));
                sendPlayerPacket(player, packet);
            }
        }.runTaskLater(plugin, delay);
    }


    public ServerboundSwingPacket interceptArmSwing(Player player, ServerboundSwingPacket packet) {
        //TODO permission logic etc for pausing

        return null;
    }


    public static void sendPlayerPacket(Player player, Packet<?> packet) {
        NMSUtils.getPlayerHandle(player).connection.send(packet);
    }

    public static void sendPlayerPackets(Player player, Packet<?> ...packets) {
        ServerPlayerConnection conn = NMSUtils.getPlayerHandle(player).connection;
        for (Packet<?> packet : packets) conn.send(packet);
    }

}
