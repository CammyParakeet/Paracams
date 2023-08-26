package com.parakeetstudios.paracams.core.utils;

import java.util.logging.Logger;

public class Paralog {

    private static Logger logger;

    public static void init(Logger loggerInstance) {
        logger = loggerInstance;
    }

    public static void info(String message) {
        if (logger != null) {
            logger.info(message);
        }
    }

    public static void warning(String message) {
        if (logger != null) {
            logger.warning(message);
        }
    }

    public static void severe(String message) {
        if (logger != null) {
            logger.severe(message);
        }
    }

}
