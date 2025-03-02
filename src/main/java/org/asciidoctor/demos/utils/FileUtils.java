package org.asciidoctor.demos.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class FileUtils {
    
    public static final String SRC_PATH = "src/asciidoc/";

    public static File file(String filename) {
        return new File(SRC_PATH, filename);
    }

    public static String readToString(String filename)  {
        File file = new File(SRC_PATH, filename);
        try {
            return Files.readString(file.toPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
