package pkg.r2d2.word;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Utils {

    public static Element findElementOneResultByCssQuery(Element element, String cssQuery) {
        Elements elements = element.select(cssQuery);
        if (elements != null && elements.size() > 0) {
            return elements.get(0);
        }
        return null;
    }

    public static Element finElementOneResultByClass(Element element, String className) {
        Elements elements = element.getElementsByClass(className);
        if (elements != null && elements.size() > 0) {
            return elements.get(0);
        }
        return null;
    }

    interface IFindElementRunnable {
        Elements findItems(Element element);
    }


}
