package org.asciidoctor.demos.extensions;

import org.asciidoctor.Asciidoctor;
import org.asciidoctor.jruby.extension.spi.ExtensionRegistry;

public class TerminalCommandExtension implements ExtensionRegistry {

    @Override
    public void register(Asciidoctor asciidoctor) {
        asciidoctor.javaExtensionRegistry().preprocessor(CommentPreprocessor.class);
    }

}
