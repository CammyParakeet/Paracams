package com.parakeetstudios.paracams.core.utils;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

public class DefaultEntities {

    public static Entity createBat(Location location, String viewSettings) {
        //TODO setup settings logic
        Entity batHandle = location.getWorld().spawnEntity(location, EntityType.BAT);
        batHandle.setSilent(true);
        batHandle.setGravity(false);
        batHandle.setInvulnerable(true);
        batHandle.setCustomNameVisible(false);
        batHandle.setGlowing(true);
        batHandle.setVisibleByDefault(false); //test?
        return batHandle;
    }

    public static Entity createDisplay(Location location, String displaySettings) {
        //TODO settings setup
        Entity displayHandle = location.getWorld().spawnEntity(location, EntityType.BLOCK_DISPLAY);
        displayHandle.setCustomNameVisible(true);
        displayHandle.setGlowing(true);
        //TODO proper display setup
        return displayHandle;
    }


}
