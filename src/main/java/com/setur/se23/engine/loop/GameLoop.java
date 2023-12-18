package com.setur.se23.engine.loop;

import com.setur.se23.engine.core.Time;
import com.setur.se23.engine.render.Renderer;

import java.util.ArrayList;

public class GameLoop {

    private static GameLoop _instance;
    private final GameIterationInterface _iteration;
    private final ArrayList<FrameInterface> _frameComputation = new ArrayList<>();

    // Private constructor to prevent instantiation from other classes
    private GameLoop(GameIterationInterface iteration) {
        _iteration = iteration;
        _iteration.initialize(this::update);
    }

    /**
     * Gets the singleton instance of the GameLoop.
     *
     * @return The singleton instance of the GameLoop.
     */
    public static GameLoop getInstance() {
        assert _instance != null;
        return _instance;
    }

    /**
     * Instantiates the GameLoop with the given GameIterationInterface.
     *
     * @param iteration GameIterationInterface needed to update each frame.
     * @return The singleton instance of the GameLoop.
     */
    public static void initialize(GameIterationInterface iteration) {
        _instance = new GameLoop(iteration);
    }

    /**
     * Subcribe loop to frame updates
     * 
     * @param frame instance of FrameInterface
     */
    public void subscribeToFrame(FrameInterface frame) {
        _frameComputation.add(frame);
    }

    /**
     * Unsubcribe loop to frame updates
     * 
     * @param frame instance of FrameInterface
     */
    public void unsubscribeFromFrame(FrameInterface frame) {
        _frameComputation.remove(frame);
    }

    /**
     * Method to start loop
     */
    public void start() {
        _iteration.startLoop();
    }

    /**
     * Method to stop loop
     */
    public void stop() {
        _iteration.stopLoop();
    }

    /**
     * Method update loops with currentTime
     * 
     * @param currentTime
     */
    private void update(long currentTime) {

        Time.updateTime(currentTime);

        _frameComputation.forEach(frame -> frame.update(currentTime));

        Renderer.getInstance().swapBuffers();
    }
}
