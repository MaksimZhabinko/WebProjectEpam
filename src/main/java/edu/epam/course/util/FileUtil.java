package edu.epam.course.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.UUID;

public class FileUtil {
    private static final Logger logger = LogManager.getLogger(FileUtil.class);
    private static final String EXTENSION_SEPARATOR = ".";

    private FileUtil() {
    }

    public static String generateName(String fileName) {
        UUID uuid = UUID.randomUUID();
        String name = uuid.toString();
        int index = fileName.lastIndexOf(EXTENSION_SEPARATOR);
        String extension = fileName.substring(index);
        return name + extension;
    }
}
