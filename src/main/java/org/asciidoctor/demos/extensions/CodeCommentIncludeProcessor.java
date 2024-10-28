package org.asciidoctor.demos.extensions;

import org.asciidoctor.ast.Document;
import org.asciidoctor.extension.IncludeProcessor;
import org.asciidoctor.extension.PreprocessorReader;

import java.io.File;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CodeCommentIncludeProcessor extends IncludeProcessor {

    private static final String textLine = "Lorem ipsum dolor sit amet";

    @Override
    public boolean handles(String target) {
        return true;
    }

    @Override
    public void process(Document document, PreprocessorReader reader, String target, Map<String, Object> attributes) {

        final String source = (String) attributes.getOrDefault("source", "java");
        final String textToInclude = getCommenter(source).apply((textLine + "\n").repeat(3));

        reader.pushInclude(
            textToInclude,
            target,
            new File(".").getAbsolutePath(),
            1,
            attributes);
    }

    private Function<String, String> getCommenter(String source) {
        if (source.equalsIgnoreCase("java"))
            return this::javaCommenter;
        if (source.equalsIgnoreCase("xml"))
            return this::xmlCommenter;

        throw new IllegalArgumentException("No commenter available for: " + source);
    }

    private String javaCommenter(String text) {
        return text.lines()
            .map(line -> "// " + line + "\n")
            .collect(Collectors.joining());
    }

    private String xmlCommenter(String text) {
        return "<!--\n" + text + "-->\n";
    }

}
