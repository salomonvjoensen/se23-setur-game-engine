package com.setur.se23.game;

import java.util.ArrayList;

import com.setur.se23.engine.core.Core;
import com.setur.se23.engine.core.Entity;
import com.setur.se23.engine.input.Input;
import com.setur.se23.engine.loop.Loop;
import com.setur.se23.game.Flappy_Bird_Objects.Background;
import com.setur.se23.game.Flappy_Bird_Objects.Bird;
import com.setur.se23.game.Flappy_Bird_Objects.Ground;
import com.setur.se23.game.Flappy_Bird_Objects.Pipe;

public class FlappyBird {

    private Input inputSystem;

    private Loop gameLoop = new Loop();

    public FlappyBird() {
        gameLoop.sendEntities(createFlappyBirdObjects(), true);
    }

    public void update(double deltaTimeS) {
        gameLoop.update(deltaTimeS);
    }

    private ArrayList<Entity> createFlappyBirdObjects() {

        ArrayList<Entity> entities = new ArrayList<Entity>();
        
        entities.add(new Background(0, 0));
        
        entities.add(new Pipe(300, -250, 80, 270 * 2, true));
        entities.add(new Pipe(300, 500, 80, 270 * 2, false));
        entities.add(new Pipe(600, -450, 80, 270 * 2, true));
        entities.add(new Pipe(600, 300, 80, 270 * 2, false));
        entities.add(new Pipe(900, -350, 80, 270 * 2, true));
        entities.add(new Pipe(900, 400, 80, 270 * 2, false));
        entities.add(new Pipe(1200, -100, 80, 270 * 2, true));
        entities.add(new Pipe(1200, 650, 80, 270 * 2, false));

        entities.add(new Ground(0, Core.getStageHeight() - 100, (int) Core.getStageWidth(), 100));
        
        Bird player = new Bird(50, 50, 50, 40);

        createInputs(player);

        entities.add(player);

        return entities;
    }

    private void createInputs(Bird player) {
        inputSystem = new Input(new GameEvents(player));

        inputSystem.addInputs();
    }
}
