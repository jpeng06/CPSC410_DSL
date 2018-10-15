package ast;

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
        if (tokenizer.checkToken("Grade")) {
            grade = Double.parseDouble(tokenizer.getNext());
        }
        tokenizer.getAndCheckNext(";");

    }

    @Override
    public String evaluate() throws FileNotFoundException, UnsupportedEncodingException {
        return null;
    }
}
