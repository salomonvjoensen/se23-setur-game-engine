package com.setur.se23.engine.loop;

import java.util.ArrayList;

import com.setur.se23.engine.Collision.Collidable;
import com.setur.se23.engine.Collision.CollisionDetection;
import com.setur.se23.engine.core.Core;
import com.setur.se23.engine.core.Entity;
import com.setur.se23.engine.render.Renderer;

public class Loop extends FX_FrameUpdate {

    public static ArrayList<Entity> entities = new ArrayList<Entity>();
    public static ArrayList<Entity> collidableEntities = new ArrayList<Entity>();

    public void sendEntities(ArrayList<Entity> entityList) {
        entities = entityList;

        assignCollidebles();
    }

    private void assignCollidebles() {

        collidableEntities.clear();

        for (Entity entity : entities) {
            if (entity instanceof Collidable) {
                collidableEntities.add(entity);
            }
        }
    }

    public void update(double deltaTime) {

        for (Entity entity : entities) {
            entity.update(deltaTime);
        }


        for (Entity firstEntity : collidableEntities) {

            for (Entity secondEntity : collidableEntities) {
                if (firstEntity.equals(secondEntity) == false && CollisionDetection.checkForCollision(firstEntity, secondEntity)) {
                    ((Collidable) firstEntity).collisionEvent(secondEntity);
                }
            }
        }


        render();

        if (Core.FPS_Counter) {
            Core.debug.checkFPS();
        }
    }

    private void render() {

        for (Entity entity : entities) {
            entity.renderEntity();
        }

        for (Entity entity : collidableEntities) {
            if (Core.renderGizmos) {
                ((Collidable) entity).getCollider().RenderGizmo(entity.getX(), entity.getY(), entity.getWidth(), entity.getHeight());
            }
        }
        
        Renderer.getInstance().swapBuffers();
    }
}
