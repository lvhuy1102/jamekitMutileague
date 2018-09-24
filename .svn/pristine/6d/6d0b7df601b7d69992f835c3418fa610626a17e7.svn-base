package com.hcpt.multileagues.xml.utils;

import com.hcpt.multileagues.configs.XMLConfigs;
import com.hcpt.multileagues.objects.FeedsObj;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;

import java.util.ArrayList;

public class XMLParser {

    private static final String TAG = "XMLParser";

    public static ArrayList<FeedsObj> parserNews(String xml) {
        ArrayList<FeedsObj> arrNews = new ArrayList<>();
        try {
            Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
            String title = "", description = "", pubDate = "", link = "", img = "";
            for (Element e : doc.select(XMLConfigs.XML_TAG_ITEM)) {
                Document childDoc = Jsoup.parse(e.toString(), "",
                        Parser.xmlParser());

                Element titleE = childDoc.select(XMLConfigs.XML_TAG_TITLE).first();
                title = htmlToText(titleE.text());
                Element descriptionE = childDoc.select(XMLConfigs.XML_TAG_DESCRIPTION).first();
                description = htmlToText(descriptionE.text());
                /*description*/
                Element pubDateE = childDoc.select(XMLConfigs.XML_TAG_PUBDATE)
                        .first();
                pubDate = htmlToText(pubDateE.text());
                Element linkE = childDoc.select(XMLConfigs.XML_TAG_LINK).first();
                link = htmlToText(linkE.text());

                try {
                    Element imageE = childDoc.select(XMLConfigs.XML_TAG_ENCLOSURE)
                            .first();
                    img = imageE.attr(XMLConfigs.XML_ATTR_URL);

                } catch (NullPointerException ex) {
                    ex.printStackTrace();
                }

                FeedsObj news = new FeedsObj(title, description, img, link, pubDate);
                arrNews.add(news);
            }
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }
        return arrNews;
    }

    public static String htmlToText(String html) {
        return Jsoup.parse(html).text();
    }
}
