package org.asciidoctor.demos.extensions;

import org.asciidoctor.ast.Document;
import org.asciidoctor.extension.Postprocessor;

import java.util.Map;

import static org.asciidoctor.demos.converters.TextConverter.checkFootnotes;

public class ByePostprocessor extends Postprocessor {

    public ByePostprocessor(Map<String, Object> config) {
        super(config);
        System.out.println(this.getClass().getSimpleName() + "("
            + this.getClass().getSuperclass().getSimpleName() + ") initialized");
    }

    @Override
    public String process(Document document, String output) {
        checkFootnotes(document);
        System.out.println("Bye!!");
        return output;
    }

}
