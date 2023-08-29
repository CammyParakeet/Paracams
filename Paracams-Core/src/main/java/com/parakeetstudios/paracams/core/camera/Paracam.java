package com.parakeetstudios.paracams.core.camera;

import com.parakeetstudios.paracams.api.ParacamsAPI;
import com.parakeetstudios.paracams.api.camera.Camera;
import com.parakeetstudios.paracams.api.camera.CameraSettings;
import com.parakeetstudios.paracams.api.cinematics.AnimationController;
import com.parakeetstudios.paracams.api.registers.CameraRegistry;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.parakeetstudios.paracams.core.utils.DefaultEntities.*;
import static com.parakeetstudios.paracams.core.utils.MathUtils.clamp;

@SuppressWarnings("UnstableApiUsage")
public class Paracam implements Camera {

    private final int cameraID;
    private int cameraNumber;   // TODO how will we handle this
    private String name;
    private Location position;
    private Location origin;
    private CameraSettings cameraSettings;
    private final List<Player> attachedPlayers;
    private final CameraRegistry owningRegistry;
    private List<AnimationController> animationControllers; //TODO setup how we do this
    private Color color;

    private boolean isCustomNamed;
    private boolean displayVisible;
    private boolean nameVisible;
    private boolean isMoving;

    private Entity viewEntityHandle;
    private Entity displayEntityHandle;

    public Paracam(int cameraID, String name, @NotNull Location position, CameraRegistry registry, Color color) {
        this.cameraID = cameraID;
        this.position = position;
        this.origin = position.clone();
        this.attachedPlayers = new ArrayList<>();
        this.animationControllers = new ArrayList<>();
        this.owningRegistry = registry;
        this.cameraNumber = this.getOwningRegistry().count()+1;
        this.color = (color != null) ? color : Color.WHITE;
        this.isCustomNamed = (name != null);
        this.name = this.isCustomNamed ? name : "cam" + cameraNumber;
    }

    public Paracam(int cameraID, @NotNull Location position, CameraRegistry registry, Color color) {
        this(cameraID, "", position, registry, color);
    }

    private void initializeCamera() {
//        if (this.owningRegistry.getDefaultSettings().isDisplayVisible()) {
//            this.displayVisible = true;
//            this.displayEntityHandle = DefaultEntities.createDisplay(position, null);
//        }
        this.viewEntityHandle = createBat(position, null);
        this.displayEntityHandle = createDragonDisplay(this);
    }

    public boolean isCustomNamed() {
        return this.isCustomNamed;
    }

    public int getCameraNumber() {
        return this.cameraNumber;
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
    public void spawn() {
        initializeCamera();
    }

    @Override
    public void despawn() {
        this.viewEntityHandle.remove();
        this.displayEntityHandle.remove();
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
        this.displayEntityHandle.setVisibleByDefault(true);
        this.displayVisible = true;
        //TODO test if this works well
    }

    @Override
    public void hideDisplay() {
        this.viewEntityHandle.setCustomNameVisible(false);
        this.displayEntityHandle.setVisibleByDefault(false);
        this.displayVisible = false;
    }

    @Override
    public boolean isDisplayVisible() {
        return this.displayVisible;
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public Color getColor() {
        return this.color;
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
        player.showEntity(ParacamsAPI.getInstance().getPlugin(), viewEntityHandle);
        player.setSpectatorTarget(viewEntityHandle);
        player.hideEntity(ParacamsAPI.getInstance().getPlugin(), viewEntityHandle);
        attachedPlayers.add(player);
        //TODO extra handling
    }

    @Override
    public void detachPlayer(Player player) {
        attachedPlayers.remove(player);
        //player.setGameMode(GameMode.ADVENTURE);   //TODO needs to get their beforehand game mode somehow
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

    @Override
    public boolean isPlayingAnimation() {
        return false;
    }


    @Override
    public String simpleString() {
        return "Paracam{" +
                "ID: " + cameraID +
                ", name: " + name +
                ", position: " + "[" + clamp(position.getX(), 2) +
                    ", " + clamp(position.getY(), 2) +
                    ", " + clamp(position.getZ(), 2) +
                    "]" +
                '}';
    }

    //TODO chat hover to get more info, and click event to tp to camera
    @Override
    public String toString() {
        return getName();
    }

}
