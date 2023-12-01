package com.setur.se23.engine.Collision;

import com.setur.se23.engine.core.Entity;

public class CircleCollider extends Collider {

    @Override
    public boolean isInArea(Entity a, Entity b) {

        
        return false;
    }

    @Override
    public void RenderGizmo(double entityX, double entityY, int entityWidth, int entityHeight) {
    }
}
