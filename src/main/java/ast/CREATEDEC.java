package ast;

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
        return null;
    }
}
