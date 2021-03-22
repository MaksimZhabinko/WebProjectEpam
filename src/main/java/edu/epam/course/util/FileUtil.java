package edu.epam.course.util;

import java.io.File;
import java.util.UUID;

public class FileUtil {
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

    public static void deleteImage(String fileName) {
        File file = new File(fileName);
        file.delete();
    }
}
