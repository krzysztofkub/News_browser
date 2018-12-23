import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Article {
    public static List<List<String>> sportTitles() {
        Connection connect = Jsoup.connect("https://sport.onet.pl");
        List<List<String>> list = new ArrayList<>();
        try {
            Document document = connect.get();
            Elements links = document.select("section.stream a");
            for (int i = 0; i < links.size(); i++) {
                List<String> article = new ArrayList<>();
                String href = links.get(i).attr("href");
                String title = links.get(i).select("h3").text();
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


    public static List<List<String>> getNextSportArticles(int count) {
        Connection connect = Jsoup.connect("https://sport.onet.pl/?ajax=" + count + "&page=1");
        List<List<String>> list = new ArrayList<>();
        try {
            Document document = connect.get();
            Elements links = document.select("a");
            for (int i = 0; i < links.size(); i++) {
                List<String> article = new ArrayList<>();
                String href = links.get(i).attr("href");
                String title = links.get(i).select("h3").text();
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


    public static List<String> getArticle(String href) {
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


    public static List<List<String>> getAllNewsArticles() {
        Connection connect = Jsoup.connect("https://wiadomosci.onet.pl");
        List<List<String>> list = loadByConnection(connect, "section.stream a");
        return list;
    }


    public static List<List<String>> getAllSportArticles() {
        Connection connect = Jsoup.connect("https://sport.onet.pl");
        List<List<String>> list = loadByConnection(connect, "section.stream a");
        return list;
    }


    public static List<List<String>> getNextNewsArticles(int count) {

        Connection connect = Jsoup.connect("https://wiadomosci.onet.pl/?ajax=" + count + "&page=1");
        List<List<String>> list = loadByConnection(connect, "a.itemBoxNormal");
        return list;
    }


    public static List<List<String>> loadByConnection(Connection connect, String firstSelect) {
        List<List<String>> list = new ArrayList<>();
        try {
            Document document = connect.get();
            Elements links = document.select(firstSelect);
            for (int i = 0; i < links.size(); i++) {
                List<String> article = new ArrayList<>();
                Connection connect2 = Jsoup.connect(links.get(i).attr("href"));
                try {
                    Document document2 = connect2.get();
                    Element title = document2.select("h1.mainTitle").first();
                    Element intro = document2.select("div#lead").first();
                    Element text = document2.select("div#detail").first();
                    Element mainPhoto = document2.select("figure.mainPhoto").first();
                    if (text == null) {
                        text = document2.select("div.articleBody").first();
                    }
                    article.add(title.text());
                    article.add(intro.text());
                    article.add(text.html());
                    article.add(mainPhoto.html());

                    list.add(article);

                } catch (IOException | NullPointerException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }


    public static String getImages(String article) {
        System.out.println(article.replaceAll("src=\"", "src=\"http:").replaceAll("<span class=\"copyright\">(.*)</span>", "").replaceAll("<span class=\"imageDescription\">(.*)</span>",""));
        return article.replaceAll("src=\"", "src=\"http:").replaceAll("<span class=\"copyright\">(.*)</span>", "").replaceAll("<span class=\"imageDescription\">(.*)</span>","");
    }
}
