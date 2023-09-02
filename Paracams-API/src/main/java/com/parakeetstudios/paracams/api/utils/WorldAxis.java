package com.parakeetstudios.paracams.api.utils;

import org.bukkit.util.Vector;

/**
 * The axis in the camera view plane
 */
public enum WorldAxis {

    /**
     * The x axis.
     * AKA: left and right
     */
    X(new Vector(1, 0, 0)),
    /**
     * The y axis.
     * AKA: up and down
     */
    Y(new Vector(0, 1, 0)),
    /**
     * The z axis.
     * AKA: back and forth
     */
    Z(new Vector(0, 0, 1));

    private final Vector direction;

    WorldAxis(Vector direction) {
        this.direction = direction;
    }

    public Vector getDirection() {
        return direction.clone();
    }
}
