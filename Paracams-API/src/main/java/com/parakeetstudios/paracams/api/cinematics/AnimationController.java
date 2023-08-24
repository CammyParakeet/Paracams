package com.parakeetstudios.paracams.api.cinematics;

import com.parakeetstudios.paracams.api.camera.Camera;

import java.util.List;
import java.util.Map;

public interface AnimationController {

    Map<Integer, List<Keyframe>> getAnimations();

    void addAnimation(int id, List<Keyframe> animation);

    List<Keyframe> getAnimation(int id);



}
