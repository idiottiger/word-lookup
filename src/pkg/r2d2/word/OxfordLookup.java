package pkg.r2d2.word;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class OxfordLookup implements ILookup {

    private static String BASE_URL = "https://en.oxforddictionaries.com/definition/";

    @Override
    public Word doLookup(String word) {

        String url = BASE_URL + word;
        System.out.println("URL: [" + url + "]");

        Word lookUpWord = new Word();

        try {
            Document document = Jsoup.connect(url).get();

            final Elements pronounceList = document.select("header > h2 > a > audio");
            for (Element pronounce : pronounceList) {
                System.out.println("pronounce:" + pronounce);
            }
            System.out.println(lookUpWord);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lookUpWord;
    }
}
