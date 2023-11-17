package com.setur.se23.game;

import java.util.ArrayList;

import com.setur.se23.engine.core.Entity;
import com.setur.se23.engine.render.Renderer;
import com.setur.se23.game.Flappy_Bird_Objects.Background;
import com.setur.se23.game.Flappy_Bird_Objects.Bird;
import com.setur.se23.game.Flappy_Bird_Objects.Pipe;

public class GameLoop {

    private ArrayList<Entity> entities = new ArrayList<Entity>();

    public GameLoop() {
        entities.add(new Background(0, 0));
        
        entities.add(new Pipe(300, 200, 0, 80, -270));
        
        entities.add(new Pipe(300, 500, 0, 80, 270));
        
        entities.add(new Bird(10, 10, 100));
    }

    public void update(double deltaTime) {

        for (Entity entity : entities) {
            entity.update(deltaTime);
        }

        render();
    }

    private void render() {

        for (Entity entity : entities) {
            entity.renderEntity();
        }
        
        Renderer.getInstance().swapBuffers();
    }
}
