package com.parakeetstudios.paracams.core.camera;

import com.parakeetstudios.paracams.api.camera.Camera;
import com.parakeetstudios.paracams.api.camera.CameraSettings;
import com.parakeetstudios.paracams.api.cinematics.AnimationController;
import com.parakeetstudios.paracams.api.registers.CameraRegistry;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Paracam implements Camera {

    private final int cameraID;
    private int cameraNumber;
    private String name;
    private Location position;
    private Location origin;
    private CameraSettings cameraSettings;
    private boolean displayVisible;
    private boolean showName;
    private List<Player> attachedPlayers;
    private CameraRegistry owningRegistry;
    private List<AnimationController> animationControllers;

    private Entity viewHandle;
    private Entity displayHandle;

    public Paracam(int cameraID, String name, @NotNull Location position, CameraRegistry registry) {
        this.cameraID = cameraID;
        this.name = name;
        this.position = position;
        this.origin = position.clone();
        this.attachedPlayers = new ArrayList<>();
        this.animationControllers = new ArrayList<>();
        this.owningRegistry = registry;
        initializeCamera();
    }

    public Paracam(int cameraID, @NotNull Location position, CameraRegistry registry) {
        this(cameraID, "", position, registry);
    }

    private void initializeCamera() {
        if (this.owningRegistry.getDefaultSettings().isDisplayVisible()) {
            this.displayVisible = true;
            //TODO register display entity
        }
        //TODO registry bat entity for view
    }

    @Override
    public int getCameraID() {
        return this.cameraID;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setPosition(Location location) {
        this.position = location;
    }

    @Override
    public Location getPosition() {
        return this.position;
    }

    @Override
    public void setOrigin(Location location) {

    }

    @Override
    public Location getOrigin() {
        return null;
    }

    @Override
    public void pan() {

    }

    @Override
    public void tilt() {

    }

    @Override
    public void rotate() {

    }

    @Override
    public void showDisplay() {

    }

    @Override
    public void hideDisplay() {

    }

    @Override
    public boolean isDisplayVisible() {
        return false;
    }

    @Override
    public void setCameraSettings(CameraSettings settings) {

    }

    @Override
    public CameraSettings getCameraSettings() {
        return null;
    }

    @Override
    public void attachPlayer(Player player) {

    }

    @Override
    public void detachPlayer(Player player) {

    }

    @Override
    public boolean isPlayerAttached(Player player) {
        return false;
    }

    @Override
    public List<Player> getAttachedPlayers() {
        return null;
    }

    @Override
    public Player getAttachedPlayerByID(UUID id) {
        return null;
    }

    @Override
    public Player getAttachedPlayerByName(String name) {
        return null;
    }

    @Override
    public CameraRegistry getOwningRegistry() {
        return null;
    }

    @Override
    public void createMotionController() {

    }

    @Override
    public void addMotionController(AnimationController controller) {

    }

    @Override
    public AnimationController getMotionController(int id, Camera owner) {
        return null;
    }
}
