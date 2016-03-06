package org.asciidoctor.demos;

import org.asciidoctor.*;

import java.io.File;

public class ManpageRenderer {

    public static final String SRC_PATH = "src/asciidoc/";

    public static void main(String[] args) {
        // null is required to create isolates Asciidoctor from the system gems (default planned for 1.6.0)
        Asciidoctor asciidoctor = Asciidoctor.Factory.create((String) null);

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


    public static File file(String filaname) {
        return new File(SRC_PATH, filaname);
    }

}
