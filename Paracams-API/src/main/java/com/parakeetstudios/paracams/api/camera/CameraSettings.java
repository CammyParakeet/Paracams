package com.parakeetstudios.paracams.api.camera;

import com.parakeetstudios.paracams.api.cinematics.InterpolationMode;
import com.parakeetstudios.paracams.api.cinematics.TweeningMode;

public interface CameraSettings {

    double getMovementSpeed();
    void setMovementSpeed(double speed);

    double getRotationSpeed();
    void setRotationSpeed(double speed);

    InterpolationMode getInterpolationMode();
    void setInterpolationMode(InterpolationMode mode);

    TweeningMode getTweeningMode();
    void setTweeningMode(TweeningMode mode);

    boolean isDisplayVisible();
    void setDisplayVisible(boolean isVisible);

    float getDepthOfField();
    void setDepthOfField(float dof);

    long getFadeDuration();
    void setFadeDuration(long duration);

    boolean isNightVision();
    void setNightVision(boolean nv);

}
