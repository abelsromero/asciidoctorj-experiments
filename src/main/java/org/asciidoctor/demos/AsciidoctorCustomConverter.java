package org.asciidoctor.demos;

import org.asciidoctor.*;
import org.asciidoctor.demos.converters.TextConverter;

import java.io.File;

import static org.asciidoctor.demos.utils.BackendUtils.setSourceHighlighter;
import static org.asciidoctor.demos.utils.FileUtils.file;

public class AsciidoctorCustomConverter {

    public static void main(String[] args) {
        Asciidoctor asciidoctor = Asciidoctor.Factory.create();

        Attributes attributes = Attributes.builder()
            .tableOfContents(true)
            .tableOfContents(Placement.LEFT)
            .build();

        var options = Options.builder()
            .inPlace(true)
            .safe(SafeMode.SAFE)
            .toDir(new File(".", "/build"))
            .mkDirs(true)
            .toFile(false)
            .build();

        asciidoctor.javaConverterRegistry()
            .register(TextConverter.class);

        options.setBackend("text");
        setSourceHighlighter("html5", attributes);
        String convertedContent = asciidoctor.convertFile(file("sample.adoc"), options);
        System.out.println("Result:\n" + convertedContent);
    }
}
