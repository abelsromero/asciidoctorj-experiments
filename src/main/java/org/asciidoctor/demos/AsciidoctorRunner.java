package org.asciidoctor.demos;

import org.asciidoctor.*;
import org.asciidoctor.ast.Document;
import org.asciidoctor.ast.StructuralNode;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.asciidoctor.demos.utils.BackendUtils.setSourceHighlighter;
import static org.asciidoctor.demos.utils.FileUtils.file;

public class AsciidoctorRunner {

    public static void main(String[] args) throws IOException {
        Asciidoctor asciidoctor = Asciidoctor.Factory.create();

        var attributes = Attributes.builder()
            .tableOfContents(true)
            .tableOfContents(Placement.LEFT)
            .build();
//        .icons("font");

        var options = Options.builder()
            .safe(SafeMode.UNSAFE)
            .mkDirs(true)
            .attributes(attributes)
            .toDir(new File("build"));

        Document load = asciidoctor.load(Files.readString(Path.of("/home/asalgadr/github/asciidoctorj-experiments/src/asciidoc/sample.adoc")), options.build());

        List<StructuralNode> blocks = load.getBlocks();

        options.backend("pdf");
        setSourceHighlighter("pdf", attributes);
        asciidoctor.convertFile(file("sample.adoc"), options.build());

        options.backend("html5");
        setSourceHighlighter("html5", attributes);
        asciidoctor.convertFile(file("sample.adoc"), options.build());
        // asciidoctor.convertFile(file("example-manual.adoc"), options);
    }

}
