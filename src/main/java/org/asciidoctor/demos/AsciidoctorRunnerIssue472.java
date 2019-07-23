package org.asciidoctor.demos;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.asciidoctor.Asciidoctor;
import org.asciidoctor.OptionsBuilder;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


public class AsciidoctorRunnerIssue472 {

    public static final String SRC_PATH = "src/asciidoc/";

    public static void main(String[] args) throws IOException {
//        Asciidoctor asciidoctor = Asciidoctor.Factory.create((String) null);
        Asciidoctor asciidoctor = Asciidoctor.Factory.create();

        String adocContent = IOUtils.toString(new FileInputStream(file("table-test.adoc"))); // content is below
        String convertedContent = asciidoctor.convert(adocContent, OptionsBuilder.options().backend("html5"));  // exception is thrown
        FileUtils.writeStringToFile(new File("src/main/resources/output.html"), convertedContent);
    }


    public static File file(String filaname) {
        return new File(SRC_PATH, filaname);
    }


}
