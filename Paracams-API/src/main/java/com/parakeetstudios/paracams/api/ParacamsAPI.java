package com.parakeetstudios.paracams.api;

import com.parakeetstudios.paracams.api.registers.CameraRegistry;
import org.bukkit.plugin.Plugin;

public final class ParacamsAPI {

    private CameraRegistry cameraRegistry;
    private final Object lock = new Object();
    private Plugin plugin;

    private ParacamsAPI(){};

    private static final class InstanceHolder {
        private static final ParacamsAPI instance = new ParacamsAPI();
    }

    public static ParacamsAPI getInstance() {
        return InstanceHolder.instance;
    }

    public Plugin getPlugin() { return this.plugin; }

    public CameraRegistry getCameraRegistry() { return this.cameraRegistry; }

    public void onEnable(CameraRegistry registry, Plugin plugin) {
        synchronized (lock) {
            if (this.cameraRegistry == null) {
                this.cameraRegistry = registry;
                this.plugin = plugin;
            } else {
                throw new IllegalStateException("ParacamsAPI has already been enabled");
            }
        }
    }

    public synchronized void onDisable() {
        synchronized (lock) {
            if (this.cameraRegistry != null) {
                this.cameraRegistry.clear();
                this.cameraRegistry = null;
            }
        }
    }

}
