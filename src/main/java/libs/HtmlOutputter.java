package libs;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class HtmlOutputter {
    public static void writeToHtmlFile(String s, boolean isAppendMode) {
        try {
            FileWriter fstream = new FileWriter("mygrades.html", isAppendMode);
            BufferedWriter out = new BufferedWriter(fstream);
            out.write(s);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
