package ru.sug4chy.demo6.service;

import java.io.File;
import java.io.IOException;

public class FileService {

    public static final String usersHomeDirectoriesPath = "D:\\FileManager";

    public File[] getDirectories(String path) {
        if (path == null) {
            path = "D:\\";
        }
        return new File(path).listFiles(File::isDirectory);
    }

    public File[] getFiles(String path) {
        if (path == null) {
            path = "D:\\";
        }
        return new File(path).listFiles(File::isFile);
    }

    public void createDirectory(String path) throws IOException {
        File directory = new File(path);
        if (!directory.exists()) {
            boolean result = directory.mkdir();
            if (!result) {
                throw new IOException("Не удалось создать директорию по этому пути");
            }
        }
    }
}