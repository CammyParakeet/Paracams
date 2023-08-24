package com.parakeetstudios.paracams.api.cinematics;

public enum InterpolationMode {

    LINEAR("Linear"),
    EASE_IN("Ease In"),
    EASE_OUT("Ease Out"),
    EASE_IN_OUT("Ease In Out"),
    BOUNCE("Bounce"),
    ELASTIC("Elastic");

    private final String displayName;

    InterpolationMode(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

}
