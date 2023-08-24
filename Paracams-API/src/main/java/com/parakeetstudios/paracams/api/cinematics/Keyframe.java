package com.parakeetstudios.paracams.api.cinematics;

import org.bukkit.Location;
import org.bukkit.util.EulerAngle;

public record Keyframe(int id, Location position, EulerAngle rotation, float scale, int renderDistance){}
//TODO fix this up to what we actually need
