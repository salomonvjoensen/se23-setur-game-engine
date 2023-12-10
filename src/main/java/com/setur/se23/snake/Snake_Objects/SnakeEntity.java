package com.setur.se23.snake.Snake_Objects;
import com.setur.se23.engine.Collision.Collidable;
import com.setur.se23.engine.Collision.Collider;
import com.setur.se23.engine.Collision.SquareCollider;
import com.setur.se23.engine.core.Entity;
import com.setur.se23.engine.render.common.Material;

import com.setur.se23.snake.GridUtils;

/**
 * The abstract class of all 5 snake body parts.
 */
public abstract class SnakeEntity extends Entity implements Collidable{

    public Collider collider;

    /**
     * Constructor. Uses the SquareCollider.
     * 
     * @param material What body part image is used.
     * @param xPos Sets horizontal position.
     * @param yPos Sets vertical position.
     * @param angle Sets the angle, initially.
     * @param scaleX Sets the horizontal scaling, all the body parts use 10% scaling.
     * @param scaleY Sets the vertical scaling, all the body parts use 10% scaling.
     */
    public SnakeEntity(Material material, double xPos, double yPos, double angle) {
        super(material, xPos, yPos, angle, 0.1, 0.1);

        setCollider(new SquareCollider(this, getWidth(), getHeight()));
    }
    
    /**
     * The logical translation between grid and stage, and angle.
     * 
     * @param gridX The corresponding horizontal position on the grid.
     * @param gridY The corresponding vertical position on the grid.
     * @param angle The angle of the snake bodypart.
     */
    public void setPosition(double gridX, double gridY, double angle) {
        this.xPos = GridUtils.gridToStageCoordinateX((int)gridX);
        this.yPos = GridUtils.gridToStageCoordinateY((int)gridY);
        this.angle = angle;
    }

    /**
     * Sets the Collider.
     * 
     * @param collider
     */
    @Override
    public void setCollider(Collider collider) {
        this.collider = collider;
    }


    /**
     * Get method for the Collider. Uses Square Collider.
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
