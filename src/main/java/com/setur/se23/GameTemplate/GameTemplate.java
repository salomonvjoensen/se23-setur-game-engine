package com.setur.se23.GameTemplate;

import java.util.ArrayList;

import com.setur.se23.dependency.input.FX_Input;
import com.setur.se23.engine.core.Entity;
import com.setur.se23.engine.input.InputEvents;
import com.setur.se23.engine.input.InputManager;
import com.setur.se23.engine.loop.GameLoop;
import com.setur.se23.engine.loop.Loop;

public class GameTemplate {

    public GameLoop gameLoop = GameLoop.getInstance();
    public Loop loop;

    public GameTemplate() {
        newGame();
    }

    private void newGame() {

        gameLoop.unsubscribeFromFrame(loop);

        loop = new Loop(createObjects(), getRunnables());

        gameLoop.subscribeToFrame(loop);
    }

    private ArrayList<Entity> createObjects() {

        // entities are rendered in list order
        // i.e. last in list is rendered over the others

        ArrayList<Entity> entities = new ArrayList<Entity>();



        createInputs();

        return entities;
    }

    private void initializeInputManager(InputEvents gameEvents) {
        var inputSystem = new FX_Input();

        InputManager.Instantiate(inputSystem)
                .initialize(gameEvents);
    }

    private void createInputs() {
        initializeInputManager(new GameEventsTemplate());

        InputManager inputSystem = InputManager.getInstance();

        inputSystem.setInputs();
    }

    private ArrayList<Runnable> getRunnables() {
        ArrayList<Runnable> runnables = new ArrayList<Runnable>();


        
        return runnables;
    }
}
