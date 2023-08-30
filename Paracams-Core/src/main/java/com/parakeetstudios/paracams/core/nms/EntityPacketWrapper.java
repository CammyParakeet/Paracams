package com.parakeetstudios.paracams.core.nms;

import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundMoveEntityPacket;
import net.minecraft.server.network.ServerPlayerConnection;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftPlayer;
import org.bukkit.entity.Entity;

import java.util.Collection;
import java.util.stream.Collectors;

public class EntityPacketWrapper {


    public static Collection<ServerPlayerConnection> getConnections() {
        return Bukkit.getServer().getOnlinePlayers().stream()
                .map(player -> ((CraftPlayer) player).getHandle().connection)
                .collect(Collectors.toList());
    }

    private static short toFixedPoint(double val) {
        return (short) (val * 4096);
    }

    private static byte toByteAngle(float angle) {
        return (byte) ((int) (angle * 256f / 360f));
    }

    public static void broadcastPacket(Packet<?> packet) {
        getConnections().forEach(c -> {
            c.send(packet);
        });
    }


    public static void moveEntity(Entity entity, double dx, double dy, double dz) {
       ClientboundMoveEntityPacket.Pos packet = new ClientboundMoveEntityPacket.Pos(
               entity.getEntityId(),
               toFixedPoint(dx),
               toFixedPoint(dy),
               toFixedPoint(dz),
               false
       );

       broadcastPacket(packet);
    }

    public static void rotateEntity(Entity entity, float yaw, float pitch) {
        ClientboundMoveEntityPacket.Rot packet = new ClientboundMoveEntityPacket.Rot(
                entity.getEntityId(),
                toByteAngle(yaw),
                toByteAngle(pitch),
                false
        );

        broadcastPacket(packet);
    }

    public static void moveAndRotateEntity(Entity entity, double dx, double dy, double dz, float yaw, float pitch) {
        ClientboundMoveEntityPacket.PosRot packet = new ClientboundMoveEntityPacket.PosRot(
                entity.getEntityId(),
                toFixedPoint(dx),
                toFixedPoint(dy),
                toFixedPoint(dz),
                toByteAngle(yaw),
                toByteAngle(pitch),
                false
        );

        broadcastPacket(packet);
    }




}
