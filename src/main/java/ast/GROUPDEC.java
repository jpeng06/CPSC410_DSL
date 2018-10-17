package ast;

import libs.HtmlOutputter;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

public class GROUPDEC extends STATEMENT {

    String name;

    @Override
    public void parse() {
        tokenizer.getAndCheckNext("Name");
        tokenizer.getAndCheckNext(":");
        name = tokenizer.getNext();
        name += tokenizer.getNext();
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

        HtmlOutputter.writeToFile("scripts.js", sb.toString(), true);
    }
}
