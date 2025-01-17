package com.setur.se23.snake.Snake_Objects;

import com.setur.se23.engine.Collision.CircleCollider;
import com.setur.se23.engine.Collision.Collidable;
import com.setur.se23.engine.Collision.Collider;
import com.setur.se23.engine.core.Entity;
import com.setur.se23.engine.core.Resource;
import com.setur.se23.engine.render.common.Texture2D;
import com.setur.se23.snake.SnakeGlobals;

/**
 * The Apple that is to be eaten by the Snake to grow
 */
public class Apple extends Entity implements Collidable{

    private static final double SCALE = SnakeGlobals.SCALE;  // Default 0.1
    Collider collider;

    /**
     * Constructor.
     * 
     * @param xPos Sets horizontal position.
     * @param yPos Sets vertical position.
     */
    public Apple(double xPos, double yPos) {
                super(new Texture2D(Resource.getSprite("apple.png"), 160, 160), 
              xPos, 
              yPos, 
              0,
              SCALE, 
              SCALE);
        
        setCollider(new CircleCollider(this, getHeight() / 2));
    }

    /**
     * Applies the Collider to the Apple
     * 
     * @param collider
     */
    @Override
    public void setCollider(Collider collider) {
        this.collider = collider;
    }


    /**
     * Get method to get the Collider.
     * 
     * @return The Collider.
     */
    @Override
    public Collider getCollider() {
        return collider;
    }

    /**
     * For registering collision, handled in SnakeHead.
     * 
     * @param collisionEntity The collision between entities.
     *                        All handled in SnakeHead.
     */
    @Override
    public void collisionEvent(Entity collisionEntity) {
    }  
}