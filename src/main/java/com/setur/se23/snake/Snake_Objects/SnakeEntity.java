package com.setur.se23.snake.Snake_Objects;
import com.setur.se23.engine.Collision.Collidable;
import com.setur.se23.engine.Collision.Collider;
import com.setur.se23.engine.core.Entity;
import com.setur.se23.engine.render.common.Material;

import com.setur.se23.snake.GridUtils;

public abstract class SnakeEntity extends Entity implements Collidable{

    public Collider collider;

    public SnakeEntity(Material material, double xPos, double yPos, double angle, double scaleX, double scaleY) {
            super(material, xPos, yPos, angle, scaleX, scaleY);
    }
    
    public void setPosition(double gridX, double gridY, double angle) {
        double[] stageCoordinates = GridUtils.gridToStageCoordinates((int)gridX, (int)gridY);
        this.xPos = stageCoordinates[0];
        this.yPos = stageCoordinates[1];
        this.angle = angle;
    }

    @Override
    public void setCollider(Collider collider) {
        this.collider = collider;
    }


    @Override
    public Collider getCollider() {
        return collider;
    }

    @Override
    public void collisionEvent(Entity collisionEntity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'collisionEvent'");
    }    
}
