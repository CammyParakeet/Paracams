package com.parakeetstudios.paracams.core.utils;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class NMSUtils {

    public static ServerPlayer getPlayerHandle(Player player) {
        return ((CraftPlayer) player).getHandle();
    }

    public static Entity getEntityHandle(org.bukkit.entity.Entity bukkitEntity) {
        return ((CraftEntity) bukkitEntity).getHandle();
    }

}
