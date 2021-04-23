package org.asciidoctor.demos;

import org.asciidoctor.*;

import java.io.File;

import static org.asciidoctor.demos.utils.FileUtils.file;

public class ManpageRenderer {

    public static void main(String[] args) {
        Asciidoctor asciidoctor = Asciidoctor.Factory.create();

        AttributesBuilder attributes = AttributesBuilder.attributes();
        attributes.tableOfContents(true);
        attributes.tableOfContents(Placement.LEFT);

        OptionsBuilder options = OptionsBuilder.options();
        options.backend("manpage");
        options.docType("manpage");
        options.safe(SafeMode.UNSAFE);
        options.mkDirs(true);
        options.attributes(attributes);

        // Output
        options.toDir(new File("build"));

        asciidoctor.convertFile(file("manpage.adoc"), options);
        // asciidoctor.convertFile(file("example-manual.adoc"), options);
    }
}
