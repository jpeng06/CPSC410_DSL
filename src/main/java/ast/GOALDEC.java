package ast;

import ui.Main;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class GOALDEC extends STATEMENT {

    String name;
    double mark;

    @Override
    public void parse() {
        tokenizer.getAndCheckNext("GOAL");
        name = tokenizer.getNext();
        name += tokenizer.getNext();
        tokenizer.getAndCheckNext("\\(");
        tokenizer.getAndCheckNext("Mark");
        tokenizer.getAndCheckNext(":");
        mark = Double.parseDouble(tokenizer.getNext());
        tokenizer.getAndCheckNext(";");
        tokenizer.getAndCheckNext("\\)");
    }

    @Override
    public String evaluate() throws FileNotFoundException, UnsupportedEncodingException {

        String type = Main.typeLookup.get(name);

        if (type.equals("COURSE")) {
            ArrayList<COMPONENTDEC> components = (ArrayList<COMPONENTDEC>) Main.symbolTable.get(name);


            if (components == null) {
                return null;
            }

            double avg = 0.0;
            double totalWeight = 0.0;

            for (COMPONENTDEC c : components) {
                //if grade is available
                if (c.grade != -1) {
                    avg += c.grade * c.weight;
                    totalWeight += c.weight;
                }
            }

            double remainingWeight = 100 - totalWeight;

            double averageNeeded = (mark * 100 - avg) / remainingWeight;

            System.out.println("You require an average grade of " + averageNeeded + "% on your remaining components to achieve your goal of " + mark + "% for " + name + ".");

            return null;
        } else if (type.equals("GROUP")) {
            // TODO
            return null;
        } else return null;
    }
}
