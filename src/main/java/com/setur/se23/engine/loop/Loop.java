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
    public static ArrayList<Runnable> gameFunctions = new ArrayList<Runnable>();

    public void sendScene(ArrayList<Entity> entityList, ArrayList<Runnable> functionList) {
        entities = entityList;
        gameFunctions = functionList;

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

    public void logicLoop(double deltaTime) {

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

        for (Runnable function : gameFunctions) {
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
