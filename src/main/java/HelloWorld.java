import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * author : lch
 * date : 2014/12/10
 * depiction :
 */
public class HelloWorld {
    public static void main(String[] args) throws IOException {
        File outDir = new File("/home/vagrant/output");
        if (!outDir.exists()) {
            outDir.mkdirs();
        }
        DateFormat df = new SimpleDateFormat("HH-mm-ss");


        File outputFile = new File(outDir, "output_" + df.format(new Date()) + ".log");
        System.out.println(outputFile.getCanonicalPath());
        if (!outputFile.exists()) {
            outputFile.createNewFile();
        }

        if (args == null || args.length == 0) {
            FileUtils.writeStringToFile(outputFile, "args is null");
        } else {
            for (String s : args) {
                s = s + IOUtils.LINE_SEPARATOR_UNIX;
                FileUtils.writeStringToFile(outputFile, s, true);
            }
            FileUtils.writeStringToFile(outputFile, "args length is " + args.length, true);
        }
    }
}
