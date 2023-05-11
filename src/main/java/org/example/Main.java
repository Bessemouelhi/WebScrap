package org.example;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebResponse;
import com.gargoylesoftware.htmlunit.html.*;
import org.example.Model.Pokemon;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    record Player(String name, String img, Integer price) {
    }

    public static void main(String[] args) {

        int i = 1;
        String url = "https://scrapeme.live/shop/page/" + i + "/";
        List<Pokemon> pokeList = new ArrayList<>();

        boolean isNotEmpty = true;

        while (isNotEmpty)
        {
            try
            {
                url = "https://scrapeme.live/shop/page/" + i + "/";
                final Document document = Jsoup.connect(url).get();
                Elements elements = document.select("li.product");
                if (elements.size() > 0) {
                    for (Element row : elements) {
                        String name = row.select("h2").text();
                        String urlImage = row.select("img").attr("src");

                        //System.out.println(i);
                        System.out.println(row);
                        System.out.println(name);
                        System.out.println(urlImage);
                        Pokemon pokemon = new Pokemon(name, urlImage);
                        pokeList.add(pokemon);
                    }

                    i++;
                } else {
                    isNotEmpty = false;
                }
            } catch (IOException e) {
                //throw new RuntimeException(e);
                isNotEmpty = false;
                System.out.println("NOOOOOOOOOOOOOO");
            }
        }


        System.out.println("Pokemon size : " + pokeList.size());
        for (Pokemon pokemon : pokeList) {
            System.out.println(pokemon.getName());
        }

    }

    private static List<Player> parseResults(HtmlPage htmlPage) {
        return null;
    }
}