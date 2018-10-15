package ast;

import ui.Main;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class COURSE extends STATEMENT {

    String name;

    ArrayList<COMPONENTDEC> components = new ArrayList<>();

    @Override
    public void parse() {
        tokenizer.getAndCheckNext("COURSE");
        name = tokenizer.getNext();//dept
        name += tokenizer.getNext();//code

        tokenizer.getAndCheckNext("\\(");

        while (!tokenizer.checkToken("\\)")) {
            COMPONENTDEC c = new COMPONENTDEC();
            c.parse();
            components.add(c);
        }
        tokenizer.getAndCheckNext("\\)");

    }

    @Override
    public String evaluate() throws FileNotFoundException, UnsupportedEncodingException {
        Main.symbolTable.put(name, components);
        return null;
    }
}
