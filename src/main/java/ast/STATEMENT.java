package ast;

import libs.Node;

public  abstract class STATEMENT extends Node {
    public static STATEMENT getSubStatement() {

        if (tokenizer.checkToken("component")) {
            return new COMPONENT();
        } else if (tokenizer.checkToken("grade")) {
            return new GRADE();
        }
        else return null;
    }
}
