package org.asciidoctor.demos;

import org.asciidoctor.*;
import org.asciidoctor.demos.converters.TextConverter;

import java.io.File;

public class AsciidoctorCustomConverter {

    public static final String SRC_PATH = "src/asciidoc/";

    public static void main(String[] args) {
        Asciidoctor asciidoctor = Asciidoctor.Factory.create();

        AttributesBuilder attributes = AttributesBuilder.attributes();
        attributes.tableOfContents(true);
        attributes.tableOfContents(Placement.LEFT);

        var options = new Options();
        options.setInPlace(true);
        options.setSafe(SafeMode.SAFE);
        options.setToDir("." + "/build");
        options.setMkDirs(true);
        options.setDestinationDir("docbook");
        options.setToFile(false);

        asciidoctor.javaConverterRegistry()
            .register(TextConverter.class);

        options.setBackend("text");
        setSourceHighlighter("html5", attributes);
        String convertedContent = asciidoctor.convertFile(file("sample.adoc"), options);
        System.out.println("Result:\n" + convertedContent);
    }


    public static File file(String filaname) {
        return new File(SRC_PATH, filaname);
    }

    public static void setSourceHighlighter(String backend, AttributesBuilder attributes) {
        if (backend.equals("pdf")) {
            attributes.sourceHighlighter("rouge");
        } else
            attributes.sourceHighlighter("highlightjs");
    }
}
