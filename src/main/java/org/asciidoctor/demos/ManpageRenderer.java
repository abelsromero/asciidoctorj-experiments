package org.asciidoctor.demos;

import org.asciidoctor.*;

import java.io.File;

import static org.asciidoctor.demos.utils.FileUtils.file;

public class ManpageRenderer {

    public static void main(String[] args) {
        Asciidoctor asciidoctor = Asciidoctor.Factory.create();

        var attributes = Attributes.builder()
            .tableOfContents(true)
            .tableOfContents(Placement.LEFT)
            .build();

        var options = Options.builder()
            .backend("manpage")
            .docType("manpage")
            .safe(SafeMode.UNSAFE)
            .mkDirs(true)
            .attributes(attributes)
            .build();

        // Output
        options.setToDir("build");

        asciidoctor.convertFile(file("manpage.adoc"), options);
        // asciidoctor.convertFile(file("example-manual.adoc"), options);
    }
}
