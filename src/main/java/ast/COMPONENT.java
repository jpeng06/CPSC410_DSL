package ast;

import ui.Main;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

public class COMPONENT extends STATEMENT {

    String name = "";
    double mark = 0;

    @Override
    public void parse() {
        tokenizer.getAndCheckNext("component");
        tokenizer.getAndCheckNext(":");
        name = tokenizer.getNext();
        tokenizer.getAndCheckNext(",");
        tokenizer.getAndCheckNext("weight");
        tokenizer.getAndCheckNext(":");
        mark = Double.parseDouble(tokenizer.getNext());
        tokenizer.getAndCheckNext(";");
    }

    @Override
    public String evaluate() throws FileNotFoundException, UnsupportedEncodingException {
        // todo: Main.symbolTable.put();
        return null;
    }
}
