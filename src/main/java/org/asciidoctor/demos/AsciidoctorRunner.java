package org.asciidoctor.demos;

import org.asciidoctor.*;

import java.io.File;

import static org.asciidoctor.demos.utils.FileUtils.file;

public class AsciidoctorRunner {

    public static void main(String[] args) {
        Asciidoctor asciidoctor = Asciidoctor.Factory.create();

        AttributesBuilder attributes = Attributes.builder()
            .tableOfContents(true)
            .tableOfContents(Placement.LEFT);
//        .icons("font");

        OptionsBuilder options = Options.builder()
            .safe(SafeMode.UNSAFE)
            .mkDirs(true)
            .attributes(attributes)
            .toDir(new File("build"));

        asciidoctor.convertFile(file("sample.adoc"), options.build());

        options.backend("pdf");
        setSourceHighlighter("pdf", attributes);
        asciidoctor.convertFile(file("sample.adoc"), options.build());

        options.backend("html5");
        setSourceHighlighter("html5", attributes);
        asciidoctor.convertFile(file("sample.adoc"), options.build());
        // asciidoctor.convertFile(file("example-manual.adoc"), options);
    }

    public static void setSourceHighlighter(String backend, AttributesBuilder attributes) {
        if (backend.equals("pdf")) {
            attributes.sourceHighlighter("rouge");
        } else
            attributes.sourceHighlighter("highlightjs");
    }
}
