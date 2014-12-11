import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

/**
 * author : lch
 * date : 2014/12/10
 * depiction :
 */
public class HelloWorld {
    public static void main(String[] args) throws Exception {
        printToFile(args);
    }


    public static void printToFile(String[] args) throws Exception {
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
            FileUtils.writeStringToFile(outputFile, "args is null" + IOUtils.LINE_SEPARATOR_UNIX);
        } else {
            for (int i = 0; i < args.length; i++) {
                String s = args[i];
                FileUtils.writeStringToFile(outputFile, "arg[" + i + "] is " + s + IOUtils.LINE_SEPARATOR_UNIX, true);
            }
            FileUtils.writeStringToFile(outputFile, "args length is " + args.length + IOUtils.LINE_SEPARATOR_UNIX, true);
        }

        // print sysproperties
        Map m = System.getenv();
        FileUtils.writeStringToFile(outputFile, "------------------ env -- start -----------------------" + IOUtils.LINE_SEPARATOR_UNIX, true);
        for (Iterator it = m.keySet().iterator(); it.hasNext(); ) {
            String key = (String) it.next();
            String value = (String) m.get(key);
            System.out.println(key + ":" + value);
            FileUtils.writeStringToFile(outputFile, key + ":" + value + IOUtils.LINE_SEPARATOR_UNIX, true);
        }
        FileUtils.writeStringToFile(outputFile, "------------------ env -- end -----------------------" + IOUtils.LINE_SEPARATOR_UNIX, true);
        FileUtils.writeStringToFile(outputFile, IOUtils.LINE_SEPARATOR_UNIX, true);
        FileUtils.writeStringToFile(outputFile, "---------------- jvm -- start ---------------------" + IOUtils.LINE_SEPARATOR_UNIX, true);
        Properties p = System.getProperties();
        for (Iterator it = p.keySet().iterator(); it.hasNext(); ) {
            String key = (String) it.next();
            String value = (String) p.get(key);
            System.out.println(key + ":" + value);
            FileUtils.writeStringToFile(outputFile, key + " ---> " + value + IOUtils.LINE_SEPARATOR_UNIX, true);
        }
        FileUtils.writeStringToFile(outputFile, "---------------- jvm -- end ---------------------" + IOUtils.LINE_SEPARATOR_UNIX, true);
    }
}
