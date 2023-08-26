package com.parakeetstudios.paracams.hook;

import com.parakeetstudios.paracams.api.ParacamsAPI;
import com.parakeetstudios.paracams.core.registers.ParacamRegistry;
import com.parakeetstudios.paracams.core.utils.Paralog;
import org.bukkit.plugin.java.JavaPlugin;

public class ParacamPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        Paralog.init(getLogger());
        Paralog.info(this.getName() + " is starting...");

        ParacamsAPI.getInstance().onEnable(new ParacamRegistry());

        Paralog.info(this.getName() + " has been initialized");
    }

    @Override
    public void onDisable() {
        Paralog.info(this.getName() + " is shutting down");
        ParacamsAPI.getInstance().onDisable();
    }

}
