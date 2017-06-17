import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Main {
    String myURL = "https://www.cgv.vn/default/cinox/reserve/cgv-iph-ha-noi";
    public Main() {
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.processPage("myURL");

    }

    private void processPage(String url) throws IOException {
//        Document doc = Jsoup.connect(url).get();
//        Document doc = Jsoup.connect("http://en.wikipedia.org/").get();
//        Elements newsHeadlines = doc.select("#mp-itn b a");
//        System.out.println(doc.toString());
//        for (Element element: newsHeadlines) {
//            System.out.println("Main.processPage "+element.toString());
//        }
        String html = "<div><p>Lorem ipsum.</p>";
        Document doc = Jsoup.parseBodyFragment(html);
        Element body = doc.body();
        System.out.println("Main.processPage "+body.toString());
    }
}
