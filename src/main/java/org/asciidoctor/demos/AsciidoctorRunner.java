package org.asciidoctor.demos;

import org.asciidoctor.*;

import java.io.File;

public class AsciidoctorRunner {

    public static final String SRC_PATH = "src/asciidoc/";

    public static void main(String[] args) {
        // null is required to create isolates Asciidoctor from the system gems (default planned for 1.6.0)
        Asciidoctor asciidoctor = Asciidoctor.Factory.create((String) null);

        AttributesBuilder attributes = AttributesBuilder.attributes();
        attributes.tableOfContents(true);
        attributes.tableOfContents(Placement.LEFT);

        OptionsBuilder options = OptionsBuilder.options();
        options.safe(SafeMode.UNSAFE);
        options.mkDirs(true);
        options.attributes(attributes);
        options.toDir(new File("build"));

        for (int i = 0; i < 500; i++) {
            System.out.println("Converting: " + i);
            options.toFile(new File("target/" + i + ".html"));
//            Asciidoctor.Factory.create((String) null)
            asciidoctor
//                    .convertFile(file("user-password-expiration.adoc"), options);
                    .convertFile(file("git-usage.adoc"), options);
        }
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
