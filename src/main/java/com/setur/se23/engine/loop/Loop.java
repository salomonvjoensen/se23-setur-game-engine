package com.setur.se23.engine.loop;

import java.util.ArrayList;

import com.setur.se23.engine.Collision.Collidable;
import com.setur.se23.engine.Collision.CollisionDetection;
import com.setur.se23.engine.core.Core;
import com.setur.se23.engine.core.DynamicEntity;
import com.setur.se23.engine.core.Entity;
import com.setur.se23.engine.render.Renderer;

public class Loop extends FX_FrameUpdate {

    public static ArrayList<Entity> entities = new ArrayList<Entity>();
    public static ArrayList<DynamicEntity> dynamicEntities = new ArrayList<DynamicEntity>();
    public static ArrayList<Entity> collidableEntities = new ArrayList<Entity>();

    public static ArrayList<Runnable> gameRunnables = new ArrayList<Runnable>();

    public void sendScene(ArrayList<Entity> entityList, ArrayList<Runnable> runnableList) {
        entities = entityList;
        gameRunnables = runnableList;

        assignDynamics();
        assignCollidables();
    }

    private void assignDynamics() {

        dynamicEntities.clear();

        for (Entity entity : entities) {
            if (entity instanceof DynamicEntity) {
                dynamicEntities.add((DynamicEntity)(entity));
            }
        }
    }

    private void assignCollidables() {

        collidableEntities.clear();

        for (Entity entity : entities) {
            if (entity instanceof Collidable) {
                collidableEntities.add(entity);
            }
        }
    }

    public void logicLoop(double deltaTime) {

        for (DynamicEntity entity : dynamicEntities) {
            entity.update(deltaTime);
        }


        for (Entity firstEntity : collidableEntities) {

            for (Entity secondEntity : collidableEntities) {

                if (firstEntity.equals(secondEntity) == false && CollisionDetection.checkForCollision(firstEntity, secondEntity)) {
                    ((Collidable) firstEntity).collisionEvent(secondEntity);
                }
            }
        }

        for (Runnable function : gameRunnables) {
            function.run();
        }


        renderLoop();
    }

    private void renderLoop() {

        for (Entity entity : entities) {
            entity.renderEntity();
        }

        if (Core.renderGizmos) {
            for (Entity entity : collidableEntities) {
                ((Collidable) entity).getCollider().RenderGizmo();
            }
        }

        if (Core.FPS_Counter) {
            Core.debug.checkFPS();
        }

        Renderer.getInstance().swapBuffers();
    }
}
