package org.asciidoctor.demos;

import org.asciidoctor.*;
import org.asciidoctor.demos.extensions.ByePostprocessor;
import org.asciidoctor.demos.extensions.HelloPreprocessor;
import org.asciidoctor.extension.ExtensionGroup;
import org.asciidoctor.extension.JavaExtensionRegistry;

import java.io.File;
import java.util.Map;

public class AsciidoctorExtensionGroup {

    public static final String SRC_PATH = "src/asciidoc/";
    public static final String EXTENSION_GROUP_NAME = "my-extensions";

    public static void main(String[] args) {
        Asciidoctor asciidoctor = Asciidoctor.Factory.create();

        asciidoctor.createGroup()
            .preprocessor(new HelloPreprocessor(Map.of(), "group 1"))
            .register();

        final String groupName = "my-group";
        ExtensionGroup group = asciidoctor.createGroup(groupName);
        group
            .postprocessor(ByePostprocessor.class)
            .register();

        // group.unregister();

        JavaExtensionRegistry javaExtensionRegistry = asciidoctor.javaExtensionRegistry();
        javaExtensionRegistry.preprocessor(new HelloPreprocessor(Map.of(), "registry "));

        // Will register all extension: from registry, groups & SPI
        // asciidoctor.unregisterAllExtensions();

        ExtensionGroup group2 = asciidoctor.createGroup();
        asciidoctor.createGroup(EXTENSION_GROUP_NAME);

        AttributesBuilder attributes = AttributesBuilder.attributes()
            .tableOfContents(true)
            .tableOfContents(Placement.LEFT)
            .icons("font");

        OptionsBuilder options = OptionsBuilder.options()
            .backend("html5")
            .safe(SafeMode.UNSAFE)
            .mkDirs(true)
            .toDir(new File("build"))
            .attributes(attributes);

        Map<String, Object> attributesMap = Map.of(
            "icons", "font",
            "experimental", Boolean.TRUE,
            "my-attribute", "my-value");

        asciidoctor.convertFile(file("sample.adoc"), options);
    }


    public static File file(String filename) {
        return new File(SRC_PATH, filename);
    }


}
