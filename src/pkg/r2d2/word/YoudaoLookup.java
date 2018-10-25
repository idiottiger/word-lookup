package pkg.r2d2.word;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class YoudaoLookup implements ILookup {

    private static final String BASE_URL = "http://www.youdao.com/w/eng/";

    @Override
    public Word doLookup(String word) {

        String url = BASE_URL + word;
        System.out.println("URL: [" + url + "]");

        Word lookUpWord = new Word();

        try {
            Document document = Jsoup.connect(url).get();

            final Elements pronounceList = document.select("#phrsListTab > h2 > div");

            lookUpWord.originalText = word;


            //pronounce
            for (Element pronounce : pronounceList) {
                Elements items = pronounce.getElementsByClass("pronounce");
                for (Element item : items) {
                    Pronounce pron = new Pronounce();
                    Elements phoneticList = item.getElementsByClass("phonetic");
                    for (Element phonetic : phoneticList) {
                        pron.pronounce = phonetic.ownText();
                    }
                    lookUpWord.addPronounce(pron);
                }
            }


            //normal translate
            Elements items = document.select("#phrsListTab > div");
            for (Element item : items) {
                Elements elementsByClass = item.getElementsByClass("trans-container");
                for (Element e : elementsByClass) {
                    System.out.println("e: [" + e + "]");
                }
            }

            //collin result
            Elements olList = document.select("#collinsResult > div > div > div > div > ul");
            for (Element element : olList) {
                System.out.println(element);
            }

            System.out.println(lookUpWord);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lookUpWord;
    }
}
