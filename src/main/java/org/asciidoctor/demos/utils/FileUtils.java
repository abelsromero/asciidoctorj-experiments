package org.asciidoctor.demos.utils;

import java.io.File;

public class FileUtils {
    
    public static final String SRC_PATH = "src/asciidoc/";

    public static File file(String filename) {
        return new File(SRC_PATH, filename);
    }
    
}
