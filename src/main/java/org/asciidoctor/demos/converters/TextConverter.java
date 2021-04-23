package org.asciidoctor.demos.converters;

import org.asciidoctor.ast.ContentNode;
import org.asciidoctor.ast.Document;
import org.asciidoctor.ast.Section;
import org.asciidoctor.ast.StructuralNode;
import org.asciidoctor.converter.ConverterFor;
import org.asciidoctor.converter.StringConverter;
import org.asciidoctor.jruby.ast.impl.StructuralNodeImpl;
import org.jruby.RubyArray;
import org.jruby.RubyHash;
import org.jruby.RubySymbol;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.Optional;

@ConverterFor(TextConverter.BACKEND)
public class TextConverter extends StringConverter {

    public static final String BACKEND = "text";

    private String LINE_SEPARATOR = "\n";

    public TextConverter(String backend, Map<String, Object> opts) {      // (2)
        super(backend, opts);
    }

    @Override
    public String convert(
        ContentNode node, String transform, Map<Object, Object> attributes) {

        if (transform == null) {
            transform = node.getNodeName();
        }

        // IMPORTANT: one must invoke `getContent` to trigger node traversion.
        if (node instanceof Document) {
            Document document = (Document) node;
//            Object references = ((DocumentImpl) document).getProperty("references");
            return document.getContent().toString();
        } else if (node instanceof Section) {
            Section section = (Section) node;
            return new StringBuilder()
                .append("== ").append(section.getTitle()).append(" ==")
                .append(LINE_SEPARATOR).append(LINE_SEPARATOR)
                .append(section.getContent()).toString();
        } else if (transform.equals("paragraph")) {
            StructuralNode block = (StructuralNode) node;
            String content = (String) block.getContent();
            return new StringBuilder(content.replaceAll(LINE_SEPARATOR, " "))
                .append(LINE_SEPARATOR).toString();
        }
        return null;
    }

    @Override
    public void write(String output, OutputStream out) throws IOException {
        if (output != null) {
            out.write(output.getBytes(Charset.forName("UTF-8")));
        }
    }

    public static void checkFootnotes(ContentNode node) {
        Object references = ((StructuralNodeImpl) node).getProperty("references");
        RubyHash rubyReferences = (RubyHash) references;

        Optional<RubySymbol> footnotesKey = (Optional<RubySymbol>) rubyReferences.keys()
            .stream()
            .filter(key -> ((RubySymbol) key).asJavaString().equals("footnotes"))
            .findFirst();
        if (footnotesKey.isPresent()) {
            RubyArray footnotes = (RubyArray) rubyReferences.get(footnotesKey.get());
            System.out.println(footnotes);
        } else {
            System.out.println("not found");
        }

    }

}
