package com.parakeetstudios.paracams.api.camera;

import com.parakeetstudios.paracams.api.cinematics.AnimationController;
import com.parakeetstudios.paracams.api.registers.CameraRegistry;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.UUID;

public interface Camera {

    int getCameraID();

    void setName(String name);
    String getName();

    void setPosition(Location location);
    Location getPosition();

    void setOrigin(Location location);
    Location getOrigin();

    void pan();
    void tilt();
    void rotate();

    void showDisplay();
    void hideDisplay();
    boolean isDisplayVisible();

    //TODO do we need these getters or only use field?
    void setCameraSettings(CameraSettings settings);
    CameraSettings getCameraSettings();

    void attachPlayer(Player player);
    void detachPlayer(Player player);
    boolean isPlayerAttached(Player player);

    List<Player> getAttachedPlayers();
    Player getAttachedPlayerByID(UUID id);
    Player getAttachedPlayerByName(String name);

    CameraRegistry getOwningRegistry();

    void createMotionController();
    void addMotionController(AnimationController controller);
    AnimationController getMotionController(int id, Camera owner);

    //TODO setup animation manager of some sort

}