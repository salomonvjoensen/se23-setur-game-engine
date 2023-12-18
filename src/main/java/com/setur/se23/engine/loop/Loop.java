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

    /**
     * Constructor for loop.
     * 
     * @param entityList all objects in an arraylist
     * @param runnableList all runnables in an arraylist
     */
    public Loop(ArrayList<Entity> entityList, ArrayList<Runnable> runnableList) {
        entities = entityList;
        gameRunnables = runnableList;

        assignlists();
    }

    /**
     * Method for starting all sorting methods
     */
    public void assignlists() {
        assignPhysics();
        assignDynamics();
        assignCollidables();
    }

    /**
     * Method to add all instances of PhysicsEntity to 
     * physicsEntities ArrayList.
     */
    private void assignPhysics() {

        physicsEntities.clear();

        for (Entity entity : entities) {
            if (entity instanceof PhysicsEntity) {
                physicsEntities.add((PhysicsEntity)(entity));
            }
        }
    }

    /**
     * Method to add all instances of DynamicEntity to 
     * dynamicEntities ArrayList.
     */
    private void assignDynamics() {

        dynamicEntities.clear();

        for (Entity entity : entities) {
            if (entity instanceof DynamicEntity) {
                dynamicEntities.add((DynamicEntity)(entity));
            }
        }
    }

    /**
     * Method to add all instances of Collidable to 
     * collidableEntities ArrayList.
     */
    private void assignCollidables() {

        collidableEntities.clear();

        for (Entity entity : entities) {
            if (entity instanceof Collidable) {
                collidableEntities.add(entity);
            }
        }
    }

    private long previousTime = 0;

    /**
     * Mehtod to update loop.
     */
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

    /**
     * Method to run all logic updates with deltatime.
     * 
     * @param deltaTime
     */
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

    /**
     * Method to run all render updates.
     */
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
