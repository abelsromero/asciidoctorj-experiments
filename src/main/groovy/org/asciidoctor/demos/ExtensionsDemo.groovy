package org.asciidoctor.demos

import org.asciidoctor.Asciidoctor
import org.asciidoctor.groovydsl.AsciidoctorExtensions

AsciidoctorExtensions.extensions {
    block(name: 'BIG', contexts: [':paragraph']) {
        parent, reader, attributes ->
            def upperLines = reader.readLines()
                    .collect { it.toUpperCase() }
                    .inject('') { a, b -> a + '\n' + b }

            createBlock(parent, 'paragraph', [upperLines], attributes, [:])
    }
}

println(Asciidoctor.Factory.create((String)null).render('''
[BIG]
Hello World
''', [:]))
