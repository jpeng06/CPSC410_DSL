package ast;

import ui.Main;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class GROUP extends STATEMENT {

    String name;

    ArrayList<GROUPDEC> groups = new ArrayList<>();

    @Override
    public void parse() {
        tokenizer.getAndCheckNext("GROUP");
        name = tokenizer.getNext();//dept
        name += tokenizer.getNext();//code

        tokenizer.getAndCheckNext("\\(");

        while (!tokenizer.checkToken("\\)")) {
            GROUPDEC g = new GROUPDEC();
            g.parse();
            groups.add(g);
        }
        tokenizer.getAndCheckNext("\\)");

    }

    @Override
    public String evaluate() throws FileNotFoundException, UnsupportedEncodingException {
        enterScope(this.toString());
        //Main.symbolTable.put(name + "." + getScope() , groups);
        Main.symbolTable.put(name, groups);
        //leaveScope();
        Main.typeLookup.put(name, "GROUP");
        return null;
    }
}
