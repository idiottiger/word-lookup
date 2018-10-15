package pkg.r2d2.word;

import java.util.ArrayList;
import java.util.List;

public class Word {
    public String originalText;
    private List<Pronounce> pronounceList = new ArrayList<>();


    @Override
    public String toString() {
        return "Word{" +
                "originalText='" + originalText + '\'' +
                ", pronounceList=" + pronounceList +
                '}';
    }

    public void addPronounce(Pronounce pronounce) {
        pronounceList.add(pronounce);
    }
}
