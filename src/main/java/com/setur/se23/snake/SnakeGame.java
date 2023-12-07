package com.setur.se23.snake;

import java.util.ArrayList;

import com.setur.se23.engine.core.Core;
import com.setur.se23.engine.core.Entity;
import com.setur.se23.engine.input.FX_Input;
import com.setur.se23.engine.input.Input;
import com.setur.se23.engine.loop.Loop;

public class SnakeGame {
    
    private Input inputSystem;

    public Loop gameLoop = new Loop();

    public SnakeGame() {
        sendGameObjects();
    }

    private void sendGameObjects() {
        gameLoop.sendScene(createSnakeGameObjects(), getFunctions());
    }

    private ArrayList<Entity> createSnakeGameObjects() {
        return null;
    }

    private ArrayList<Runnable> getFunctions() {
        return null;
    }
}
