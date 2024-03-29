package com.parakeetstudios.paracams.core.utils;

import com.destroystokyo.paper.profile.PlayerProfile;
import com.destroystokyo.paper.profile.ProfileProperty;
import com.parakeetstudios.paracams.core.camera.Paracam;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.minecraft.world.entity.animal.Panda;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Objects;
import java.util.UUID;

public class DefaultEntities {

    private static final String camera1Skin = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjgwNWQ1NWYyMWI0OWEwNzRjZDVlM2RjMjQ0YTVhMDcwZTU1NDRiNTRmYTkyNTRkMmRjMmUxOGYxZTY4MDJmOSJ9fX0=";
    private static final SkullData camera1SD = new SkullData(
            new int[]{1172424783,-966964153,-1790414813,311583781},
            camera1Skin);

    public enum DisplayType {
        DRAGON(Material.DRAGON_HEAD),
        HEAD(Material.PLAYER_HEAD);

        private final Material type;

        DisplayType(Material type) {
            this.type = type;
        }
        public Material getType() {
            return type;
        }
    }


    public static ArmorStand createArmorStandAnchor(Location location, String viewSettings) {
        ArmorStand anchor = location.getWorld().spawn(location, ArmorStand.class);

        anchor.setInvisible(true);
        //anchor.setMarker(true);
        anchor.setBasePlate(false);
        anchor.setGravity(false);
        anchor.setPersistent(false);
        anchor.setRemoveWhenFarAway(false);
        anchor.setAI(false);
        anchor.setCanPickupItems(false);
        anchor.getChunk().setForceLoaded(true);

        anchor.addScoreboardTag("Paracam");
        anchor.addScoreboardTag("ParacamAnchor");

        return anchor;
    }


    //TODO test if this works as well as armorstand
    public static Entity createBatAnchor(Location location, String viewSettings) {
        //TODO setup settings logic
        Bat batHandle = (Bat) location.getWorld().spawnEntity(location, EntityType.BAT);

        // remove vanilla settings and AI
        batHandle.setAI(false);
        batHandle.setSilent(true);
        batHandle.setGravity(false);
        batHandle.setAwake(true);
        batHandle.setInvulnerable(true);
        batHandle.setCanPickupItems(false);

        batHandle.setCustomNameVisible(false);

        // not persistent by default
        batHandle.setPersistent(false);

        // invisible by default
        // TODO - on a player without certain permission has updategamemode to spectate event, hide the entity from that player
        // TODO - since the ghost will still show
        batHandle.setInvisible(true);

        // glowing for dev?
        batHandle.setGlowing(false);

        batHandle.addScoreboardTag("Paracam");
        batHandle.addScoreboardTag("ParacamView");
        return batHandle;
    }


    public static TextDisplay createDisplayName(String name, Location location, Color color) {
        Matrix M = new Matrix();

        M.setTranslation(0, 1.75f, 0);

        TextDisplay textDisplay = location.getWorld().spawn(location, TextDisplay.class);

        textDisplay.text(Component.text(name));
        textDisplay.setTransformation(M.toTransformation());


        return textDisplay;
    }


    public static Entity createCamDisplay(Paracam cam, DisplayType type) {
        Matrix M = new Matrix();
        ItemStack displayItem = new ItemStack(type.getType());

        //TODO better logic for this later on
        if (type.getType() == Material.PLAYER_HEAD) {
            displayItem = NBTUtils.SkullOwnerNBT(displayItem, camera1SD);
            M.setScale(0.45f, 0.45f, 0.65f);
        } else {
            // scale the camera - eventually from config
            M.setScale(.25f, .25f, .2f);
        }

        //M.setTranslation(0, 1.75f, 0);

        // set and spawn the display entity
        Location location = cam.getPosition();
        ItemDisplay displayHandle = (ItemDisplay) location.getWorld().spawnEntity(location, EntityType.ITEM_DISPLAY);
        displayHandle.setItemStack(displayItem);

        // teleport is needed as far as I know to update the direction vector
        displayHandle.teleport(location.clone().add(0, 1.75, 0));

        // set the transformation
        displayHandle.setTransformation(M.toTransformation());

        // display to FIXED ensures the display faces the correct direction of the entity
        displayHandle.setItemDisplayTransform(ItemDisplay.ItemDisplayTransform.FIXED);


        // glowing by default
        displayHandle.setGlowing(true);
        // gray glow color by default
        displayHandle.setGlowColorOverride(cam.getColor());

        // not persistent by default
        displayHandle.setPersistent(false);

        displayHandle.setInvulnerable(true);

        // simple camera icon name by default - color only applies to icon by default
        displayHandle.customName(Component.text("\uD83D\uDCF7")
                .color(TextColor.color(cam.getColor().asRGB()))
                .append(Component.text(" " + ((cam.isCustomNamed()) ? cam.getName() : cam.getCameraNumber()))
                        .color(TextColor.color(0xFFFFFF))));
        // name visible by default
        displayHandle.setCustomNameVisible(true);

        displayHandle.addScoreboardTag("Paracam");
        displayHandle.addScoreboardTag("ParacamDisplay");

        return displayHandle;
    }


}
