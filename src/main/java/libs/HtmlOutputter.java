package libs;

import java.awt.*;
import java.io.*;
import java.nio.channels.FileChannel;

public class HtmlOutputter {
    public static void writeToFile(String file, String s, boolean isAppendMode) {
        try {
            FileWriter fstream = new FileWriter(file, isAppendMode);
            BufferedWriter out = new BufferedWriter(fstream);
            out.write(s);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void openHtmlFile() {
        File htmlFile = new File("mygrades.html");
        try {
            Desktop.getDesktop().browse(htmlFile.toURI());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void copyFile(String sourceFilePath, String destFilePath) throws IOException {
        File sourceFile = new File(sourceFilePath);
        File destFile = new File(destFilePath);

        if(!destFile.exists()) {
            destFile.createNewFile();
        }

        FileChannel source = null;
        FileChannel destination = null;

        try {
            source = new FileInputStream(sourceFile).getChannel();
            destination = new FileOutputStream(destFile).getChannel();
            destination.transferFrom(source, 0, source.size());
        }
        finally {
            if(source != null) {
                source.close();
            }
            if(destination != null) {
                destination.close();
            }
        }
    }
}
