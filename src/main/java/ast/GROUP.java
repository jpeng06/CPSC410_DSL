package ast;

import libs.HtmlOutputter;
import ui.Main;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class GROUP extends STATEMENT {

    String name;

    ArrayList<GROUPDEC> groups = new ArrayList<>();

    @Override
    public void parse() {
        tokenizer.getAndCheckNext("GROUP");
        name = tokenizer.getNext();//dept
        name += tokenizer.getNext();//code

        tokenizer.getAndCheckNext("\\(");

        while (!tokenizer.checkToken("\\)")) {
            GROUPDEC g = new GROUPDEC();
            g.parse();
            groups.add(g);
        }
        tokenizer.getAndCheckNext("\\)");

    }

    @Override
    public String evaluate() throws FileNotFoundException, UnsupportedEncodingException {
        buildScript();
        Main.symbolTable.put(name, groups);
        Main.typeLookup.put(name, "GROUP");
        for (GROUPDEC group : groups) {
            group.evaluate();
        }
        leaveScope();
        return null;
    }

    private void buildScript() {
        StringBuilder sb = new StringBuilder();
        sb.append("var head = document.createElement(\"div\");\n");
        sb.append("head.setAttribute(\"class\", \"table100-head\");\n");
        sb.append("var parentDiv = document.getElementById(\"" + getScope() + "\");\n");
        sb.append("parentDiv.appendChild(head);\n");
        sb.append("var table = document.createElement(\"table\");\n");
        sb.append("head.appendChild(table);\n");
        sb.append("var thead = document.createElement(\"thead\");\n");
        sb.append("table.appendChild(thead);\n");
        sb.append("var tr = document.createElement(\"tr\");\n");
        sb.append("tr.setAttribute(\"class\", \"row100 head\");\n");
        sb.append("thead.appendChild(tr);\n");
        sb.append("var course = document.createElement(\"th\");\n");
        sb.append("course.setAttribute(\"class\", \"cell100 column1\");\n");
        sb.append("tr.appendChild(course);\n");
        sb.append("var courseName = document.createTextNode(\"" + name + "\");\n");
        sb.append("course.appendChild(courseName);\n");
        sb.append("var body = document.createElement(\"div\");\n");
        sb.append("body.setAttribute(\"class\", \"table100-body js-pscroll\");\n");
        sb.append("parentDiv.appendChild(body);\n");
        sb.append("var table2 = document.createElement(\"table\");\n");
        sb.append("body.appendChild(table2);\n");
        sb.append("var tbody = document.createElement(\"tbody\");\n");
        enterScope(this.toString());
        sb.append("tbody.setAttribute(\"id\", \"" + getScope() + "\");\n");
        sb.append("table2.appendChild(tbody);\n");
        HtmlOutputter.writeToFile("scripts.js", sb.toString(), true);
    }
}
