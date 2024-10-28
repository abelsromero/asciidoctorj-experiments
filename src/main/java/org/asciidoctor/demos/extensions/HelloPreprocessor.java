package org.asciidoctor.demos.extensions;

import org.asciidoctor.ast.Document;
import org.asciidoctor.extension.Preprocessor;
import org.asciidoctor.extension.PreprocessorReader;
import org.asciidoctor.extension.Reader;

import java.util.Map;

import static org.asciidoctor.demos.converters.TextConverter.checkFootnotes;

public class HelloPreprocessor extends Preprocessor {

    private final String message;

    public HelloPreprocessor(Map<String, Object> config, String message) {
        super(config);
        this.message = message;
        System.out.println(this.getClass().getSimpleName() + "("
                + this.getClass().getSuperclass().getSimpleName() + ") initialized");
    }

    @Override
    public Reader process(Document document, PreprocessorReader reader) {
        checkFootnotes(document);
        System.out.println("Hello " + message + " !!!");
        return reader;
    }

}
