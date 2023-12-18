package com.setur.se23.dependency.loop;

import com.setur.se23.engine.loop.FrameInterface;
import com.setur.se23.engine.loop.GameIterationInterface;

import javafx.animation.AnimationTimer;

public class FX_GameLoop extends AnimationTimer implements GameIterationInterface {

    private FrameInterface _frameCallback;

    @Override
    public void handle(long currentNanoTime) {
        _frameCallback.update(currentNanoTime);
    }

    @Override
    public void initialize(FrameInterface frameCallback) {
        _frameCallback = frameCallback;
    }

    @Override
    public void startLoop() {
        start();
    }

    @Override
    public void stopLoop() {
        stop();
    }

}
