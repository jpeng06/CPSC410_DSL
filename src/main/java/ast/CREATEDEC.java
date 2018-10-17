package ast;

import libs.HtmlOutputter;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

public class CREATEDEC extends STATEMENT {

    COURSE course;
    GROUP group;

    @Override
    public void parse() {
        tokenizer.getAndCheckNext("CREATE");
        if (tokenizer.checkToken("COURSE")) {
            course = new COURSE();
            course.parse();
        } else {
            group = new GROUP();
            group.parse();
        }
    }

    @Override
    public String evaluate() throws FileNotFoundException, UnsupportedEncodingException {
        enterScope(this.toString());
        buildScript();
        if (course != null) {
            course.evaluate();
        } else {
            group.evaluate();
        }
        leaveScope();
        return null;
    }

    private void buildScript() {
        StringBuilder sb = new StringBuilder();
        sb.append("var limiter = document.createElement(\"div\");\n");
        sb.append("limiter.setAttribute(\"class\", \"limiter\");\n");
        sb.append("var createDiv = document.getElementById(\"createDiv\");\n");
        sb.append("createDiv.appendChild(limiter);\n");
        sb.append("var container = document.createElement(\"div\");\n");
        sb.append("container.setAttribute(\"class\", \"container-table100\");\n");
        sb.append("limiter.appendChild(container);\n");
        sb.append("var wrap = document.createElement(\"div\");\n");
        sb.append("wrap.setAttribute(\"class\", \"wrap-table100\");\n");
        sb.append("container.appendChild(wrap);\n");
        sb.append("var table = document.createElement(\"div\");\n");
        sb.append("table.setAttribute(\"class\", \"table100 ver1 m-b-110\");\n");
        sb.append("table.setAttribute(\"id\", \"" + getScope() + "\");\n");
        sb.append("wrap.appendChild(table);\n");
        HtmlOutputter.writeToFile("scripts.js", sb.toString(), true);
    }
}
