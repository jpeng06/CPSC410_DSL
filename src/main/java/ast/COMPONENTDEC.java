package ast;

import libs.HtmlOutputter;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class COMPONENTDEC extends STATEMENT {

    String name;
    Double weight;
    Double grade = -1.0;
    @Override
    public void parse() {
        tokenizer.getAndCheckNext("Name");
        tokenizer.getAndCheckNext(":");
        name = tokenizer.getNext();
        tokenizer.getAndCheckNext(",");
        tokenizer.getAndCheckNext("Weight");
        tokenizer.getAndCheckNext(":");
        weight = Double.parseDouble(tokenizer.getNext());
        if (tokenizer.checkToken(",")) {
            tokenizer.getAndCheckNext(",");
            tokenizer.getAndCheckNext("Grade");
            tokenizer.getAndCheckNext(":");
            grade = Double.parseDouble(tokenizer.getNext());
        }
        tokenizer.getAndCheckNext(";");

    }

    @Override
    public String evaluate() throws FileNotFoundException, UnsupportedEncodingException {
        buildScript();
        return null;
    }

    private void buildScript() {
        StringBuilder sb = new StringBuilder();


        sb.append("var tr = document.createElement(\"tr\");\n");
        sb.append("tr.setAttribute(\"class\", \"row100 body\");\n");
        sb.append("var parentDiv = document.getElementById(\"" + getScope() + "\");\n");
        sb.append("parentDiv.appendChild(tr);\n");

        sb.append("var component = document.createElement(\"td\");\n");
        sb.append("component.setAttribute(\"class\", \"cell100 column1\");\n");
        sb.append("tr.appendChild(component);\n");
        sb.append("var nameNode = document.createTextNode(\"" + name + "\");\n");
        sb.append("component.appendChild(nameNode);\n");

        sb.append("var weight = document.createElement(\"td\");\n");
        sb.append("weight.setAttribute(\"class\", \"cell100 column2\");\n");
        sb.append("tr.appendChild(weight);\n");
        sb.append("var weightName = document.createTextNode(\"" + weight + "\");\n");
        sb.append("weight.appendChild(weightName);\n");

        sb.append("var grade = document.createElement(\"td\");\n");
        sb.append("grade.setAttribute(\"class\", \"cell100 column3\");\n");
        sb.append("tr.appendChild(grade);\n");

        if (grade != -1) {
            sb.append("var gradeName = document.createTextNode(\"" + grade + "\");\n");
            sb.append("grade.appendChild(gradeName);\n");
        }

        HtmlOutputter.writeToFile("scripts.js", sb.toString(), true);
    }
}
