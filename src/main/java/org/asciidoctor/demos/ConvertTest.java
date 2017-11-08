package org.asciidoctor.demos;

import org.asciidoctor.AsciiDocDirectoryWalker;
import org.asciidoctor.Asciidoctor;
import org.asciidoctor.AttributesBuilder;
import org.asciidoctor.OptionsBuilder;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by asalgadr on 09/04/2017.
 */
public class ConvertTest {

    public static void main(final String[] args) {
        Asciidoctor asciidoctor = Asciidoctor.Factory.create();

        String html2 = asciidoctor.convert(
                "Writing AsciiDoc is _easy_!",
                new HashMap<String, Object>());
        System.out.println(html2);


        Map<String, Object> attributes = AttributesBuilder.attributes()
                .backend("html")
                .asMap();

        Map<String, Object> options = OptionsBuilder.options()
                .inPlace(false)
                .toFile(false)
                .attributes(attributes)
                .asMap();


        String[] result2 = asciidoctor.convertDirectory(
                new AsciiDocDirectoryWalker("src/asciidoc/"), new HashMap<String, Object>());

        String[] result = asciidoctor.convertDirectory(
                new AsciiDocDirectoryWalker("src/asciidoc/"), options);
        System.out.println("Created files:");
        for (String html : result) {
            System.out.println(html);
        }
        System.out.println(result.length);
        System.out.println("finished");
    }

}
