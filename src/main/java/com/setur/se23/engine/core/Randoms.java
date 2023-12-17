package com.setur.se23.engine.core;

import java.util.Random;

public class Randoms {
    
    public static Random random = new Random();

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
