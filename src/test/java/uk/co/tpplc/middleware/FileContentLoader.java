package uk.co.tpplc.middleware;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileContentLoader {

    public String load(String path) {
        return loadContentFromPath(Paths.get(path));
    }

    public String load(File file) {
        return loadContentFromPath(file.toPath());
    }

    private static String loadContentFromPath(Path path) {
        try {
            return new String(Files.readAllBytes(path));
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

}
