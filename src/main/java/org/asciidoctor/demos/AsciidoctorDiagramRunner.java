package org.asciidoctor.demos;

import org.asciidoctor.*;

import java.io.File;
import java.io.IOException;

import static org.asciidoctor.demos.utils.FileUtils.file;

public class AsciidoctorDiagramRunner {

    public static void main(String[] args) throws IOException {
        final String filename = "sample-diagram.adoc";
        convertWithDiagram(filename);
    }

    private static void convertWithDiagram(String filename) {
        Asciidoctor asciidoctor = Asciidoctor.Factory.create();
        asciidoctor.requireLibrary("asciidoctor-diagram");

        AttributesBuilder attributes = Attributes.builder();
        Options options = Options.builder()
            .backend("html5")
            .safe(SafeMode.UNSAFE)
            .mkDirs(true)
            .toDir(new File("build"))
            .attributes(attributes.build())
            .build();

        File file = file(filename);
        String convert = asciidoctor.convertFile(file, options);
        assert convert == null;
    }

}
