package org.asciidoctor.demos;

import org.asciidoctor.*;

import java.io.File;

import static org.asciidoctor.demos.utils.FileUtils.file;

public class AsciidoctorRunner {

    public static void main(String[] args) {
        Asciidoctor asciidoctor = Asciidoctor.Factory.create();

        AttributesBuilder attributes = AttributesBuilder.attributes();
        attributes.tableOfContents(true);
        attributes.tableOfContents(Placement.LEFT);
//        attributes.icons("font");

        OptionsBuilder options = OptionsBuilder.options();
        options.safe(SafeMode.UNSAFE);
        options.mkDirs(true);
        options.attributes(attributes);
        options.toDir(new File("build"));

        asciidoctor.convertFile(file("sample.adoc"), options);

        options.backend("pdf");
        setSourceHighlighter("pdf", attributes);
        asciidoctor.convertFile(file("sample.adoc"), options);

        options.backend("html5");
        setSourceHighlighter("html5", attributes);
        asciidoctor.convertFile(file("sample.adoc"), options);
        // asciidoctor.convertFile(file("example-manual.adoc"), options);
    }

    public static void setSourceHighlighter(String backend, AttributesBuilder attributes) {
        if (backend.equals("pdf")) {
            attributes.sourceHighlighter("rouge");
        } else
            attributes.sourceHighlighter("highlightjs");
    }
}
