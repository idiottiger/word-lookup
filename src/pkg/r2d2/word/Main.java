package pkg.r2d2.word;

public class Main {

    public static void main(String[] args) {
        // write your code here
        ILookup youdaoLookup = new OxfordLookup();
        youdaoLookup.doLookup("carry");
    }
}
