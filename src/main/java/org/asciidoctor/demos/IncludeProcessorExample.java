package org.asciidoctor.demos;

import org.asciidoctor.*;
import org.asciidoctor.demos.extensions.CodeCommentIncludeProcessor;

import java.io.File;

import static org.asciidoctor.demos.utils.BackendUtils.setSourceHighlighter;
import static org.asciidoctor.demos.utils.FileUtils.file;

public class IncludeProcessorExample {

    public static void main(String[] args) {
        Asciidoctor asciidoctor = Asciidoctor.Factory.create();

        asciidoctor.javaExtensionRegistry()
            .includeProcessor(CodeCommentIncludeProcessor.class);

        var attributes = Attributes.builder()
            .tableOfContents(true)
            .tableOfContents(Placement.LEFT)
            .icons("font")
            .build();

        var options = Options.builder()
            .inPlace(true)
            .safe(SafeMode.SAFE)
            .toDir(new File(".", "/build"))
            .mkDirs(true)
            .build();


        options.setBackend("html5");
        setSourceHighlighter("html5", attributes);
        asciidoctor.convertFile(file("custom-include-processor.adoc"), options);
    }

}
