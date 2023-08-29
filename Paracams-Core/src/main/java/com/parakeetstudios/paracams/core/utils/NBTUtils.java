package com.parakeetstudios.paracams.core.utils;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.server.commands.TagCommand;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

public class NBTUtils {

    public static ItemStack SkullOwnerNBT(ItemStack skull, SkullData data) {

        net.minecraft.world.item.ItemStack nmsSkull = CraftItemStack.asNMSCopy(skull);
        CompoundTag compoundTag = (nmsSkull.hasTag()) ? nmsSkull.getTag() : new CompoundTag();

        CompoundTag skullOwner = new CompoundTag();
        skullOwner.putIntArray("Id", data.id());

        ListTag textures = new ListTag();
        CompoundTag textureVal = new CompoundTag();
        textureVal.putString("Value", data.texture());
        textures.add(textureVal);

        CompoundTag properties = new CompoundTag();
        properties.put("textures", textures);

        skullOwner.put("Properties", properties);

        compoundTag.put("SkullOwner", skullOwner);

        nmsSkull.setTag(compoundTag);

        return CraftItemStack.asBukkitCopy(nmsSkull);
    }

}
