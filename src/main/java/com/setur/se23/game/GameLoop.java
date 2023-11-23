package com.setur.se23.game;

import java.util.ArrayList;

import com.setur.se23.engine.core.Core;
import com.setur.se23.engine.core.Entity;
import com.setur.se23.engine.input.Input;
import com.setur.se23.engine.render.Renderer;
import com.setur.se23.game.Flappy_Bird_Objects.Background;
import com.setur.se23.game.Flappy_Bird_Objects.Bird;
import com.setur.se23.game.Flappy_Bird_Objects.Ground;
import com.setur.se23.game.Flappy_Bird_Objects.Pipe;

public class GameLoop {

    private ArrayList<Entity> pipes = new ArrayList<Entity>();

    private Background background;
    private Ground ground;
    private Bird player;

    private Input inputSystem;

    public GameLoop() {
        background = new Background(0, 0);
        
        pipes.add(new Pipe(300, 300, 80, -270 * 2));
        pipes.add(new Pipe(300, 500, 80, 270 * 2));
        pipes.add(new Pipe(600, 100, 80, -270 * 2));
        pipes.add(new Pipe(600, 300, 80, 270 * 2));
        pipes.add(new Pipe(900, 200, 80, -270 * 2));
        pipes.add(new Pipe(900, 400, 80, 270 * 2));
        pipes.add(new Pipe(1200, 450, 80, -270 * 2));
        pipes.add(new Pipe(1200, 650, 80, 270 * 2));

        ground = new Ground(0, Core.getStageHeight() - 100, (int) Core.getStageWidth(), 0);
        
        player = new Bird(50, 50, 50, 40);

        inputSystem = new Input(new GameEvents(player));

        inputSystem.addInputs();

    }

    public void update(double deltaTime) {

        int random = 0;

        for (Entity entity : pipes) {
            entity.update(deltaTime);
            if (entity.getX() < -100) {
                if (random == 0) {
                    entity.setX(entity.getX() + 1200);

                    random = Core.randomInt(1, 10);
                    entity.setY(random * 50);
                } else {
                    entity.setX(entity.getX() + 1200);

                    entity.setY(random * 50 + 200);
                    random = 0;
                }
                
            }
        }

        player.update(deltaTime);

        render();
    }

    private void render() {

        background.renderEntity();

        for (Entity entity : pipes) {
            entity.renderEntity();
        }

        ground.renderEntity();

        player.renderEntity();
        
        Renderer.getInstance().swapBuffers();
    }
}
