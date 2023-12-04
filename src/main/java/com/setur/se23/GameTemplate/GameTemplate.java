package com.setur.se23.GameTemplate;

import java.util.ArrayList;

import com.setur.se23.engine.core.Entity;
import com.setur.se23.engine.input.FX_Input;
import com.setur.se23.engine.input.Input;
import com.setur.se23.engine.loop.Loop;

public class GameTemplate {

    private Input inputSystem;

    public Loop gameLoop = new Loop();

    public GameTemplate() {
        sendGameObjects();
    }

    private void sendGameObjects() {
        gameLoop.sendScene(createObjects(), getFunctions());
    }

    private ArrayList<Entity> createObjects() {

        // entities are rendered in list order
        // i.e. last in list is rendered over the others

        ArrayList<Entity> entities = new ArrayList<Entity>();
        


        createInputs();

        return entities;
    }

    private void createInputs() {
        inputSystem = new FX_Input(new GameEventsTemplate());

        inputSystem.addInputs();
    }

    private ArrayList<Runnable> getFunctions() {
        ArrayList<Runnable> runnables = new ArrayList<Runnable>();


        
        return runnables;
    }
}
