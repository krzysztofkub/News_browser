import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class Article {
    static List<List<String>> newsTitles() {
        Connection connect = Jsoup.connect("https://wiadomosci.onet.pl");
        return loadByConnection(connect, "section.stream a");
    }


    static List<List<String>> sportTitles() {
        Connection connect = Jsoup.connect("https://sport.onet.pl");
        return loadByConnection(connect, "section.stream a");
    }


    static List<List<String>> getNextNewsArticles(int count) {
        Connection connect = Jsoup.connect("https://wiadomosci.onet.pl/?ajax=1&page=" + count);
        return loadByConnection(connect, "a");
    }


    static List<List<String>> getNextSportArticles(int count) {
        Connection connect = Jsoup.connect("https://sport.onet.pl/?ajax=1&page=" + count);
        return loadByConnection(connect, "a");
    }


    static List<String> getArticle(String href) {
        Connection connect = Jsoup.connect(href);
        List<String> list = new ArrayList<>();
        try {
            Document document = connect.get();
            Element title = document.select("h1.mainTitle").first();
            Element intro = document.select("div#lead").first();
            Element text = document.select("div#detail").first();
            Element mainPhoto = document.select("figure.mainPhoto").first();
            if (text == null) {
                text = document.select("div.articleBody").first();
                if (text == null) {
                    text = new Element("<p></p>");
                }
            }
            if (mainPhoto == null) {
                mainPhoto = new Element("<p></p>");
            }
            list.add(title.text());
            list.add(intro.text());
            list.add(getImages(text.html()));
            list.add(getImages(mainPhoto.html()));
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
        return list;
    }


    private static List<List<String>> loadByConnection(Connection connect, String firstSelect) {
        List<List<String>> list = new ArrayList<>();
        try {
            Document document = connect.get();
            Elements links = document.select(firstSelect);
            for (Element link : links) {
                List<String> article = new ArrayList<>();
                String href = link.attr("href");
                String title = link.select("h3").text();
                if (href != null && title != null && !title.equals("")) {
                    article.add(href);
                    article.add(title);
                    list.add(article);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }


    private static String getImages(String article) {
        return article.replaceAll("src=\"", "src=\"http:").replaceAll("<span class=\"copyright\">(.*)</span>", "").replaceAll("<span class=\"imageDescription\">(.*)</span>", "");
    }
}
