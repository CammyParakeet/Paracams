package com.parakeetstudios.paracams.core.utils;

import net.kyori.adventure.text.Component;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

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
        batHandle.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, -1, 1, true, false, false));
        // glowing for dev
        batHandle.setGlowing(true);

        batHandle.addScoreboardTag("Paracam");
        return batHandle;
    }

    public static Entity createSimpleDisplay(Location location, String displaySettings) {
        return null;
    }

    public static Entity createDragonDisplay(Location location, String displaySettings) {
        //TODO settings setup
        ItemDisplay displayHandle = (ItemDisplay) location.getWorld().spawnEntity(location, EntityType.ITEM_DISPLAY);
        displayHandle.setItemStack(new ItemStack(Material.DRAGON_HEAD));

        displayHandle.setDisplayHeight(0.3f);
        displayHandle.setDisplayWidth(0.3f);

        //displayHandle.setTransformation();

        // glowing by default
        displayHandle.setGlowing(true);
        // gray glow color by default
        displayHandle.setGlowColorOverride(Color.GRAY);

        // not persistent by default
        displayHandle.setPersistent(false);

        displayHandle.setInvulnerable(true);

        // simple camera icon name by default
        displayHandle.customName(Component.text("\uD83D\uDCF7"));
        // name visible by default
        displayHandle.setCustomNameVisible(true);

        displayHandle.addScoreboardTag("ParacamDisplay");
        return displayHandle;
    }


}
