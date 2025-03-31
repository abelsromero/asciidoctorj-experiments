package org.asciidoctor.demos;

import org.apache.commons.io.FileUtils;
import org.asciidoctor.Asciidoctor;
import org.asciidoctor.Options;
import org.asciidoctor.ast.Document;
import org.asciidoctor.ast.StructuralNode;
import org.asciidoctor.extension.Treeprocessor;
import org.asciidoctor.jruby.ast.impl.SectionImpl;
import org.jruby.runtime.builtin.IRubyObject;

import java.io.File;
import java.io.IOException;

import static org.asciidoctor.demos.utils.FileUtils.file;

public class AstTraverser {

    public static void main(String[] args) throws IOException {

        final String asciidocContent = FileUtils.readFileToString(file("ast.adoc"));

        final Asciidoctor asciidoctor = Asciidoctor.Factory.create();
        final Options options = Options.builder()
            .backend("pdf")
            .mkDirs(true)
            .toFile(new File("build", "converted.html"))
            .build();

        asciidoctor.convert(asciidocContent, options);

        Document document = asciidoctor.load(asciidocContent, options);
        new NodesTraverser()
            .processNode(document, 0);

        asciidoctor.javaExtensionRegistry()
            .treeprocessor(new Treeprocessor() {
                @Override
                public Document process(Document document) {
                    SectionImpl section = (SectionImpl) document.getBlocks().get(1);
                    String title = section.getTitle();
                    Object title1 = section.getProperty("title");

                    section.getRubyObject().getInstanceVariables().getInstanceVariable("@title");

                    System.out.println("go!");
                    new NodesTraverser()
                        .processNode(document, 0);
                    return document;
                }
            });

        asciidoctor.load(asciidocContent, options);

//        asciidoctor.unregisterAllExtensions();
//        asciidoctor.convertFile(file, options);
    }
}
