package com.parakeetstudios.paracams.core.camera;

import com.parakeetstudios.paracams.api.ParacamsAPI;
import com.parakeetstudios.paracams.api.camera.Camera;
import com.parakeetstudios.paracams.api.camera.CameraSettings;
import com.parakeetstudios.paracams.api.cinematics.AnimationController;
import com.parakeetstudios.paracams.api.registers.CameraRegistry;
import com.parakeetstudios.paracams.api.utils.ViewAxis;

import com.parakeetstudios.paracams.core.handlers.PlayerHandlers;
import com.parakeetstudios.paracams.core.registers.PlayerStateRegistry;
import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.TextDisplay;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.*;

import static com.parakeetstudios.paracams.core.utils.DefaultEntities.*;
import static com.parakeetstudios.paracams.core.utils.MathUtils.clamp;

@SuppressWarnings("UnstableApiUsage")
public class Paracam implements Camera {

    private final int cameraID;
    private final int cameraNumber;   // TODO how will we handle this
    private String name;
    private Location position;
    private Location origin;
    private CameraSettings cameraSettings;
    private final List<Player> attachedPlayers = new ArrayList<>();
    private final PlayerStateRegistry playerStates = new PlayerStateRegistry();
    private final CameraRegistry owningRegistry;
    private final Plugin owningPlugin;
    private final List<AnimationController> animationControllers = new ArrayList<>();; //TODO setup how we do this
    private Color color;

    private boolean isCustomNamed;
    private boolean displayVisible;
    private boolean nameVisible;
    private boolean isMoving;

    private Entity viewEntityHandle;
    private Entity displayEntityHandle;
    private TextDisplay displayName;

    public Paracam(int cameraID, String name, @NotNull Location position, CameraRegistry registry, Color color) {
        this.cameraID = cameraID;
        this.position = position;
        this.origin = position.clone();
        this.owningRegistry = registry;
        this.cameraNumber = this.getOwningRegistry().count()+1;
        this.color = (color != null) ? color : Color.WHITE;
        this.isCustomNamed = (name != null);
        this.name = this.isCustomNamed ? name : "cam" + cameraNumber;
        this.owningPlugin = ParacamsAPI.getInstance().getPlugin();
    }

    public Paracam(int cameraID, @NotNull Location position, CameraRegistry registry, Color color) {
        this(cameraID, "", position, registry, color);
    }

    private void initializeCamera() {
        this.viewEntityHandle = createArmorStandAnchor(position, null);
        this.displayEntityHandle = createCamDisplay(this, DisplayType.HEAD);
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
        this.isCustomNamed = true;
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setPosition(Location location) {
        updatePlayerCameras();

        viewEntityHandle.teleport(location);

        // translation to correct display
        Location displayLocation = location.clone().add(0, 1.75, 0);
        displayEntityHandle.teleport(displayLocation);

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
    public void pan(float deg, long duration) {
        rotate(deg, 0, 0, duration, ViewAxis.Y);
    }

    @Override
    public void tilt(float deg, long duration) {
        rotate(0, deg, 0, duration, ViewAxis.X);
    }

    @Override
    public void roll(float deg, long duration) {
        rotate(0, 0, deg, duration, ViewAxis.Z);
    }

    @Override
    public void rotate(float yaw, float pitch, float roll, long duration, ViewAxis axis) {
        Location newPos = position;
        newPos.setYaw(position.getYaw() + yaw);
        newPos.setPitch(position.getPitch() + pitch);

        setPosition(newPos);
    }


    @Override
    public void showDisplay() {
        this.displayEntityHandle.setCustomNameVisible(true);
        this.displayEntityHandle.setVisibleByDefault(true);
        this.displayVisible = true;
        //TODO test if this works well
    }

    @Override
    public void hideDisplay() {
        this.displayEntityHandle.setCustomNameVisible(false);
        this.displayEntityHandle.setVisibleByDefault(false);
        this.displayVisible = false;
    }

    @Override
    public boolean isDisplayVisible() {
        return this.displayVisible;
    }

    @Override
    public void showDisplayToPlayer(Player p) {
        p.showEntity(owningPlugin, displayEntityHandle);
    }

    @Override
    public void hideDisplayFromPlayer(Player p) {
        p.hideEntity(owningPlugin, displayEntityHandle);
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
        attachedPlayers.add(player);
        playerStates.storePlayerState(player, true);

        PlayerHandlers.setCamera(player, viewEntityHandle, owningPlugin, 30L);

        // Hide display from viewers
        hideDisplayFromPlayer(player);
        //TODO extra handling
    }

    private void updatePlayer(Player player) {

    }

    @Override
    public void hideViewForPlayer(Player p) {
        //p.hideEntity(owningPlugin, this.viewEntityHandle);
    }


    @Override
    public void detachPlayer(Player player) {
        attachedPlayers.remove(player);
        playerStates.restorePlayerState(player);

        // TODO if conditions for display
        showDisplayToPlayer(player);
    }


    @Override
    public boolean isPlayerAttached(Player player) {
        return (attachedPlayers.contains(player));
    }

    @Override
    public void updatePlayerCameras() {
        attachedPlayers.forEach(player -> {
            //TODO do we need this still
        });
    }

    @Override
    public List<Player> getAttachedPlayers() {
        return this.attachedPlayers;
    }

    //TODO do we actually need these ones
    @Override
    public Player getAttachedPlayerByID(UUID id) {
        if (attachedPlayers.contains(owningPlugin.getServer().getPlayer(id))) {
            return owningPlugin.getServer().getPlayer(id);
        } else {
            return null; // TODO sort this out better
        }
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
