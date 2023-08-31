package com.parakeetstudios.paracams.core.registers;

import com.parakeetstudios.paracams.core.handlers.PlayerState;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerStateRegistry {

    private final Map<UUID, PlayerState> storedStates = new HashMap<>();

    public void storePlayerState(Player player) {
        storedStates.put(player.getUniqueId(), new PlayerState(player));
    }

    public void storePlayerState(Player player, boolean toClear) {
        PlayerState state = new PlayerState(player);
        if (toClear) state.clearExistence(player);
        storedStates.put(player.getUniqueId(), state);
    }

    public void restorePlayerState(Player player) {
        PlayerState state = storedStates.remove(player.getUniqueId());
        if (state != null) state.restoreExistence(player);
    }

    public boolean hasStoredState(Player player) {
        return storedStates.containsKey(player.getUniqueId());
    }

}
