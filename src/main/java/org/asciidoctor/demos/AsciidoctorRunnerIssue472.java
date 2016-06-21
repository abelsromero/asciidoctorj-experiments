package org.asciidoctor.demos;

import org.apache.commons.io.FileUtils;
import org.asciidoctor.*;
import org.asciidoctor.internal.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class AsciidoctorRunnerIssue472 {

    public static final String SRC_PATH = "src/asciidoc/";

    public static void main(String[] args) throws IOException {
        // null is required to create isolates Asciidoctor from the system gems (default planned for 1.6.0)
//        Asciidoctor asciidoctor = Asciidoctor.Factory.create((String) null);
        Asciidoctor asciidoctor = Asciidoctor.Factory.create();

        String adocContent = IOUtils.readFull(new FileInputStream(file("table-test.adoc"))); // content is below
        String convertedContent = asciidoctor.convert(adocContent, OptionsBuilder.options().backend("html5"));  // exception is thrown
        FileUtils.writeStringToFile(new File("src/main/resources/output.html"), convertedContent);
    }


    public static File file(String filaname) {
        return new File(SRC_PATH, filaname);
    }


}
