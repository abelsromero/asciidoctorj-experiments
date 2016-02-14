package org.asciidoctor.demos;

import org.asciidoctor.Asciidoctor;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by abelsr on 13/02/2016.
 */
public class GenerateFile {

    private static final long serialVersionUID = 1L;
    private String fileName;
    private FileWriter fw;
    private BufferedWriter bw;
    private String basepath;

    public GenerateFile() throws IOException {

//        fileName = Document.getName();
        fileName = "example-manual";
        File file = new File(fileName + ".adoc");

        if (!file.exists()) {
            file.createNewFile();
        }

//        basepath = "C:\\Brochures\\";
        basepath = "src\\asciidoc";

        /*
        fw = new FileWriter(basepath + fileName + ".adoc");
        bw = new BufferedWriter(fw);
        bw.write("===testing"); // just an example
        bw.close();
        */

        // null is required to create isolates Asciidoctor from the system gems (default planned for 1.6.0)
        Asciidoctor asciidoctor = Asciidoctor.Factory.create((String)null);

        Map<String, Object> options = new HashMap<String, Object>();
        options.put("backend", "pdf");
        options.put("in_place", true);

        /*
        OptionsBuilder options = OptionsBuilder.options();
        options.safe(SafeMode.UNSAFE);
        options.backend("pdf");//.inPlace(true);
*/
        asciidoctor.convertFile(new File(basepath,fileName+".adoc"), options);

        /*
        try {
            Resource resource = new FileResource(new File("C:\\Brochures\\" + fileName + ".pdf"));
            FileDownloader fileDownloader = new FileDownloader(resource);
            fileDownloader.extend(MasterBrochureTab.getDownloadBrochureButton());
        } catch (Exception e) {
            Notification.show("You need to generate a brochure, and then download it.");
        }
        */
    }

    public static void main(String[] args) throws IOException {
        GenerateFile gf = new GenerateFile();
    }

}
