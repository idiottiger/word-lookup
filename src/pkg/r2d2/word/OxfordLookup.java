package pkg.r2d2.word;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class OxfordLookup implements ILookup {

    private static String BASE_URL = "https://en.oxforddictionaries.com/definition/";

    private void processGramb(Word lookUpWord, Element element) {
        //get prop
        Element posItem = Utils.finElementOneResultByClass(element, "pos");
        if (posItem != null) {
            System.out.println("prop:" + posItem.text());
        }




        //introduce
        Elements indList = element.getElementsByClass("ind");
        for (Element item : indList) {
            if ("subSense".equals(item.parent().className())) {
                System.out.println("    ind: " + item);
            } else {
                System.out.println("ind: " + item);
            }
        }

        //exmaple

        //#content > div.lex-container > div.main-content > div > div > div > div.entryWrapper > section:nth-child(12) > ul > li:nth-child(1) > div > div.exg > div > em
    }

    @Override
    public Word doLookup(String word) {

        String url = BASE_URL + word;
        System.out.println("URL: [" + url + "]");

        Word lookUpWord = new Word();
        lookUpWord.originalText = word;

        try {
            Document document = Jsoup.connect(url).get();

            Pronounce pronounce = new Pronounce();

            Element pron = Utils.findElementOneResultByCssQuery(document, "#content > div.lex-container > div.main-content > div > div > div > div.entryWrapper > section.pronSection.etym > div > span.phoneticspelling");
            pronounce.pronounce = pron == null ? "" : pron.ownText();

            Element audio = Utils.findElementOneResultByCssQuery(document, "header > h2 > a > audio");
            if (audio != null) {
                pronounce.audioUrl = audio.attr("src");
            }
            lookUpWord.addPronounce(pronounce);

            Elements grambList = document.getElementsByClass("gramb");
            for (Element item : grambList) {
                //System.out.println("item: "+ item);
                processGramb(lookUpWord, item);
            }

            System.out.println(lookUpWord);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lookUpWord;
    }
}
