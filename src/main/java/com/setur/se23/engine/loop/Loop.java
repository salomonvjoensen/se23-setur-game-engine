package com.setur.se23.engine.loop;

import java.util.ArrayList;

import com.setur.se23.engine.Collision.Collidable;
import com.setur.se23.engine.Collision.CollisionDetection;
import com.setur.se23.engine.Physics.PhysicsEntity;
import com.setur.se23.engine.core.Core;
import com.setur.se23.engine.core.DynamicEntity;
import com.setur.se23.engine.core.Entity;
import com.setur.se23.engine.core.RenderConfig;

public class Loop implements FrameInterface {

    public static ArrayList<Entity> entities = new ArrayList<>();
    public static ArrayList<PhysicsEntity> physicsEntities = new ArrayList<>();
    public static ArrayList<DynamicEntity> dynamicEntities = new ArrayList<>();
    public static ArrayList<Entity> collidableEntities = new ArrayList<>();

    public static ArrayList<Runnable> gameRunnables = new ArrayList<>();

    public Loop(ArrayList<Entity> entityList, ArrayList<Runnable> runnableList) {
        entities = entityList;
        gameRunnables = runnableList;

        assignPhysics();
        assignDynamics();
        assignCollidables();
    }

    public void assignlists() {
        assignPhysics();
        assignDynamics();
        assignCollidables();
    }

    private void assignPhysics() {

        physicsEntities.clear();

        for (Entity entity : entities) {
            if (entity instanceof PhysicsEntity) {
                physicsEntities.add((PhysicsEntity)(entity));
            }
        }
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

    private long previousTime = 0;

    @Override
    public void update(long currentTime) {
        if (previousTime == 0) {
            previousTime = currentTime;
        }

        double deltaTimeNs = currentTime - previousTime;
        double deltaTimeS = deltaTimeNs / 1_000_000_000.0;

        // Call your update method to update the game state based on deltaTime
        logicLoop(deltaTimeS);
        renderLoop();

        previousTime = currentTime;
    }

    public void logicLoop(double deltaTime) {

        for (PhysicsEntity entity : physicsEntities) {
            entity.updatePhysics(deltaTime);
        }

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
    }

    private void renderLoop() {

        for (Entity entity : entities) {
            entity.renderEntity();
        }

        if (RenderConfig.renderGizmos) {
            for (Entity entity : collidableEntities) {
                ((Collidable) entity).getCollider().RenderGizmo();
            }
        }

        if (RenderConfig.FPS_Counter) {
            Core.debug.checkFPS();
        }
    }
}
