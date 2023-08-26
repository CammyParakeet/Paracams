package com.parakeetstudios.paracams.core.registers;

import com.parakeetstudios.paracams.api.camera.Camera;
import com.parakeetstudios.paracams.api.camera.CameraSettings;
import com.parakeetstudios.paracams.api.registers.CameraRegistry;
import com.parakeetstudios.paracams.api.registers.SceneRegistry;
import com.parakeetstudios.paracams.core.camera.Paracam;
import com.parakeetstudios.paracams.core.utils.MathUtils;
import com.parakeetstudios.paracams.core.utils.Paralog;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * This should be a registry for all cameras on the server used
 * currently using integer id's (potentially move to UUID)
 * {@link SceneRegistry} should be used to create sequenced cameras
 */
public class ParacamRegistry implements CameraRegistry {

    private final ConcurrentHashMap<Integer, Camera> cameras = new ConcurrentHashMap<>();

    private Paracam createParacam(int id, String name, @NotNull Location position) {
        return new Paracam(id, name, position, this);
    }

    @Override
    public Camera createCamera(Location position) {
        return createCamera(null, position);
    }

    @Override
    public Camera createCamera(String name, Location position) {
        Objects.requireNonNull(position, "Position cannot be null");

        Paracam cam = createParacam(MathUtils.genRandInt(), name, position);
        registerCamera(cam);

        return cam;
    }

    @Override
    public void registerCamera(Camera cam) {
        this.cameras.put(cam.getCameraID(), cam);
    }

    @Override
    public void remove(int camID) {
        try {
            this.cameras.remove(camID);
        } catch (Exception e) {
            //TODO correct handle
            e.printStackTrace();
        }
    }

    @Override
    public void remove(Camera cam) {
        remove(cam.getCameraID());
    }

    @Override
    public Camera getCamera(int camID) {
        return this.cameras.get(camID);
    }

    @Override
    public Camera getCameraOnPlayer(Player player) {
        //TODO think about how to handle this logic
        // do we try assign a camera to the player object somehow?
        // or search cameras for their assigned players?
        return null;
    }

    @Override
    public Camera getNearestCamera(Location location) {
        //TODO location comparison logic
        this.cameras.values().forEach(c -> {

        });

        return null;
    }

    @Override
    public List<Camera> getAllCameras() {
        return this.cameras.values().stream().toList();
    }

    @Override
    public List<Camera> searchCameras(Predicate<Camera> predicate) {
        //TODO search logic
        return null;
    }

    @Override
    public void showCamera(int camID) {
        this.cameras.get(camID).showDisplay();
    }

    @Override
    public void hideCamera(int camID) {
        this.cameras.get(camID).hideDisplay();
    }

    @Override
    public void showAllCameras() {
        this.cameras.values().forEach(Camera::showDisplay);
    }

    @Override
    public void hideAllCameras() {
        this.cameras.values().forEach(Camera::hideDisplay);
    }

    @Override
    public void activateCamera(int camID) {
        //TODO start animations
    }

    @Override
    public void pauseCamera(int camID) {
        //TODO pause animation
    }

    @Override
    public void deactivateCamera(int camID) {
        //TODO ?? needed? or is this same as pause
    }

    @Override
    public void resetCamera(int camID) {
        //TODO reset position to origin
    }

    @Override
    public boolean isActive(int camID) {
        return this.cameras.get(camID).isPlayingAnimation();
    }

    @Override
    public void setDefaultSettings(CameraSettings settings) {
        //TODO settings/config logic
    }

    @Override
    public CameraSettings getDefaultSettings() {
        return null;
    }

    @Override
    public int count() {
        return this.cameras.size();
    }

    @Override
    public void clear() {
        //TODO should we be clearing a final field
        // or make it not final?
        //this.cameras = null;
    }

    @Override
    public void addListener() {

    }

    @Override
    public void update(int camID, CameraSettings settings) {

    }

    @Override
    public void updateAll(Function<Camera, Camera> updateFunction) {

    }
}
