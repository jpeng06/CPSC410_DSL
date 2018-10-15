package ast;

import libs.Node;

public abstract class STATEMENT extends Node {
    public static STATEMENT getSubStatement() {

        if (tokenizer.checkToken("CREATE")) {
            return new CREATEDEC();
        } else if (tokenizer.checkToken("COMPUTE")) {
            return new COMPUTEDEC();
        }
        else return null;
    }
}
