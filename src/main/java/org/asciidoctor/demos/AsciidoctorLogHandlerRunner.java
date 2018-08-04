package org.asciidoctor.demos;

import org.asciidoctor.*;
import org.asciidoctor.internal.JRubyRuntimeContext;
import org.asciidoctor.log.LogHandler;
import org.asciidoctor.log.LogRecord;
import org.jruby.internal.runtime.GlobalVariables;

import java.io.File;

public class AsciidoctorLogHandlerRunner {

    public static final String SRC_PATH = "src/asciidoc/";

    public static void main(String[] args) {
        // null is required to create isolates Asciidoctor from the system gems (default planned for 1.6.0)
        final Asciidoctor asciidoctor = Asciidoctor.Factory.create((String) null);

        GlobalVariables globalVariables = JRubyRuntimeContext.get().getGlobalVariables();
        printVerbose(globalVariables);
        globalVariables.set("$VERBOSE", JRubyRuntimeContext.get().getFalse());
        printVerbose(globalVariables);

        asciidoctor.registerLogHandler(new LogHandler() {
            @Override
            public void log(LogRecord logRecord) {
//                System.out.println("Java println -> " + logRecord.getMessage());
            }
        });

        final AttributesBuilder attributes = AttributesBuilder.attributes();
        attributes.tableOfContents(true);
        attributes.tableOfContents(Placement.LEFT);

        final OptionsBuilder options = OptionsBuilder.options();
        options.safe(SafeMode.UNSAFE);
        options.mkDirs(true);
        options.attributes(attributes);
        options.toDir(new File("build"));

        asciidoctor.convertFile(file("document-with-errors.adoc"), options);


    }

    private static void printVerbose(GlobalVariables globalVariables) {
        System.out.println("$VERBOSE: " + globalVariables.get("$VERBOSE"));
    }

    public static File file(String filaname) {
        return new File(SRC_PATH, filaname);
    }

}
