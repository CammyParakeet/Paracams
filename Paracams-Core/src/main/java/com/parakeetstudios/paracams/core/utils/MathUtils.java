package com.parakeetstudios.paracams.core.utils;

import java.util.concurrent.ThreadLocalRandom;

public class MathUtils {

    public static int genRandInt() {
        return ThreadLocalRandom.current().nextInt(Integer.MAX_VALUE);
    }

    public static int genRandInt(int max) {
        return ThreadLocalRandom.current().nextInt(max);
    }

    public static double clamp(double in, int round) {
        double multi = Math.pow(10, round);
        return Math.round(in * multi) / multi;
    }

}
