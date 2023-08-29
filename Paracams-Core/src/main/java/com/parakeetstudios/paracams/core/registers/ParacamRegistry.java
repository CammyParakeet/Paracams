package com.parakeetstudios.paracams.core.registers;

import com.parakeetstudios.paracams.api.ParacamsAPI;
import com.parakeetstudios.paracams.api.camera.Camera;
import com.parakeetstudios.paracams.api.camera.CameraSettings;
import com.parakeetstudios.paracams.api.registers.CameraRegistry;
import com.parakeetstudios.paracams.api.registers.SceneRegistry;
import com.parakeetstudios.paracams.core.camera.Paracam;
import com.parakeetstudios.paracams.core.utils.MathUtils;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * This should be a registry for all cameras on the server used
 * currently using integer id's (potentially move to UUID)
 * {@link SceneRegistry} should be used to create sequenced cameras
 */
public class ParacamRegistry implements CameraRegistry {

    private ConcurrentHashMap<Integer, Camera> cameras = new ConcurrentHashMap<>();

    private Paracam createParacam(int id, String name, @NotNull Location position, Color color) {
        return new Paracam(id, name, position, this, color);
    }

    @Override
    public Camera createCamera(Location position) {
        return createCamera(null, position, null);
    }

    @Override
    public Camera createCamera(String name, Location position) { return createCamera(name, position, null); }

    @Override
    public Camera createCamera(Location position, Color color) { return createCamera(null, position, color); }

    @Override
    public Camera createCamera(String name, Location position, Color color) {
        Objects.requireNonNull(position, "Position cannot be null");
        // id limit in config?
        Paracam cam = createParacam(MathUtils.genRandInt(1000), name, position, color);
        registerCamera(cam);    // register instantly by default

        return cam;
    }

    @Override
    public void registerCamera(Camera cam) { this.cameras.put(cam.getCameraID(), cam); }

    @Override
    public void remove(int camID) {
        try {
            cameras.get(camID).despawn();
            cameras.remove(camID);
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
    public Optional<Camera> getCamera(int camID) {
        return Optional.ofNullable(this.cameras.get(camID));
    }

    @Override
    public Optional<Camera> getCameraOnPlayer(Player player) {
        return cameras.values().stream()
                .filter(cam -> cam.isPlayerAttached(player))
                .findFirst();
    }

    @Override
    public Optional<Camera> getNearestCamera(Location location) {
        return cameras.values().stream()
                .min(Comparator.comparingDouble(cam -> cam.getPosition().distanceSquared(location)));
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
    public void showAllCamerasToPlayer(Player p) { getAllCameras().forEach(cam -> cam.showDisplayToPlayer(p)); }

    @Override
    public void hideAllCamerasFromPlayer(Player p) {

    }

    @Override
    public void hideAllViewHandlesFromPlayer(Player p) { getAllCameras().forEach(cam -> cam.hideViewForPlayer(p)); }

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
        cameras.values().forEach(Camera::despawn);
        cameras = null;
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
