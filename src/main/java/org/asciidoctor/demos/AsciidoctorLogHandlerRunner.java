package org.asciidoctor.demos;

import org.asciidoctor.*;
import org.asciidoctor.jruby.internal.JRubyRuntimeContext;
import org.asciidoctor.log.LogHandler;
import org.asciidoctor.log.LogRecord;
import org.jruby.internal.runtime.GlobalVariables;

import java.io.File;

import static org.asciidoctor.demos.utils.FileUtils.file;

public class AsciidoctorLogHandlerRunner {

    public static void main(String[] args) {
        final Asciidoctor asciidoctor = Asciidoctor.Factory.create();

        GlobalVariables globalVariables = JRubyRuntimeContext.get(asciidoctor).getGlobalVariables();
        printVerbose(globalVariables);
        globalVariables.set("$VERBOSE", JRubyRuntimeContext.get(asciidoctor).getFalse());
        printVerbose(globalVariables);

        asciidoctor.registerLogHandler(new LogHandler() {
            @Override
            public void log(LogRecord logRecord) {
//                System.out.println("Java println -> " + logRecord.getMessage());
            }
        });

        var attributes = Attributes.builder()
            .tableOfContents(true)
            .tableOfContents(Placement.LEFT)
            .build();

        var options = Options.builder()
            .safe(SafeMode.UNSAFE)
            .mkDirs(true)
            .attributes(attributes)
            .toDir(new File("build"))
            .build();

        asciidoctor.convertFile(file("document-with-errors.adoc"), options);
    }

    private static void printVerbose(GlobalVariables globalVariables) {
        System.out.println("$VERBOSE: " + globalVariables.get("$VERBOSE"));
    }
}
