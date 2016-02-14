package org.asciidoctor.demos;

import org.asciidoctor.*;

import java.io.File;

public class AsciidoctorRunner {

    public static final String SRC_PATH = "src/asciidoc/";

    public static void main(String[] args) {
        // null is required to create isolates Asciidoctor from the system gems (default planned for 1.6.0)
        Asciidoctor asciidoctor = Asciidoctor.Factory.create((String)null);

        String backend = "pdf";

        AttributesBuilder attributes = AttributesBuilder.attributes();
        attributes.tableOfContents(true);
        attributes.tableOfContents(Placement.LEFT);
        if (backend.equals("pdf")) {
            attributes.sourceHighlighter("rouge");
        } else
            attributes.sourceHighlighter("highlightjs");

        OptionsBuilder options = OptionsBuilder.options();
        options.backend(backend);
        options.safe(SafeMode.UNSAFE);
        options.mkDirs(true);

        options.attributes(attributes);

        asciidoctor.convertFile(file("example-manual.adoc"), options);

    }


    public static File file(String filaname) {
        return new File(SRC_PATH, filaname);
    }

}
