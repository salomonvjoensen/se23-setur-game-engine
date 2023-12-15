package com.setur.se23.FlappyBird;

import java.util.ArrayList;

import com.setur.se23.FlappyBird.Flappy_Bird_Objects.Background;
import com.setur.se23.FlappyBird.Flappy_Bird_Objects.Bird;
import com.setur.se23.FlappyBird.Flappy_Bird_Objects.Ground;
import com.setur.se23.FlappyBird.Flappy_Bird_Objects.Pipe;
import com.setur.se23.FlappyBird.Flappy_Bird_Objects.ScoringHitBox;
import com.setur.se23.dependency.input.FX_Input;
import com.setur.se23.engine.audio.SoundEffectsManager;
import com.setur.se23.engine.core.Entity;
import com.setur.se23.engine.core.Randoms;
import com.setur.se23.engine.input.InputEvents;
import com.setur.se23.engine.input.InputManager;
import com.setur.se23.engine.input.InputType;
import com.setur.se23.engine.loop.GameLoop;
import com.setur.se23.engine.loop.Loop;

public class FlappyBird {

    public GameLoop gameLoop = GameLoop.getInstance();
    public Loop loop;

    public FlappyBird() {
        FlappyBirdGUI.setRestartRunnable(() -> newGame());
        
        newGame();
    }

    private void newGame() {
        Pipe.stop();
        Score.resetScore();
        FlappyBirdGUI.newGame();

        loadSoundEffects();

        
        gameLoop.unsubscribeFromFrame(loop);

        loop = new Loop(createFlappyBirdObjects(), getRunnables());

        gameLoop.subscribeToFrame(loop);
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

        double random = Randoms.randomDouble(1, 8);

        for (int i = 0; i < pairAmount; i++) {

            entities.add(new Pipe(true,  spacing,  (random * 50 - 500)));
            entities.add(new Pipe(false, spacing,  (random * 50 + 250)));
            
            entities.add(new ScoringHitBox(spacing,  (random * 50 + 150)));

            spacing += 300;
            random = Randoms.randomDouble(1, 8);
        }
    }

    private void initializeInputManager(InputEvents gameEvents) {
        var inputSystem = new FX_Input();

        InputManager.Instantiate(inputSystem)
                .initialize(gameEvents);
    }

    private void createInputs(Bird player) {
        initializeInputManager(new GameEvents(player, () -> newGame()));

        InputManager inputSystem = InputManager.getInstance();

        //on key press
        inputSystem.addInput(InputType.onPress, "SPACE", "Jump");
        inputSystem.addInput(InputType.onPress, "UP", "Jump");
        inputSystem.addInput(InputType.onPress, "P", "toggle_FPS_Counter");
        inputSystem.addInput(InputType.onPress, "O", "toggle_Gizmos");
        inputSystem.addInput(InputType.onPress, "R", "Restart");

        //on key release
        inputSystem.addInput(InputType.onRelease, "SPACE", "Jump_Ready");
        inputSystem.addInput(InputType.onRelease, "UP", "Jump_Ready");

        inputSystem.setInputs();

        FlappyBirdGUI.setGUI();
    }

    private void loadSoundEffects() {
        SoundEffectsManager.loadSoundEffect(SoundEffects.DIE.getFilePath());
        SoundEffectsManager.loadSoundEffect(SoundEffects.FLAP.getFilePath());
        SoundEffectsManager.loadSoundEffect(SoundEffects.HIT.getFilePath());
    }

    private ArrayList<Runnable> getRunnables() {
        ArrayList<Runnable> runnables = new ArrayList<Runnable>();

        runnables.add(() -> Pipe.loopAroundPipes());

        return runnables;
    }
}
