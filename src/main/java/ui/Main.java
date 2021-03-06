package ui;

import ast.PROGRAM;
import libs.HtmlOutputter;
import libs.Tokenizer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static Map<String,Object> symbolTable = new HashMap<>();
    public static Map<String, String> typeLookup = new HashMap<>();

    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        List<String> literals = Arrays.asList("CREATE", "COMPUTE", "COURSE", "GROUP", "AVG", "GOAL", "Name", "Weight", "Mark", "(", ")", ":", ",", ";");
        Tokenizer.makeTokenizer("input.tvar",literals);
        HtmlOutputter.writeToFile("scripts.js", "", false);
        try {
            HtmlOutputter.copyFile("html5-canvas-bar-graph.js", "scripts.js");
        } catch (IOException e) {
            e.printStackTrace();
        }
        HtmlOutputter.writeToFile("mygrades.html", "", false);
        PROGRAM p = new PROGRAM();
        p.parse();
        p.evaluate();
        System.out.println("completed successfully");
        System.out.println(symbolTable);
    }

}
