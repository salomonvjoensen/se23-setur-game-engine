package com.setur.se23.game;

import java.util.ArrayList;

import com.setur.se23.engine.core.Core;
import com.setur.se23.engine.core.Entity;
import com.setur.se23.engine.debug.CollisionTestObject;
import com.setur.se23.engine.input.Input;
import com.setur.se23.engine.loop.Loop;
import com.setur.se23.game.Flappy_Bird_Objects.Background;
import com.setur.se23.game.Flappy_Bird_Objects.Bird;
import com.setur.se23.game.Flappy_Bird_Objects.Ground;
import com.setur.se23.game.Flappy_Bird_Objects.Pipe;

public class FlappyBird {

    private Input inputSystem;

    public Loop gameLoop = new Loop();

    public FlappyBird() {
        sendGameObjects();
    }

    private void sendGameObjects() {
        gameLoop.sendEntities(createFlappyBirdObjects());
    }

    private ArrayList<Entity> createFlappyBirdObjects() {

        ArrayList<Entity> entities = new ArrayList<Entity>();
        
        entities.add(new Background(0, 0));
        
        entities.add(new Pipe(300, -250, 52, 320, 1.5, 1.68, true));
        entities.add(new Pipe(300, 500, 52, 320, 1.5, 1.68, false));
        entities.add(new Pipe(600, -450, 52, 320, 1.5, 1.68, true));
        entities.add(new Pipe(600, 300, 52, 320, 1.5, 1.68, false));
        entities.add(new Pipe(900, -350, 52, 320, 1.5, 1.68, true));
        entities.add(new Pipe(900, 400, 52, 320, 1.5, 1.68, false));
        entities.add(new Pipe(1200, -100, 52, 320, 1.5, 1.68, true));
        entities.add(new Pipe(1200, 650, 52, 320, 1.5, 1.68, false));

        entities.add(new Ground(0, Core.getStageHeight() - 100, (int) Core.getStageWidth(), 100));
        
        Bird player = new Bird(50, 50, 50, 40);
        CollisionTestObject test = new CollisionTestObject(50, 200, 50, 40);

        createInputs(player, test);

        entities.add(player);
        //entities.add(test);

        return entities;
    }

    private void createInputs(Bird player, CollisionTestObject test) {
        inputSystem = new Input(new GameEvents(player, test, () -> sendGameObjects()));

        inputSystem.addInputs();
    }
}
