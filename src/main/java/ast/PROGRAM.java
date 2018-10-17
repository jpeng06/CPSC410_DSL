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
        createHtmlTemplate();
        for (STATEMENT s : statements){
            s.evaluate();
        }
        HtmlOutputter.openHtmlFile();
        return null;
    }

    private void createHtmlTemplate() {
        StringBuilder sb = new StringBuilder();
        sb.append("<!DOCTYPE html>");
        sb.append("<html>");
        sb.append("<head>");
        sb.append("<title>My Grades</title>");
        sb.append("<link rel=\"stylesheet\" type=\"text/css\" href=\"vendor/bootstrap/css/bootstrap.min.css\">");
        sb.append("<link rel=\"stylesheet\" type=\"text/css\" href=\"fonts/font-awesome-4.7.0/css/font-awesome.min.css\">");
        sb.append("<link rel=\"stylesheet\" type=\"text/css\" href=\"vendor/animate/animate.css\">");
        sb.append("<link rel=\"stylesheet\" type=\"text/css\" href=\"vendor/select2/select2.min.css\">");
        sb.append("<link rel=\"stylesheet\" type=\"text/css\" href=\"vendor/perfect-scrollbar/perfect-scrollbar.css\">");
        sb.append("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/util.css\">");
        sb.append("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/main.css\">");
        sb.append("</head>");
        sb.append("<body>");
        sb.append("<div id=\"computeDiv\"></div>");
        sb.append("<div id=\"createDiv\"></div>");
        sb.append("<script src=\"scripts.js\"></script>");
        sb.append("<script src=\"html5-canvas-bar-graph.js\"></script>");
        sb.append("<script src=\"vendor/jquery/jquery-3.2.1.min.js\"></script>");
        sb.append("<script src=\"vendor/bootstrap/js/popper.js\"></script>");
        sb.append("<script src=\"vendor/bootstrap/js/bootstrap.min.js\"></script>");
        sb.append("<script src=\"vendor/select2/select2.min.js\"></script>");
        sb.append("<script src=\"vendor/perfect-scrollbar/perfect-scrollbar.min.js\"></script>");
        sb.append("<script>$('.js-pscroll').each(function(){var ps = new PerfectScrollbar(this);$(window).on('resize', function(){ps.update();})});</script>");
        sb.append("<script src=\"js/main.js\"></script>");
        sb.append("</body></html>");
        HtmlOutputter.writeToFile("mygrades.html", sb.toString(), false);
    }
}
