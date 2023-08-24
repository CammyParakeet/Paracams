package com.parakeetstudios.paracams.api.camera;

import com.parakeetstudios.paracams.api.cinematics.MotionController;
import com.parakeetstudios.paracams.api.registers.CameraRegistry;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface Camera {

    int getCamID();

    void setPosition(Location location);

    Location getPosition();

    void setOrigin(Location location);

    Location getOrigin();

    void pan();

    void tilt();

    void rotate();

    void showDisplay();

    void hideDisplay();

    void attachPlayer(Player player);

    void detachPlayer(Player player);

    List<Player> getAttachedPlayers();

    Player getAttachedPlayerByID(UUID id);

    Player getAttachedPlayerByName(String name);

    CameraRegistry getOwningRegistry();

    void createMotionController();

    void addMotionController(MotionController controller);

    MotionController getMotionController(int id, Camera owner);

    //TODO setup animation manager of some sort

}