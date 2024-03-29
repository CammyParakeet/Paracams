package com.parakeetstudios.paracams.api.cinematics;

import com.parakeetstudios.paracams.api.camera.Camera;

import java.util.List;

public interface Animation {

    List<Keyframe> getKeyframes();

    Keyframe getKeyframe(int id);

    void updateKeyframe(int id);

    void createKeyframe();
    void addKeyframe();

    void loadKeyframes();
    void saveKeyframes();

    void setOwningCamera(Camera owner);
    //TODO decide how to handle relative vs absolute keyframe positions
    // this will also be relevant to if we want to clone animations and give it a new owning camera
    // animations should only play for the camera owned
    Camera getOwningCamera();

    void setInterpolationMode(InterpolationMode mode);
    InterpolationMode getInterpolationMode();

    void setTweeingMode(TweeningMode mode);
    TweeningMode getTweeningMode();

}
