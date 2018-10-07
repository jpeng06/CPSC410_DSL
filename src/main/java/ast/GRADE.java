package ast;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

public class GRADE extends STATEMENT {

    String name;
    double mark;

    @Override
    public void parse() {
        tokenizer.getAndCheckNext("grade");
        tokenizer.getAndCheckNext(":");
        name = tokenizer.getNext();
        tokenizer.getAndCheckNext(",");
        tokenizer.getAndCheckNext("mark");
        tokenizer.getAndCheckNext(":");
        mark = Double.parseDouble(tokenizer.getNext());
        tokenizer.getAndCheckNext(";");
    }

    @Override
    public String evaluate() throws FileNotFoundException, UnsupportedEncodingException {
        return null;
    }
}
