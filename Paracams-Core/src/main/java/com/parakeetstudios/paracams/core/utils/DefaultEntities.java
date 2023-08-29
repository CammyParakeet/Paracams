package com.parakeetstudios.paracams.core.utils;

import com.parakeetstudios.paracams.api.ParacamsAPI;
import com.parakeetstudios.paracams.core.camera.Paracam;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Transformation;
import org.bukkit.util.Vector;
import org.joml.AxisAngle4f;
import org.joml.Vector3f;

public class DefaultEntities {

    public static Entity createBat(Location location, String viewSettings) {
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
        batHandle.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, -1, 1, true, false, false));

        // glowing for dev
        batHandle.setGlowing(false);

        batHandle.addScoreboardTag("Paracam");
        batHandle.addScoreboardTag("ParacamBat");
        return batHandle;
    }


    public static Entity createSimpleDisplay(Location location, String displaySettings) {
        return null;
    }


    public static Entity createDragonDisplay(Paracam cam) {
        Matrix M = new Matrix();

        Location location = cam.getPosition();

        //TODO settings setup
        ItemDisplay displayHandle = (ItemDisplay) location.getWorld().spawnEntity(location, EntityType.ITEM_DISPLAY);
        // type maybe eventually from config?
        displayHandle.setItemStack(new ItemStack(Material.DRAGON_HEAD));

        // scale the camera - eventually from config
        M.setTranslation(0, 0, -.15f);
        M.setScale(.25f, .25f, .2f);
        displayHandle.setTransformation(M.toTransformation());

        // display to FIXED ensures the display faces the correct direction of the entity
        displayHandle.setItemDisplayTransform(ItemDisplay.ItemDisplayTransform.FIXED);

        // teleport is needed as far as I know to update the direction vector
        displayHandle.teleport(location);

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
