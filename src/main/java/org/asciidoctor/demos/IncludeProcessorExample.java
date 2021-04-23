package org.asciidoctor.demos;

import org.asciidoctor.*;
import org.asciidoctor.demos.extensions.CodeCommentIncludeProcessor;

import static org.asciidoctor.demos.utils.FileUtils.file;

public class IncludeProcessorExample {

    public static void main(String[] args) {
        Asciidoctor asciidoctor = Asciidoctor.Factory.create();

        asciidoctor.javaExtensionRegistry()
            .includeProcessor(CodeCommentIncludeProcessor.class);

        AttributesBuilder attributes = AttributesBuilder.attributes();
        attributes.tableOfContents(true);
        attributes.tableOfContents(Placement.LEFT);
        attributes.icons("font");

        var options = new Options();
        options.setInPlace(true);
        options.setSafe(SafeMode.SAFE);
        options.setToDir("." + "/build");
        options.setMkDirs(true);

        options.setBackend("html5");
        setSourceHighlighter("html5", attributes);
        asciidoctor.convertFile(file("custom-include-processor.adoc"), options);
    }

    public static void setSourceHighlighter(String backend, AttributesBuilder attributes) {
        if (backend.equals("pdf")) {
            attributes.sourceHighlighter("rouge");
        } else
            attributes.sourceHighlighter("highlightjs");
    }
}
