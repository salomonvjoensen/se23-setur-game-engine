package com.setur.se23.FlappyBird;

import java.util.ArrayList;

import com.setur.se23.FlappyBird.Flappy_Bird_Objects.Background;
import com.setur.se23.FlappyBird.Flappy_Bird_Objects.Bird;
import com.setur.se23.FlappyBird.Flappy_Bird_Objects.Ground;
import com.setur.se23.FlappyBird.Flappy_Bird_Objects.Pipe;
import com.setur.se23.FlappyBird.Flappy_Bird_Objects.ScoreCollider;
import com.setur.se23.engine.GUI.FX_GUI;
import com.setur.se23.engine.GUI.GUI;
import com.setur.se23.engine.core.Core;
import com.setur.se23.engine.core.Entity;
import com.setur.se23.engine.input.FX_Input;
import com.setur.se23.engine.input.InputEvents;
import com.setur.se23.engine.input.InputType;
import com.setur.se23.engine.loop.Loop;

import javafx.scene.input.KeyCode;

public class FlappyBird {

    public Loop gameLoop = new Loop();

    public FlappyBird() {
        sendGameObjects();
    }

    private void sendGameObjects() {
        Pipe.started = false;

        gameLoop.sendScene(createFlappyBirdObjects(), getRunnables());
    }

    private void createGUI(Bird player) {

        GUI gui = new FX_GUI();

        gui.AddText(20, 20, Score.scoreProperty, 40, "#086604", 5);
        //gui.AddButton(Core.getStageWidth() / 2 - 80, 
        //              Core.getStageHeight() - 150, 
        //              100,
        //              30,
        //              "Jump",
        //              30,
        //              () -> {
        //                    player.jump();
        //                    player.jumpReady = true;
        //              });
        //gui.AddButton(25, 
        //              Core.getStageHeight() - 130, 
        //              100,
        //              30,
        //              "Restart",
        //              20,
        //              () -> sendGameObjects());
        
        gui.loadGUI();
    }

    private ArrayList<Entity> createFlappyBirdObjects() {

        // entities are rendered in list order
        // i.e. last in list is rendered over the others

        ArrayList<Entity> entities = new ArrayList<Entity>();
        
        entities.add(new Background());

        addPipes(entities, 4);

        entities.add(new Ground());
        
        Bird player = new Bird(200, 300);

        createInputs(player);

        entities.add(player);

        return entities;
    }

    private void addPipes(ArrayList<Entity> entities, int pairAmount) {

        int spacing = 500;

        double random = Core.randomDouble(1, 8);

        for (int i = 0; i < pairAmount; i++) {

            entities.add(new Pipe(true,  spacing,  (random * 50 - 500)));
            entities.add(new Pipe(false, spacing,  (random * 50 + 250)));
            
            entities.add(new ScoreCollider(spacing,  (random * 50 + 150)));

            spacing += 300;
            random = Core.randomDouble(1, 8);
        }
    }

    private void createInputs(Bird player) {

        InputEvents gameEvents = new GameEvents(player, () -> sendGameObjects());

        FX_Input inputSystem = new FX_Input(gameEvents);

        //on key press
        inputSystem.addInput(InputType.onPress, KeyCode.SPACE, "Jump");
        inputSystem.addInput(InputType.onPress, KeyCode.UP, "Jump");
        inputSystem.addInput(InputType.onPress, KeyCode.P, "toggle_FPS_Counter");
        inputSystem.addInput(InputType.onPress, KeyCode.O, "toggle_Gizmos");
        inputSystem.addInput(InputType.onPress, KeyCode.R, "Restart");

        //on key release
        inputSystem.addInput(InputType.onRelease, KeyCode.SPACE, "Jump_Ready");
        inputSystem.addInput(InputType.onRelease, KeyCode.UP, "Jump_Ready");

        inputSystem.setInputs();

        createGUI(player);
    }

    private ArrayList<Runnable> getRunnables() {
        ArrayList<Runnable> runnables = new ArrayList<Runnable>();

        runnables.add(() -> Pipe.loopAroundPipes());

        return runnables;
    }
}
