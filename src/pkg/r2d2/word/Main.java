package pkg.r2d2.word;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // write your code here
        ILookup youdaoLookup = new SimpleYoudaoLookup();

        try {
            List<String> lines = FileUtils.readLines(new File("in.txt"), "utf-8");
            List<String> outLines = new ArrayList<>();
            int count = 0;
            for (String line : lines) {
                if (line.trim().length() >= 3) {
                    count++;
                    System.out.println("look up:  " + line.trim());
                    outLines.add(youdaoLookup.doLookup(line.trim()).toString());
                }
            }
            FileUtils.writeLines(new File("out.txt"), outLines);
            System.out.println("look up finished: count: " + count);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
