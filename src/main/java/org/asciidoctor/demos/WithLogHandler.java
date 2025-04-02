package org.asciidoctor.demos;

import org.asciidoctor.Asciidoctor;
import org.asciidoctor.Options;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.asciidoctor.demos.utils.FileUtils.file;

/**
 * Notes:
 * - LogHandler is instantiated only once for all Asciidoctor instances.
 * - Extension Registry is invoked once for each instance and one Extension instance is created.
 */
class WithLogHandler {

    public static void main(String[] args) {

        List<Asciidoctor> asciidoctors = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Asciidoctor asciidoctor = Asciidoctor.Factory.create();
//            Asciidoctor asciidoctor = JRubyAsciidoctor.create();
            asciidoctors.add(asciidoctor);
        }

        final File source = file("document-with-errors.adoc");
        final Options options = Options.builder().build();
        asciidoctors.forEach(asciidoctor -> {
            ClassLoader classLoader = asciidoctor.getClass().getClassLoader();
            System.out.println("Classloader: " + classLoader);
            asciidoctor.convertFile(source, options);
        });

    }

}
