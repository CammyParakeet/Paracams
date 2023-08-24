package com.parakeetstudios.paracams.api.camera;

import com.parakeetstudios.paracams.api.registers.CameraRegistry;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public interface Camera {

    int getCamID();

    void setPosition(Location location);

    Location getPosition();

    void pan();

    void tilt();

    void rotate();

    void showDisplay();

    void hideDisplay();

    void attachPlayer(Player player);

    void detachPlayer(Player player);

    CameraRegistry getOwningRegistry();

    //TODO setup animation manager of some sort

}