package org.asciidoctor.demos;

import org.asciidoctor.*;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import static org.asciidoctor.demos.utils.FileUtils.file;
import static org.asciidoctor.demos.utils.FileUtils.readToString;

/**
 * Example to capture Asciidoctor messages.
 */
public class AsciidoctorLogHandlers {

    public static void main(String[] args) {

        Asciidoctor asciidoctor = Asciidoctor.Factory.create();

        // Disable default console output of AsciidoctorJ. Otherwise, messages will leak to standard output.
        Logger.getLogger("asciidoctor").setUseParentHandlers(false);

        asciidoctor.registerLogHandler(logRecord -> {
            System.out.println("[%s] First LogHander: %s".formatted(logRecord.getSeverity(), logRecord.getMessage()));
            // Messages are capture DURING conversion, so exceptions from logHandlers can abort conversion
            // throw new RuntimeException("Boom!");
        });
        // Multiple LogHandlers can be registered
//        asciidoctor.registerLogHandler(logRecord -> {
//            System.out.println("[INFO] Second LogHander: " + logRecord.getMessage());
//        });

        // IMPORTANT: configure 'attributeMissing' to get messages related to attributes.
        var attributes = Attributes.builder()
            .tableOfContents(true)
            .tableOfContents(Placement.LEFT)
            .icons("font")
            .attributeMissing("warn")
            .build();

        var options = Options.builder()
            .backend("html5")
            .safe(SafeMode.UNSAFE)
            .mkDirs(true)
            .toDir(new File("build"))
            .attributes(attributes)
            .build();

        // NOT all messages appear when using 'load'.
        // See the missing "skipping reference to missing attribute:"
        System.out.println("---- Converting...");
        asciidoctor.convertFile(file("document-with-errors.adoc"), options);

        System.out.println("---- Loading...");
        asciidoctor.load(readToString("document-with-errors.adoc"), options);
    }

}
