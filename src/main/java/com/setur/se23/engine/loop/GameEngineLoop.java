package com.setur.se23.engine.loop;

import java.util.ArrayList;

import com.setur.se23.engine.render.BufferItem;
import com.setur.se23.engine.render.Renderer;
import com.setur.se23.engine.render.common.Material;
import com.setur.se23.engine.render.common.MaterialColour;
import com.setur.se23.engine.render.common.Texture2D;

public class GameEngineLoop {

    private static final double SPEED = 100;

    ArrayList<BufferItem> items = new ArrayList<BufferItem>();

    public double _xDir = 0;
    public double _yDir = 0;

    public GameEngineLoop() {
        items.add(new BufferItem(new Material(
                        new Texture2D("file:src/main/resources/sprites/pipe-green.png", 80, -270),
                        new MaterialColour(1.0f, 1.0f, 1.0f, 1.0f)), 
                  300, 
                  200));
    
        items.add(new BufferItem(new Material(
                        new Texture2D("file:src/main/resources/sprites/pipe-green.png", 80, 270),
                        new MaterialColour(1.0f, 1.0f, 1.0f, 1.0f)), 
                  300, 
                  500));

        items.add(new BufferItem(new Material(
                        new Texture2D("file:src/main/resources/sprites/flappy-bird.png", 40, 30),
                        new MaterialColour(1.0f, 0.0f, 0.0f, 1.0f)), 
                  10, 
                  10));
        
        for (BufferItem item : items) {
            Renderer.getInstance().allocateTexture(item.material().texture());
        }
    }

    public void update(double deltaTime) {

        double xPos = items.get(2).x();
        double yPos = items.get(2).y();

        xPos += _xDir * SPEED * deltaTime;
        yPos += _yDir * SPEED * deltaTime;

        items.remove(2);

        items.add(new BufferItem(new Material(
                        new Texture2D("file:src/main/resources/sprites/flappy-bird.png", 40, 30),
                        new MaterialColour(1.0f, 0.0f, 0.0f, 1.0f)), 
                  xPos, 
                  yPos));

        render();
    }

    private void render() {

        Renderer.getInstance().render(items);
        
        Renderer.getInstance().swapBuffers();
    }
}
