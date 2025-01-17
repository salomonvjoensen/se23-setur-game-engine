package com.setur.se23.engine.Collision;

import com.setur.se23.engine.core.Entity;

public interface Collidable {
    public void setCollider(Collider collider);
    public Collider getCollider();
    public void collisionEvent(Entity collisionEntity);
}

