package org.asciidoctor.demos;

import org.asciidoctor.Asciidoctor;
import org.asciidoctor.AttributesBuilder;
import org.asciidoctor.OptionsBuilder;
import org.asciidoctor.ast.ContentNode;
import org.asciidoctor.ast.Document;
import org.asciidoctor.ast.StructuralNode;
import org.asciidoctor.extension.Treeprocessor;

import java.io.File;
import java.util.function.Function;

import static org.asciidoctor.demos.utils.FileUtils.file;

public class AstTraverser {

    public static class Tre extends Treeprocessor {

        @Override
        public Document process(Document document) {
            System.out.println("go!");
            processNode(document, 0);
            return document;
        }

        public void processNode(StructuralNode node, int depth) {
//            if (node instanceof Table) {
//                System.out.println("");
//            }
            String message = protectedApply(node, ContentNode::getNodeName) + " (" + node.getClass().getSimpleName() + ")";
            message += "\t\t\t\t context: " + protectedApply(node, ContentNode::getContext);
            message += "\t\t\t\t style: " + protectedApply(node, StructuralNode::getStyle);
            message += "\t\t\t\t level: " + protectedApply(node, n -> String.valueOf(n.getLevel()));

            println(message, depth);
            try {
                node.getBlocks()
                    .forEach(b -> processNode(b, depth + 1));
            } catch (Exception e) {
                System.out.println("");
            }
        }

        private String getContext(StructuralNode node) {
            return node.getContext();
        }

        private String protectedApply(StructuralNode node, Function<StructuralNode, String> function) {
            try {
                return function.apply(node);
            } catch (Exception e) {
                return "ERROR";
            }
        }


        public void println(String message, int depth) {
            final String prefix = prefix(depth);
            System.out.println("[info] " + prefix + message);
        }

        private String prefix(int length) {
            return length <= 0 ? "" : "  ".repeat(length + 1);
        }
    }

    public static void main(String[] args) {
        final Asciidoctor asciidoctor = Asciidoctor.Factory.create();

        final File file = file("ast.adoc");
        final OptionsBuilder options = OptionsBuilder.options()
            .backend("html");

        asciidoctor.javaExtensionRegistry()
            .treeprocessor(Tre.class);

        Document document = asciidoctor.loadFile(file, options.asMap());
        String author = (String) document.getAttribute("author");

//        asciidoctor.unregisterAllExtensions();

//        asciidoctor.convertFile(file, options);
    }

    public static void setSourceHighlighter(String backend, AttributesBuilder attributes) {
        if (backend.equals("pdf")) {
            attributes.sourceHighlighter("rouge");
        } else
            attributes.sourceHighlighter("highlightjs");
    }
}
