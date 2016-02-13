import org.asciidoctor.*;

import java.io.File;

public class AsciidoctorRunner {

    public static final String SRC_PATH = "src/asciidoc/";

    public static void main(String[] args) {
        Asciidoctor asciidoctor = Asciidoctor.Factory.create();

        AttributesBuilder attributes = AttributesBuilder.attributes();
        attributes.tableOfContents(true);
        attributes.tableOfContents(Placement.LEFT);
        attributes.sourceHighlighter("highlightjs");

        OptionsBuilder options = OptionsBuilder.options();
        options.backend("html5");
        options.safe(SafeMode.UNSAFE);
        options.mkDirs(true);

        options.attributes(attributes);

        asciidoctor.convertFile(file("example-manual.adoc"), options);

    }


    public static File file(String filaname) {
        return new File(SRC_PATH, filaname);
    }

}
