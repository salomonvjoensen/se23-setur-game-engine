package com.setur.se23.game;

import java.util.ArrayList;

import com.setur.se23.engine.Collision.CollisionDetection;
import com.setur.se23.engine.core.Core;
import com.setur.se23.engine.core.Entity;
import com.setur.se23.engine.input.Input;
import com.setur.se23.engine.render.Renderer;
import com.setur.se23.game.Flappy_Bird_Objects.Background;
import com.setur.se23.game.Flappy_Bird_Objects.Bird;
import com.setur.se23.game.Flappy_Bird_Objects.Ground;
import com.setur.se23.game.Flappy_Bird_Objects.Pipe;

public class GameLoop {

    private boolean gameOver = false;

    private ArrayList<Entity> pipes = new ArrayList<Entity>();

    private Background background;
    private Ground ground;
    private Bird player;

    private Input inputSystem;

    public GameLoop() {
        background = new Background(0, 0);
        
        pipes.add(new Pipe(300, -250, 80, 270 * 2, true));
        pipes.add(new Pipe(300, 500, 80, 270 * 2, false));
        pipes.add(new Pipe(600, -450, 80, 270 * 2, true));
        pipes.add(new Pipe(600, 300, 80, 270 * 2, false));
        pipes.add(new Pipe(900, -350, 80, 270 * 2, true));
        pipes.add(new Pipe(900, 400, 80, 270 * 2, false));
        pipes.add(new Pipe(1200, -100, 80, 270 * 2, true));
        pipes.add(new Pipe(1200, 650, 80, 270 * 2, false));

        ground = new Ground(0, Core.getStageHeight() - 100, (int) Core.getStageWidth(), 100);
        
        player = new Bird(50, 50, 50, 40);
        player.setAngle(90);

        inputSystem = new Input(new GameEvents(player));

        inputSystem.addInputs();

    }

    public void update(double deltaTime) {

        updatePipes(deltaTime);

        player.update(deltaTime);

        for (Entity pipe : pipes) {
            if (CollisionDetection.checkForCollision(pipe, player)) {
                gameOver = true;
            }
        }

        if (gameOver) {
            //Core.debug.warning("Game over");
        }

        render();

        Core.debug.checkFPS();
    }

    private void updatePipes(double deltaTime) {
        int random = 0;

        for (Entity pipe : pipes) {
            pipe.update(deltaTime);
            if (pipe.getX() < -100) {
                if (random == 0) {
                    pipe.setX(pipe.getX() + 1200);

                    random = Core.randomInt(1, 9);
                    pipe.setY(-500 + random * 50);
                } else {
                    pipe.setX(pipe.getX() + 1200);

                    pipe.setY(random * 50 + 250);
                    random = 0;
                }
                
            }
        }
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
