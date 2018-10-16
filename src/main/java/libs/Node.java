package libs;


import java.io.*;
import java.util.LinkedList;

public abstract class Node {
    protected static Tokenizer tokenizer = Tokenizer.getTokenizer();
    static protected PrintWriter writer;
    static private LinkedList<String> scope = new LinkedList();

    abstract public void parse();
    abstract public Object evaluate() throws FileNotFoundException, UnsupportedEncodingException;

    public static void enterScope(String s){
        System.out.println("Entering scope "+s);
        scope.addFirst(s);
    }
    public static void leaveScope(){
        scope.removeFirst();
    }
    public static String getScope(){
        return scope.getFirst().toString();
    }
    public static String getGlobal(){
        return scope.getLast().toString();
    }
}
