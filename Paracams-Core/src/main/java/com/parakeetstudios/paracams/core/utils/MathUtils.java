package com.parakeetstudios.paracams.core.utils;

import java.util.concurrent.ThreadLocalRandom;

public class MathUtils {

    public static int genRandInt() {
        return ThreadLocalRandom.current().nextInt(Integer.MAX_VALUE);
    }

}
