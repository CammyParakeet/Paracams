package com.parakeetstudios.paracams.core.utils;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

public class DefaultEntities {

    public static Entity createBat(Location location, String viewSettings) {
        //TODO setup settings logic
        World world = location.getWorld();
        Entity batHandle = world.spawnEntity(location, EntityType.BAT);
        batHandle.setSilent(true);
        batHandle.setGravity(false);
        batHandle.setInvulnerable(true);
        batHandle.setCustomNameVisible(false);
        batHandle.setGlowing(true);
        batHandle.setVisibleByDefault(false); //test?
        return batHandle;
    }


}
