package ast;

import libs.Colours;
import libs.HtmlOutputter;
import ui.Main;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class AVGDEC extends STATEMENT {

    private String name;

    private List<String> xAxisLabels = new ArrayList<>();
    private List<Double> barGraphValues = new ArrayList<>();
    private List<String> barGraphColours = new ArrayList<>();

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
        enterScope(this.toString());

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

                    xAxisLabels.add(c.name);
                    barGraphValues.add(c.grade);
                    barGraphColours.add(Colours.getNext());
                }
            }

            double weightedAvg = avg/totalWeight;

            xAxisLabels.add("Average");
            barGraphValues.add(weightedAvg);
            //barGraphColours.add(Colours.getNext());
            barGraphColours.add("#ffc527");

            System.out.println("Your Average for " + name + " is: " + weightedAvg);

            buildScript();
            leaveScope();
            return weightedAvg;
        } else if (type.equals("GROUP")) {
            ArrayList<GROUPDEC> courses = (ArrayList<GROUPDEC>) Main.symbolTable.get(name);

            if (courses == null) {
                return null;
            }

            Double totalAvg = 0.0;

            for (GROUPDEC course : courses) {
                AVGDEC avgCalc = new AVGDEC();
                avgCalc.name = course.name;
                Double average = avgCalc.evaluate();
                totalAvg += average;

                xAxisLabels.add(course.name);
                barGraphValues.add(average);
                barGraphColours.add(Colours.getNext());
            }

            double weightedAvg = totalAvg/courses.size();

            xAxisLabels.add("Average");
            barGraphValues.add(weightedAvg);
            //barGraphColours.add(Colours.getNext());
            barGraphColours.add("#ffc527");

            System.out.println("Your Average for " + name + " is: " + weightedAvg);

            buildScript();
            leaveScope();
            return null;
        } else {
            leaveScope();
            return null;
        }
    }

    private void buildScript() {
        StringBuilder sb = new StringBuilder();
        sb.append("var newDiv = document.createElement(\"div\");\n");
        sb.append("newDiv.setAttribute(\"id\", \"" + getScope() + "\");\n");
        sb.append("var parent = document.getElementById(\"computeDiv\");\n");
        sb.append("parent.appendChild(newDiv);\n");

        sb.append("var title = document.createElement(\"h1\");\n");
        sb.append("newDiv.appendChild(title);\n");
        sb.append("var nameNode = document.createTextNode(\"Average for " + name + "\");\n");
        sb.append("title.appendChild(nameNode);\n");

        sb.append("var ctx = createCanvas(\"" + getScope() + "\");\n");
        sb.append("var graph = new BarGraph(ctx);\n");
        sb.append("graph.maxValue = 100;\n");
        sb.append("graph.margin = 2;\n");
        sb.append("graph.width = 1000;\n");
        sb.append("graph.height = 300;\n");
        sb.append("graph.animationInterval = 300;\n");
        sb.append("graph.animationSteps = 30;\n");
        sb.append("graph.colors = " + stringifyStringList(barGraphColours) + ";\n");
        sb.append("graph.xAxisLabelArr = " + stringifyStringList(xAxisLabels) + ";\n");
        List<Double> initialValues = new ArrayList<>(xAxisLabels.size());
        for (int i = 0; i < xAxisLabels.size(); i++) {
            initialValues.add(i, 0.0);
        }
        sb.append("graph.update(" + stringifyDoubleList(initialValues) + ");\n");
        sb.append("graph.update(" + stringifyDoubleList(barGraphValues) + ");\n");

        sb.append("var hr = document.createElement('hr');\n");
        sb.append("parent.appendChild(hr);\n");

        HtmlOutputter.writeToFile("scripts.js", sb.toString(), true);
    }

    private String stringifyStringList(List<String> list) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < list.size(); i++) {
            sb.append("\"");
            sb.append(list.get(i));
            sb.append("\"");
            if (i < list.size() - 1) {
                sb.append(",");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    private String stringifyDoubleList(List<Double> list) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
            if (i < list.size() - 1) {
                sb.append(",");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
