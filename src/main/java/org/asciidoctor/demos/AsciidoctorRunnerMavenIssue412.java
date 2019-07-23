package org.asciidoctor.demos;

import org.asciidoctor.*;

import java.io.File;

/**
 * https://github.com/asciidoctor/asciidoctor-maven-plugin/issues/412
 */

public class AsciidoctorRunnerMavenIssue412 {

    public static final String SRC_PATH = "src/asciidoc/";

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

        options.backend("html5");
        setSourceHighlighter("html5", attributes);
        asciidoctor.convertFile(file("sample.adoc"), options);
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
