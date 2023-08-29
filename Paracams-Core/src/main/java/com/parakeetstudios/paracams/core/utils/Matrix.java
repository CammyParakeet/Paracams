package com.parakeetstudios.paracams.core.utils;

import org.bukkit.entity.Entity;
import org.bukkit.util.Transformation;
import org.joml.AxisAngle4f;
import org.joml.Vector3f;

public class Matrix {

    private Transformation T;
    private Vector3f translation = new Vector3f();
    private Vector3f scale = new Vector3f();
    private AxisAngle4f left_rot = new AxisAngle4f();
    private AxisAngle4f right_rot = new AxisAngle4f();

    public Matrix() {
        this.T = new Transformation(translation, left_rot, scale, right_rot);
    }

    public void setTranslation(float t) {
        this.translation = new Vector3f(t);
    }

    public void setTranslation(float tx, float ty, float tz) {
        this.translation = new Vector3f(tx, ty, tz);
    }

    public void setScale(float s) {
        this.scale = new Vector3f(s);
    }

    public void setScale(float sx, float sy, float sz) {
        this.scale = new Vector3f(sx, sy, sz);
    }

    public void setLeftRot(Entity e, float angle) {
        this.left_rot = new AxisAngle4f(angle, e.getLocation().getDirection().toVector3f());
    }

    public void setRightRot(Entity e, float angle) {
        this.right_rot = new AxisAngle4f(angle, e.getLocation().getDirection().toVector3f());
    }

    public Transformation toTransformation() {
        this.T = new Transformation(translation, left_rot, scale, right_rot);
        return this.T;
    }


}
