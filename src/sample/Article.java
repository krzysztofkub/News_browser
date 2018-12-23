package sample;

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


    public static List<List<String>> getNextArticles(int count) {

        Connection connect = Jsoup.connect("https://wiadomosci.onet.pl/?ajax=" + count + "&page=1");
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
        System.out.println(article.replaceAll("src=\"", "src=\"http:").replaceAll("<span class=\"copyright\">(.*)</span>",""));
        return article.replaceAll("src=\"", "src=\"http:").replaceAll("<span class=\"copyright\">(.*)</span>","");
    }




}
