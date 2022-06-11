package org.asciidoctor.demos;

import org.asciidoctor.ast.ContentNode;
import org.asciidoctor.ast.StructuralNode;

import java.util.function.Function;

class NodesTraverser {

    void processNode(StructuralNode node, int depth) {
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
