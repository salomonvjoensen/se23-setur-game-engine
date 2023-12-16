package com.setur.se23.snake.Snake_Objects;
import com.setur.se23.engine.Collision.Collidable;
import com.setur.se23.engine.Collision.Collider;
import com.setur.se23.engine.Collision.SquareCollider;
import com.setur.se23.engine.core.Entity;
import com.setur.se23.engine.render.common.Texture2D;
import com.setur.se23.snake.GridUtils;
import com.setur.se23.snake.SnakeGlobals;

/**
 * The abstract class of all 5 snake body parts.
 */
public abstract class SnakeEntity extends Entity implements Collidable{

    private static final double SCALE = SnakeGlobals.SCALE;  // Default 0.1
    public Collider collider;

    /**
     * Constructor. Uses the SquareCollider.
     * 
     * @param texture What body part image is used.
     * @param xPos Sets horizontal position.
     * @param yPos Sets vertical position.
     * @param angle Sets the angle, initially.
     * @param scaleX Sets the horizontal scaling, all the body parts use 10% scaling.
     * @param scaleY Sets the vertical scaling, all the body parts use 10% scaling.
     */
    public SnakeEntity(Texture2D texture, double xPos, double yPos, double angle) {
        super(texture, xPos, yPos, angle, SCALE, SCALE);

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
