package com.parakeetstudios.paracams.core.camera;

import com.parakeetstudios.paracams.api.camera.Camera;
import com.parakeetstudios.paracams.api.camera.CameraSettings;
import com.parakeetstudios.paracams.api.cinematics.AnimationController;
import com.parakeetstudios.paracams.api.registers.CameraRegistry;
import com.parakeetstudios.paracams.core.utils.DefaultEntities;
import org.bukkit.GameMode;
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

    private Entity viewEntityHandle;
    private Entity displayEntityHandle;

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
            this.displayEntityHandle = DefaultEntities.createDisplay(position, null);
        }
        //TODO registry bat entity for view
        this.viewEntityHandle = DefaultEntities.createBat(position, null);
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
        this.origin = location;
    }

    @Override
    public Location getOrigin() {
        return this.origin;
    }

    @Override
    public void pan() {
        //TODO pan mechanics
    }

    @Override
    public void tilt() {
        //TODO tilt mechanics;
    }

    @Override
    public void rotate() {
        //TODO rotation mechanics;
    }

    @Override
    public void showDisplay() {
        this.viewEntityHandle.setCustomNameVisible(true);
        this.displayEntityHandle.setCustomNameVisible(true);
        this.displayVisible = true;
        //TODO proper display mechanics
    }

    @Override
    public void hideDisplay() {
        this.viewEntityHandle.setCustomNameVisible(false);
        this.displayEntityHandle.setCustomNameVisible(false);
        this.displayVisible = false;
    }

    @Override
    public boolean isDisplayVisible() {
        return this.displayVisible;
    }

    @Override
    public void setCameraSettings(CameraSettings settings) {
        this.cameraSettings = settings;
    }

    @Override
    public CameraSettings getCameraSettings() {
        return this.cameraSettings;
    }

    @Override
    public void attachPlayer(Player player) {
        player.setGameMode(GameMode.SPECTATOR);
        player.setSpectatorTarget(viewEntityHandle);
        attachedPlayers.add(player);
        //TODO extra handling
    }

    @Override
    public void detachPlayer(Player player) {
        attachedPlayers.remove(player);
        //player.setGameMode(GameMode.ADVENTURE);
        //TODO proper handling of detaching
        // this will require some setting management/event handling
    }

    @Override
    public boolean isPlayerAttached(Player player) {
        return (attachedPlayers.contains(player));
    }

    @Override
    public List<Player> getAttachedPlayers() {
        return this.attachedPlayers;
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
        return this.owningRegistry;
    }

    @Override
    public void createMotionController() {
        //TODO animationcontrolling?
    }

    @Override
    public void addMotionController(AnimationController controller) {
        //TODO
    }

    @Override
    public AnimationController getMotionController(int id, Camera owner) {
        //TODO
        return null;
    }
}
