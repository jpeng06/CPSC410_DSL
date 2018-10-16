package ast;

import libs.HtmlOutputter;
import libs.Node;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PROGRAM extends Node{
    private List<STATEMENT> statements = new ArrayList<>();

    @Override
    public void parse() {
        while (tokenizer.moreTokens()) {
            STATEMENT s = STATEMENT.getSubStatement();
            s.parse();
            statements.add(s);
        }

    }

    @Override
    public String evaluate() throws FileNotFoundException, UnsupportedEncodingException {
        beginHtmlTemplate();
        for (STATEMENT s : statements){
            s.evaluate();
        }
        HtmlOutputter.writeToHtmlFile("</html>", true);
        return null;
    }

    private void beginHtmlTemplate() {
        StringBuilder sb = new StringBuilder();
        sb.append("<!DOCTYPE html>");
        sb.append("<html>");
        sb.append("<head>");
        sb.append("<title>My Grades</title>");
        sb.append("</head>");
        sb.append("<body>");
        sb.append("</body>");
        HtmlOutputter.writeToHtmlFile(sb.toString(), false);
    }
}
