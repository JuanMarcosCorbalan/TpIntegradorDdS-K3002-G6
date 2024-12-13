package org.example.Sistema;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LoggerToFile {
    private static final Logger logger = Logger.getLogger(LoggerToFile.class.getName());

    static {
        try {
            FileHandler fileHandler = new FileHandler("archivoLogger.log", true);
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);
        } catch (IOException e) {
            System.err.println("Error al configurar el logger: " + e.getMessage());
        }
    }
    public static void logInfo(String message) {
        logger.info(message);
    }

    public static void logWarning(String message) {
        logger.warning(message);
    }

    public static void logError(String message, Throwable e) {
        logger.severe(message + " - " + e.getMessage());
    }

}