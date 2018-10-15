package ast;

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
        return null;
    }
}
