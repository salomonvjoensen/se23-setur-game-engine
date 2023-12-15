package com.setur.se23.engine.core;

import java.util.Random;

import com.setur.se23.engine.debug.Debug;
import com.setur.se23.engine.debug.VS_Debug;

import javafx.stage.Stage;

public class Core {

    public static Stage mainStage;

    public static long currentTime;

    public static Debug debug = new VS_Debug();

    public static Random random = new Random();

    public static double getStageWidth() {
        return mainStage.getWidth();
    }

    public static double getStageHeight() {
        return mainStage.getHeight();
    }

    public static long getCurrentTime() {
        return currentTime;
    }

    public static Stage getStage() {
        return mainStage;
    }

    /**
     * Returns a pseudorandomly chosen int value between the specified 
     * origin (inclusive) and the specified bound (exclusive).
     *
     * @param origin the least value that can be returned
     * @param upperBound the upper bound (exclusive) for the returned value
     * @return a randomly chosen int between the origin and upperBound
     */
    public static int randomInt(int origin, int upperBound) {
        return random.nextInt(origin, upperBound);
    }

    /**
     * Returns a pseudorandomly chosen double value between the specified 
     * origin (inclusive) and the specified bound (exclusive).
     *
     * @param origin the least value that can be returned
     * @param upperBound the upper bound (exclusive) for the returned value
     * @return a randomly chosen double between the origin and upperBound
     */
    public static double randomDouble(double origin, double upperBound) {
        return random.nextDouble(origin, upperBound);
    }
}
