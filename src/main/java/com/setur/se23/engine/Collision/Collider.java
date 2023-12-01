package com.setur.se23.engine.Collision;

import com.setur.se23.engine.core.Entity;

public abstract class Collider {
    public abstract boolean isInArea(Entity a, Entity b);
    public abstract void RenderGizmo(double entityX, double entityY, int entityWidth, int entityHeight);
}
