package org.asciidoctor.demos;

import org.asciidoctor.*;
import org.asciidoctor.demos.extensions.ByePostprocessor;
import org.asciidoctor.demos.extensions.HelloPreprocessor;
import org.asciidoctor.extension.ExtensionGroup;

import java.io.File;
import java.util.Map;

public class AsciidoctorExtensionGroup {

    public static final String SRC_PATH = "src/asciidoc/";
    public static final String EXTENSION_GROUP_NAME = "my-extensions";

    public static void main(String[] args) {
        Asciidoctor asciidoctor = Asciidoctor.Factory.create();

        asciidoctor.javaExtensionRegistry().

        final String groupName = "parapa";
        // crates a group with random name, invoking again creates a new group
        ExtensionGroup extensionGroup2 = asciidoctor.createGroup(groupName)
                .postprocessor(ByePostprocessor.class);
        extensionGroup2
                .register();



        ExtensionGroup extensionGroup1 = asciidoctor.createGroup(groupName)
                .preprocessor(new HelloPreprocessor(Map.of(), "1"));
        extensionGroup1
                .register();


        ExtensionGroup random2 = asciidoctor.createGroup();
        asciidoctor.createGroup(EXTENSION_GROUP_NAME);

        AttributesBuilder attributes = AttributesBuilder.attributes();
        attributes.tableOfContents(true);
        attributes.tableOfContents(Placement.LEFT);
//        attributes.icons("font");

        OptionsBuilder options = OptionsBuilder.options();
        options.backend("html5");
        options.safe(SafeMode.UNSAFE);
        options.mkDirs(true);
        options.toDir(new File("build"));
        options.attributes(attributes);

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
