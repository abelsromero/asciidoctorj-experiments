package org.asciidoctor.demos.utils;

import org.asciidoctor.Attributes;

public class BackendUtils {

    public static void setSourceHighlighter(String backend, Attributes attributes) {
        attributes.setSourceHighlighter(backend.equals("pdf") ? "rouge" : "highlightjs");
    }
}
