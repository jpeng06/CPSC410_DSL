package ast;

import ui.Main;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class AVGDEC extends STATEMENT {

    String name;

    @Override
    public void parse() {
        tokenizer.getAndCheckNext("AVG");
        name = tokenizer.getNext();
        name += tokenizer.getNext();
        tokenizer.getAndCheckNext("\\(");
        tokenizer.getAndCheckNext("\\)");
    }

    @Override
    public String evaluate() throws FileNotFoundException, UnsupportedEncodingException {

        ArrayList<COMPONENTDEC> components = (ArrayList<COMPONENTDEC>) Main.symbolTable.get(name);


        if (components == null) {
            return null;
        }

        Double avg = 0.0;
        Double totalWeight = 0.0;

        for (COMPONENTDEC c : components) {
            //if grade is available
            if (c.grade != -1) {
                avg += c.grade * c.weight;
                totalWeight += c.weight;
            }
        }


        System.out.println("Your Average for " + name + " is: " + avg/totalWeight);

        return null;
    }
}
