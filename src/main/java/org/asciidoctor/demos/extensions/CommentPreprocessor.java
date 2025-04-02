package org.asciidoctor.demos.extensions;

import org.asciidoctor.ast.Document;
import org.asciidoctor.extension.Preprocessor;
import org.asciidoctor.extension.PreprocessorReader;
import org.asciidoctor.extension.Reader;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CommentPreprocessor extends Preprocessor {

    public CommentPreprocessor() {
        super();
    }

    public CommentPreprocessor(Map<String, Object> config) {
        super(config);
    }

    @Override
    public Reader process(Document document, PreprocessorReader reader) {

        List<String> lines = reader.readLines();
        List<String> newLines = new ArrayList<>();

        boolean inComment = false;

        for (String line : lines) {
            if (line.trim().equals("////")) {
                if (!inComment) {
                    newLines.add("[NOTE]");
                }
                newLines.add("--");
                inComment = !inComment;
            } else {
                newLines.add(line);
            }
        }

        return newReader(newLines);
    }
}
