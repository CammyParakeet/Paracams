package com.parakeetstudios.paracams.core.camera;

import com.parakeetstudios.paracams.api.ParacamsAPI;
import com.parakeetstudios.paracams.api.camera.Camera;
import com.parakeetstudios.paracams.api.camera.CameraSettings;
import com.parakeetstudios.paracams.api.cinematics.AnimationController;
import com.parakeetstudios.paracams.api.registers.CameraRegistry;
import com.parakeetstudios.paracams.api.utils.ViewAxis;

import com.parakeetstudios.paracams.core.nms.EntityPacketWrapper;
import com.parakeetstudios.paracams.core.utils.Paralog;
import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
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
    private final Map<Player, GameMode> attachedPlayers = new HashMap<>();
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
//        if (this.owningRegistry.getDefaultSettings().isDisplayVisible()) {
//            this.displayVisible = true;
//            this.displayEntityHandle = DefaultEntities.createDisplay(position, null);
//        }
        this.viewEntityHandle = createBat(position, null);
        Paralog.info(viewEntityHandle.getUniqueId().toString());
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
        viewEntityHandle.teleport(location);
        displayEntityHandle.teleport(location);
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
    public void rotate(float yaw, float pitch, float deg, long duration, ViewAxis axis) {
        Location newPos = position;
        newPos.setYaw(yaw);
        newPos.setPitch(pitch);

        setPosition(newPos);
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
        attachedPlayers.put(player, player.getGameMode());
        player.setGameMode(GameMode.SPECTATOR);
        player.setSpectatorTarget(viewEntityHandle);

        // Hide display from viewers
        hideDisplayFromPlayer(player);
        //TODO extra handling
    }

    @Override
    public void hideViewForPlayer(Player p) {
        //p.hideEntity(owningPlugin, this.viewEntityHandle);
    }


    @Override
    public void detachPlayer(Player player) {
        detachPlayer(player, attachedPlayers.get(player));
    }

    @Override
    public void detachPlayer(Player player, GameMode mode) {
        attachedPlayers.remove(player);
        player.setGameMode(mode);
        showDisplayToPlayer(player);
    }

    @Override
    public boolean isPlayerAttached(Player player) {
        return (attachedPlayers.containsKey(player));
    }

    @Override
    public void updatePlayerCameras() {
        attachedPlayers.keySet().forEach(player -> {
            player.sendMessage("Are you spectating?");
            EntityPacketWrapper.rotateEntity(player, player.getLocation().getYaw(), player.getLocation().getPitch());
        });
    }

    @Override
    public Map<Player, GameMode> getAttachedPlayers() {
        return this.attachedPlayers;
    }

    //TODO do we actually need these ones
    @Override
    public Player getAttachedPlayerByID(UUID id) {
        if (attachedPlayers.containsKey(owningPlugin.getServer().getPlayer(id))) {
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
