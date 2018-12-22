package sample;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Article {

    public static List<List<String>> getAllArticles() {
        Connection connect = Jsoup.connect("https://wiadomosci.onet.pl");
        List<List<String>> list = new ArrayList<>();
        try {
            Document document = connect.get();
            Elements links = document.select("section.stream a");

            for (int i = 0; i < links.size(); i++) {
                List<String> article = new ArrayList<>();
                Connection connect2 = Jsoup.connect(links.get(i).attr("href"));
                try {
                    Document document2 = connect2.get();
                    Element title = document2.select("h1.mainTitle").first();
                    Element intro = document2.select("div#lead").first();
                    Elements paragraphs = document2.select("div#detail");
                    if (paragraphs.size() == 0) {
                        paragraphs = document2.select("div.articleBody");
                    }
                    article.add(title.text());
                    article.add(intro.text());

                    StringBuilder stringBuilder = new StringBuilder();
                    for (Element element : paragraphs) {
                        stringBuilder.append(element.text());
                    }
                    article.add(paragraphs.html());

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

    public static List<List<String>> getNextArticles(int count) {

        Connection connect = Jsoup.connect("https://wiadomosci.onet.pl/?ajax=" + count +"&page=1");
        List<List<String>> list = loadByConnection(connect);
        return list;

    }

    public static List<List<String>> loadByConnection(Connection connect) {
        List<List<String>> list = new ArrayList<>();
        try {
            Document document = connect.get();
            Elements links = document.select("a.itemBoxNormal");
            for (int i = 0; i < links.size(); i++) {
                List<String> article = new ArrayList<>();
                Connection connect2 = Jsoup.connect(links.get(i).attr("href"));
                try {
                    Document document2 = connect2.get();
                    Element title = document2.select("h1.mainTitle").first();
                    Element intro = document2.select("div#lead").first();
                    Elements paragraphs = document2.select("div#detail");
                    if (paragraphs.size() == 0) {
                        paragraphs = document2.select("div.articleBody");
                    }
                    article.add(title.text());
                    article.add(intro.text());

                    StringBuilder stringBuilder = new StringBuilder();
                    for (Element element : paragraphs) {
                        stringBuilder.append(element.text());
                    }
                    article.add(paragraphs.html());

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



}
