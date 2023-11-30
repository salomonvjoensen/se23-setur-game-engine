package com.setur.se23.engine.Collision;

import com.setur.se23.engine.core.Entity;

public class SquareCollider extends Collider {

    public double width;
    public double height;

    public SquareCollider(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public boolean isInArea(Entity a, Entity b) {


        return false;
    }

    @Override
    public void RenderGizmo() {
    }
}
