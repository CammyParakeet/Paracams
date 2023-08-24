package com.parakeetstudios.paracams.api.cinematics;

public enum TweeningMode {

    LINEAR("Linear"),
    QUADRATIC("Quadratic"),
    CUBIC("Cubic"),
    QUARTIC("Quartic"),
    QUINTIC("Quintic"),
    BEZIER("Bezier"),
    SINE("Sine"),
    CIRCULAR("Circular"),
    EXPONENTIAL("Exponential"),
    ELASTIC("Elastic"),
    BOUNCE("Bounce"),
    BACK("Back");

    private final String displayName;

    TweeningMode(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

}
