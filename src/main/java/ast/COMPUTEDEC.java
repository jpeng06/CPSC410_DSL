package ast;

import libs.HtmlOutputter;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

public class COMPUTEDEC extends STATEMENT {

    AVGDEC avg;
    GOALDEC goal;

    @Override
    public void parse() {
        tokenizer.getAndCheckNext("COMPUTE");
        if (tokenizer.checkToken("AVG")) {
            avg = new AVGDEC();
            avg.parse();
        } else {
            goal = new GOALDEC();
            goal.parse();
        }
    }

    @Override
    public String evaluate() throws FileNotFoundException, UnsupportedEncodingException {
        if (avg != null) {
            avg.evaluate();
        } else {
            goal.evaluate();
        }
        return null;
    }
}
