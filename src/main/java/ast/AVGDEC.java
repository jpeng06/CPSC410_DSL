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
    public Double evaluate() throws FileNotFoundException, UnsupportedEncodingException {

        String type = Main.typeLookup.get(name);

        if (type.equals("COURSE")) {
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

            double weightedAvg = avg/totalWeight;

            System.out.println("Your Average for " + name + " is: " + weightedAvg);

            return weightedAvg;
        } else if (type.equals("GROUP")) {
            ArrayList<GROUPDEC> courses = (ArrayList<GROUPDEC>) Main.symbolTable.get(name);

            if (courses == null) {
                return null;
            }

            Double totalAvg = 0.0;

            System.out.println(totalAvg);

            for (GROUPDEC course : courses) {
                AVGDEC avgCalc = new AVGDEC();
                avgCalc.name = course.name;
                totalAvg += avgCalc.evaluate();
                System.out.println(totalAvg);
            }

            System.out.println("Your Average for " + name + " is: " + totalAvg/courses.size());

            return null;
        } else return null;
    }
}
