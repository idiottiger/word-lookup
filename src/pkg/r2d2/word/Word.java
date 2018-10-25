package pkg.r2d2.word;

import java.util.ArrayList;
import java.util.List;

public class Word {
    public String originalText;
    private List<Pronounce> pronounceList = new ArrayList<>();
    private List<String> transList = new ArrayList<>();


    @Override
    public String toString() {
        String text = originalText + "#";
        for (String trans : transList) {
            text += (trans + "\n");
        }
        text += "\n";
        return text;
    }

    public void addPronounce(Pronounce pronounce) {
        pronounceList.add(pronounce);
    }

    public void addTranslate(String text) {
        if (text != null && text.trim().length() > 0)
            transList.add(text);
    }


}
