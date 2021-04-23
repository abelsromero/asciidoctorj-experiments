package org.asciidoctor.demos;

import org.asciidoctor.*;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static org.asciidoctor.demos.utils.FileUtils.file;

public class AsciidoctorLogHandlers {

    public static void main(String[] args) {
        Asciidoctor asciidoctor = Asciidoctor.Factory.create();

        asciidoctor.registerLogHandler(logRecord -> {
            System.out.println("[INFO] First LogHander: " + logRecord.getMessage());
            // Exceptions from logHandlers will abort conversion
            // throw new RuntimeException("Boom!");
        });
        asciidoctor.registerLogHandler(logRecord -> {
            System.out.println("[INFO] Second LogHander: " + logRecord.getMessage());
        });

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

        Map<String, Object> attributesMap = new HashMap<>();
        attributesMap.put("icons", "font");
        attributesMap.put("experimental", Boolean.TRUE);
        attributesMap.put("my-attribute", "my-value");

        asciidoctor.convertFile(file("document-with-errors.adoc"), options);
    }


}
