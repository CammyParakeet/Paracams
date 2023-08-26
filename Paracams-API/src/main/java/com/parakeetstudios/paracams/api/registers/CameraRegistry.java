package com.parakeetstudios.paracams.api.registers;

import com.parakeetstudios.paracams.api.camera.Camera;
import com.parakeetstudios.paracams.api.camera.CameraSettings;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public interface CameraRegistry {

    void spawnCamera(Location position);
    void spawnCamera(String name, Location position);
    void registerCamera(Camera cam);
    void remove(int camID);
    void remove(Camera cam);

    Camera getCamera(int camID);
    Camera getCameraOnPlayer(Player player);
    Camera getNearestCamera(Location location);
    List<Camera> getAllCameras();
    List<Camera> searchCameras(Predicate<Camera> predicate);

    void showCamera(int camID);
    void hideCamera(int camID);
    void showAllCameras();
    void hideAllCameras();

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
