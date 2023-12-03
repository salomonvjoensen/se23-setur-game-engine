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
        background = new Background();
        
        pipes.add(new Pipe(true, 300, -250));
        pipes.add(new Pipe(false, 300, 500));
        pipes.add(new Pipe(true, 600, -450));
        pipes.add(new Pipe(false, 600, 300));
        pipes.add(new Pipe(true, 900, -350));
        pipes.add(new Pipe(false, 900, 400));
        pipes.add(new Pipe(true, 1200, -100));
        pipes.add(new Pipe(false, 1200, 650));

        ground = new Ground();
        
        player = new Bird(50, 50);
        player.setAngle(90);

        inputSystem = new Input(new GameEvents(player, null, () -> update(0)));

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
