package pkg.r2d2.word;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class SimpleYoudaoLookup implements ILookup {

    private static final String BASE_URL = "http://www.youdao.com/w/eng/";

    @Override
    public Word doLookup(String word) {

        String url = BASE_URL + word;
        //System.out.println("URL: [" + url + "]");

        Word lookUpWord = new Word();
        lookUpWord.originalText = word;

        try {
            Document document = Jsoup.connect(url).get();

            final Elements tranList = document.select("#phrsListTab > div.trans-container > ul");
            for (Element item : tranList) {
                Elements liList = item.select("li");
                for (Element li : liList) {
                    lookUpWord.addTranslate(li.text());
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return lookUpWord;
    }
}
