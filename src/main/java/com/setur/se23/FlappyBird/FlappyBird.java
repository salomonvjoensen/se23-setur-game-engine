package com.setur.se23.FlappyBird;

import java.util.ArrayList;

import com.setur.se23.FlappyBird.Flappy_Bird_Objects.Background;
import com.setur.se23.FlappyBird.Flappy_Bird_Objects.Bird;
import com.setur.se23.FlappyBird.Flappy_Bird_Objects.Ground;
import com.setur.se23.FlappyBird.Flappy_Bird_Objects.Pipe;
import com.setur.se23.engine.core.Core;
import com.setur.se23.engine.core.Entity;
import com.setur.se23.engine.debug.CollisionTestObject;
import com.setur.se23.engine.input.FX_Input;
import com.setur.se23.engine.input.Input;
import com.setur.se23.engine.loop.Loop;

public class FlappyBird {

    private Input inputSystem;

    public Loop gameLoop = new Loop();

    public FlappyBird() {
        sendGameObjects();
    }

    private void sendGameObjects() {
        gameLoop.sendScene(createFlappyBirdObjects(), getRunnables());
    }

    private ArrayList<Entity> createFlappyBirdObjects() {

        // entities are rendered in list order
        // i.e. last in list is rendered over the others

        ArrayList<Entity> entities = new ArrayList<Entity>();
        
        entities.add(new Background());

        addPipes(entities, 4);

        entities.add(new Ground());
        
        Bird player = new Bird(50, 50);
        CollisionTestObject testObject = new CollisionTestObject(50, 200);

        createInputs(player, testObject);

        entities.add(player);
        //entities.add(testObject);

        return entities;
    }

    private void addPipes(ArrayList<Entity> entities, int pairAmount) {

        int spacing = 300;

        double random = Core.randomDouble(1, 8);

        for (int i = 0; i < pairAmount; i++) {

            entities.add(new Pipe(true,  spacing,  (random * 50 - 500)));
            entities.add(new Pipe(false, spacing,  (random * 50 + 250)));

            spacing += 300;
            random = Core.randomDouble(1, 8);
        }
    }

    private void createInputs(Bird player, CollisionTestObject test) {
        inputSystem = new FX_Input(new GameEvents(player, test, () -> sendGameObjects()));

        inputSystem.addInputs();
    }



    private ArrayList<Runnable> getRunnables() {
        ArrayList<Runnable> runnables = new ArrayList<Runnable>();

        runnables.add(() -> Pipe.loopAroundPipes());

        return runnables;
    }
}
