package com.setur.se23.engine.loop;

import java.util.ArrayList;

import com.setur.se23.engine.core.Core;
import com.setur.se23.engine.core.Entity;
import com.setur.se23.engine.render.Renderer;

public class Loop {

    public static ArrayList<Entity> entities = new ArrayList<Entity>();

    private boolean FPS;

    public void sendEntities(ArrayList<Entity> entityList, boolean checkFPS) {
        entities = entityList;
        this.FPS = checkFPS;
    }

    public void update(double deltaTime) {

        for (Entity entity : entities) {
            entity.update(deltaTime);
        }

        render();

        if (FPS) {
            Core.debug.checkFPS();
        }
    }

    private void render() {

        for (Entity entity : entities) {
            entity.renderEntity();
        }
        
        Renderer.getInstance().swapBuffers();
    }
}
