package ast;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

public class GOALDEC extends STATEMENT {

    String name;
    Double mark;

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
        return null;
    }
}
