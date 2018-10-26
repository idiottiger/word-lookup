package pkg.r2d2.word;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final int EVERY_FILE_WORD_COUNT_LIMIT = 300;

    public static void main(String[] args) {
        // write your code here
        ILookup youdaoLookup = new SimpleYoudaoLookup();

        try {
            List<String> lines = FileUtils.readLines(new File("in.txt"), "utf-8");
            List<String> outLines = new ArrayList<>();
            int count = 0;
            int wordCount = 0;
            int fileIndex = 0;
            for (String line : lines) {
                if (line.trim().length() >= 3) {
                    count++;
                    wordCount++;
                    System.out.println(" look up:  " + line.trim());
                    outLines.add(youdaoLookup.doLookup(line.trim()).toString());

                    if (wordCount >= EVERY_FILE_WORD_COUNT_LIMIT) {
                        ++fileIndex;
                        FileUtils.writeLines(new File("wordOut", "out_" + fileIndex + ".txt"), outLines);
                        outLines.clear();
                        wordCount = 0;

                        System.out.println(">>>>>>>> write result to file: out_" + fileIndex);
                    }
                }
            }

            if (!outLines.isEmpty()) {
                ++fileIndex;
                FileUtils.writeLines(new File("wordOut", "out_" + fileIndex + ".txt"), outLines);

                System.out.println(">>>>>>>> write result to file: out_" + fileIndex);
            }
            System.out.println(" ******* look up finished: count: " + count);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
