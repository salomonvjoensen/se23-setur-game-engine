package com.setur.se23.engine.core;

import java.util.ArrayList;

import com.setur.se23.engine.render.BufferItem;
import com.setur.se23.engine.render.Renderer;
import com.setur.se23.engine.render.common.Texture2D;

public abstract class Entity {
    protected ArrayList<Texture2D> textureList = new ArrayList<>();
    protected int currentTextureIndex;
    protected double xPos;
    protected double yPos;
    protected double angle;
    protected double scaleX;
    protected double scaleY;
    
    /**
     * Constructor for Entity
     * 
     * @param texture starting texture
     * @param xPos x position
     * @param yPos y position
     * @param angle rotation angle
     * @param scaleX scaling amount on X axis
     * @param scaleY scaling amount on Y axis
     */
    public Entity(Texture2D texture, double xPos, double yPos, double angle, double scaleX, double scaleY) {
        this.textureList.add(texture);
        this.currentTextureIndex = 0;
        this.xPos = xPos;
        this.yPos = yPos;
        this.angle = angle;
        this.scaleX = scaleX;
        this.scaleY = scaleY;

        BufferItem bufferItem = new BufferItem(texture, xPos, yPos, angle, scaleX, scaleY);

        Renderer.getInstance().allocateTexture(bufferItem.texture());
    }

    /**
     * Method for adding an additional texture to the entity.
     * 
     * @param texture new texture
     */
    public void addTexture(Texture2D texture) {
        this.textureList.add(texture);

        BufferItem bufferItem = new BufferItem(texture, xPos, yPos, angle, scaleX, scaleY);

        Renderer.getInstance().allocateTexture(bufferItem.texture());
    }

    /**
     * Method for changing to additional textures.
     * 
     * @param index texture index
     */
    public void changeTexture(int index) {
        currentTextureIndex = index;
    }

    /**
     * Method for getting current texture
     * 
     * @return current texture
     */
    public Texture2D getCurrentTexture() {
        return textureList.get(currentTextureIndex);
    }

    /**
     * Method for getting xPos
     * 
     * @return xPos
     */
    public double getX() {
        return xPos;
    }

    /**
     * Method for getting yPos
     * 
     * @return yPos
     */
    public double getY() {
        return yPos;
    }

    /**
     * Method for setting xPos
     * 
     * @param xPos
     */
    public void setX(double xPos) {
        this.xPos = xPos;
    }

    /**
     * Method for setting yPos
     * 
     * @param yPos
     */
    public void setY(double yPos) {
        this.yPos = yPos;
    }

    /**
     * Method for getting rotation angle
     * 
     * @return rotation angle
     */
    public double getAngle() {
        return angle;
    }

    /**
     * Method for setting rotation angle
     * 
     * @param angle rotation angle
     */
    public void setAngle(double angle) {
        this.angle = angle;
    }

    /**
     * Method for scaling X and Y with a scaling factor.
     * 
     * @param scalingFactor
     */
    public void uniformScale(double scalingFactor) {
        setScaleX(getScaleX() * scalingFactor);
        setScaleY(getScaleY() * scalingFactor);
    }

    /**
     * Method for scaling X with a scaling factor.
     * 
     * @param scalingFactor
     */
    public void scaleX(double scalingFactor) {
        setScaleX(getScaleX() * scalingFactor);
    }

    /**
     * Method for scaling Y with a scaling factor.
     * 
     * @param scalingFactor
     */
    public void scaleY(double scalingFactor) {
        setScaleY(getScaleY() * scalingFactor);
    }

    /**
     * Method for setting X scaling.
     * 
     * @param scalingFactor
     */
    public void setScaleX(double scaleX) {
        this.scaleX = scaleX;
    }

    /**
     * Method for setting Y scaling.
     * 
     * @param scalingFactor
     */
    public void setScaleY(double scaleY) {
        this.scaleY = scaleY;
    }

    /**
     * Method for getting X scaling.
     * 
     * @return scaleX
     */
    public double getScaleX() {
        return scaleX;
    }

    /**
     * Method for getting Y scaling.
     * 
     * @return scaleY
     */
    public double getScaleY() {
        return scaleY;
    }

    /**
     * Method for getting Texture Width.
     * 
     * @return current Texture Width
     */
    public int getTextureWidth() {
        return getCurrentTexture().width();
    }

    /**
     * Method for getting Texture Height.
     * 
     * @return current Texture Height
     */
    public int getTextureHeight() {
        return getCurrentTexture().height();
    }

    /**
     * Method for getting Entity Width.
     * 
     * @return current Entity Width
     */
    public int getWidth() {
        return (int)(getTextureWidth() * scaleX);
    }

    /**
     * Method for getting Entity Height.
     * 
     * @return current Entity Height
     */
    public int getHeight() {
        return (int)(getTextureHeight() * scaleY);
    }

    /**
     * Method for setting scale X to be desired width.
     * 
     * @param width desired width
     */
    public void setWidth(int width) {
        setScaleX(width / getTextureWidth());
    }

    /**
     * Method for setting scale Y to be desired height.
     * 
     * @param width desired height
     */
    public void setHeight(int height) {
        setScaleX(height / getTextureHeight());
    }

    /**
     * render Entity texture with xPos, yPos, angle, scaleX and scaleY
     */
    public void renderEntity() {
        BufferItem bufferItem = new BufferItem(textureList.get(currentTextureIndex), xPos, yPos, angle, scaleX, scaleY);
        
        Renderer.getInstance().render(bufferItem);
    }
}
