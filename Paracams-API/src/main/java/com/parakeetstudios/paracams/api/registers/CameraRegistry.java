package com.parakeetstudios.paracams.api.registers;

import com.parakeetstudios.paracams.api.camera.Camera;
import com.parakeetstudios.paracams.api.camera.CameraSettings;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

public interface CameraRegistry {

    Camera createCamera(Location position);
    Camera createCamera(String name, Location position);
    Camera createCamera(Location position, Color color);
    Camera createCamera(String name, Location position, Color color);
    //TODO any other creation conditions

    void registerCamera(Camera cam);
    void remove(int camID);
    void remove(Camera cam);

    Optional<Camera> getCamera(int camID);
    Optional<Camera> getCameraOnPlayer(Player player);
    Optional<Camera> getNearestCamera(Location location);
    List<Camera> getAllCameras();
    List<Camera> searchCameras(Predicate<Camera> predicate);

    void showCamera(int camID);
    void hideCamera(int camID);
    void showAllCameras();
    void hideAllCameras();
    void showAllCamerasToPlayer(Player p);
    void hideAllCamerasFromPlayer(Player p);
    void hideAllViewHandlesFromPlayer(Player p);

    void activateCamera(int camID);
    void pauseCamera(int camID);
    void deactivateCamera(int camID);
    void resetCamera(int camID);
    boolean isActive(int camID);

    void setDefaultSettings(CameraSettings settings);
    CameraSettings getDefaultSettings();

    int count();
    void clear();

    void addListener(); //TODO
    // TODO CameraRegistryListener ...

    void update(int camID, CameraSettings settings); //TODO do we need?
    void updateAll(Function<Camera, Camera> updateFunction);

}
