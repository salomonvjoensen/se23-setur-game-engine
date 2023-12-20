package com.setur.se23.snake.Snake_Objects;
import com.setur.se23.engine.Collision.Collidable;
import com.setur.se23.engine.Collision.Collider;
import com.setur.se23.engine.Collision.SquareCollider;
import com.setur.se23.engine.audio.SoundEffectsManager;
import com.setur.se23.engine.core.Entity;
import com.setur.se23.engine.render.common.Texture2D;
import com.setur.se23.snake.GridUtils;
import com.setur.se23.snake.SnakeGameGUI;
import com.setur.se23.snake.SnakeGlobals;
import com.setur.se23.snake.SoundEffects;

/**
 * The class of all 5 snake body parts.
 */
public class Snake extends Entity implements Collidable{

    private int directionX = 1;  // Initially moving right.
    private int directionY;

    private int prevDirectionX = 1; // Last movement was right.
    private int prevDirectionY = 0;

    private boolean isMovingX = true;
    private boolean isMovingY = false;
    private boolean isAlive = true;
    private boolean appleEaten = false;
      private boolean isHead = false;

    private static final double SCALE = SnakeGlobals.SCALE;  // Default 0.1
    private Collider collider;

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
    public Snake(Texture2D texture, double xPos, double yPos, double angle) {
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
     * Moving upwards, decreasing Y-coordinate by 1,
     * by updating vertical direction.
     * 
     * @param moving Boolean.
     */
    public void movingUp(boolean moving) {
        if (moving && isMovingX) {
            directionX = 0;
            directionY = -1; // Upwards movement decreases Y-coordinate
        }
    }

    /**
     * Moving left, decreasing X-coordinate by 1,
     * by updating horizontal direction.
     * 
     * @param moving Boolean.
     */
    public void movingLeft(boolean moving) {
        if (moving && isMovingY) {
            directionX = -1; // Leftwards movement decreases X-coordinate
            directionY = 0;
        }
    }

    /**
     * Moving down, increasing Y-coordinate by 1,
     * by updating vertical direction.
     * 
     * @param moving Boolean.
     */
    public void movingDown(boolean moving) {
        if (moving && isMovingX) {
            directionX = 0;
            directionY = 1; // Downwards movement increases Y-coordinate
        }
    }

    /**
     * Moving right, increasing X-coordinate by 1m
     * by updating horizontal direction.
     * 
     * @param moving Boolean.
     */
    public void movingRight(boolean moving) {
        if (moving && isMovingY) {
            directionX = 1; // Rightwards movement increases X-coordinate
            directionY = 0;
        }
    }

    /**
     * Get method.
     * 
     * @return Horizontal direction.
     */
    public int getDirectionX(){
        return directionX;
    }

    /**
     * Get method.
     * 
     * @return Vertical direction.
     */
    public int getDirectionY() {
        return directionY;
    }

    /**
     * Get method. Used to calculate bendy
     * snake bodypart.
     * 
     * @return Previous horizontal direction.
     */
    public int getPrevDirectionX() {
        return prevDirectionX;
    }

    /**
     * Get method. Used to calculate bendy
     * snake bodypart.
     * 
     * @return Previous vertical direction.
     */

    public int getPrevDirectionY() {
        return prevDirectionY;
    }

    /**
     * Set method for horizontal direction.
     * 
     * @param direction Horizontal direction.
     */
    public void setDirectionX(int direction) {
        directionX = direction;
    }

    /**
     * Set method for vertical direction.
     * 
     * @param direction vertical direction.
     */
    public void setDirectionY(int direction) {
        directionY = direction;
    }

    /**
     * Old directions updated,
     * used in calculating which
     * bendy snake bodypart to use.
     */
    public void updateDirection() {
        prevDirectionX = directionX;
        prevDirectionY = directionY;
    }

    /**
     * Get method to check for if direction was changed.
     * 
     * @return Boolean for checking if the previous direction has been changed
     */
    public boolean isDirectionChanged() {
        return prevDirectionX != directionX || prevDirectionY != directionY;
    }

    /**
     * Get method.
     * 
     * @return Boolean to check for if snake is moving in the horizontal direction.
     */
    public boolean getIsMovingX() {
        return isMovingX;
    }

    /**
     * Get method.
     * 
     * @return Boolean to check for if snake is moving in the vertical direction.
     */
    public boolean getIsMovingY() {
        return isMovingY;
    }

    /**
     * Set method, interchange between moving from/to vertical to horizontal.
     * 
     * @param movingX Boolean for horizontal movement.
     */
    public void setIsMovingX(boolean movingX) {
        isMovingX = movingX;
        isMovingY = !movingX;
    }

    /**
     * Set method, interchange between moving from/to horizontal to vertical.
     * 
     * @param movingY Boolean for vertical movement.
     */
    public void setIsMovingY(boolean movingY) {
        isMovingY = movingY;
        isMovingX = !movingY;
    }

    /**
     * Set method, boolean for snake head, used in collision check for apple.
     * 
     * @param isHead
     */
    public void isHead(boolean isHead) {
        this.isHead = isHead;
    }
    
    /**
     * Set method.
     * 
     * @param eating Boolean for eating Apple.
     */
    public void setAppleEaten(boolean eating) {
        appleEaten = eating;
    }
    
    /**
     * Get method.
     * 
     * @return Boolean for eating Apple.
     */
    public boolean isAppleEaten() {
        return appleEaten;
    }

    /**
     * Get method used in collision check.
     * 
     * @return Boolean for if snake is alive.
     */
    public boolean isAlive() {
        return isAlive;
    }
    
    /**
     * For registering collision.
     * 
     * @param collisionEntity Collision either between Apple or the snake's own body.
     */
    @Override
    public void collisionEvent(Entity collisionEntity) {
        if (collisionEntity instanceof Apple && !appleEaten && isHead) {
            SoundEffectsManager.playLoaded(SoundEffects.EAT_APPLE.getFilePath());
            appleEaten = true;
        }

        if (collisionEntity instanceof Snake && isAlive) {
            SoundEffectsManager.playLoaded(SoundEffects.SNAKE_HISS.getFilePath());
            SnakeGameGUI.gameOver();
            SnakeGameGUI.setGUI();
            isAlive = false;
        }
    }

}
