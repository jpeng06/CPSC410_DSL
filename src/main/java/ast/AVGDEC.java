package ast;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

public class AVGDEC extends STATEMENT {

    String name;

    @Override
    public void parse() {
        tokenizer.getAndCheckNext("AVG");
        name = tokenizer.getNext();
        name += tokenizer.getNext();
        tokenizer.getAndCheckNext("\\(");
        tokenizer.getAndCheckNext("\\)");
    }

    @Override
    public String evaluate() throws FileNotFoundException, UnsupportedEncodingException {
        return null;
    }
}
