package com.smapp.NewsReader;

import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created with IntelliJ IDEA.
 * User: mrwho
 * Date: 6/10/13
 * Time: 12:47 PM
 * To change this template use File | Settings | File Templates.
 */
public class RSSHandler extends DefaultHandler {
    static RSSItem item = null;
    static RSSFeed feeds = null;
    List<RSSItem> itemsList = new ArrayList<RSSItem>();
    static List<RSSFeed> feedsList = new ArrayList<RSSFeed>();
    boolean inChannel = false;
    boolean inItem = false;
    boolean inTitle = false;
    boolean inDesc = false;
    boolean inLink = false;
    boolean inPubdate = false;
    boolean inGuid = false;
    boolean inLanguage = false;
    String temp;

    /**
     * To be called inside Async Task
     *
     * @param url
     * description: rss feed url
     * @return
     * description: List of rss feed
     */
    public List<RSSFeed> processWithParsing(String url) throws ParserConfigurationException, SAXException, IOException {
        Log.d("TESTING FOR FIRST POST", url);
        SAXParserFactory spf = SAXParserFactory.newInstance();
        SAXParser sp = spf.newSAXParser();
        XMLReader xmlIn = sp.getXMLReader();
        xmlIn.setContentHandler(this);
        URL xmlUrl = new URL(url);
        URLConnection xmlConn = xmlUrl.openConnection();
        xmlConn.setRequestProperty("User-agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_3) AppleWebKit/534.55.3 (KHTML, like Gecko) Version/5.1.3 Safari/534.53.10");
        InputStreamReader xmlStream = new InputStreamReader(xmlConn.getInputStream());
        BufferedReader xmlBuf = new BufferedReader(xmlStream);
        xmlIn.parse(new InputSource(xmlBuf));
        Log.d("IN ::", feedsList.toString());
        return feedsList;
    }


    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        temp = "";

        Log.d("TAGS: ", qName);
        if (qName.equalsIgnoreCase("channel")) {
            feeds = new RSSFeed();
            inChannel = true;
            inItem = false;
        } else if (qName.equalsIgnoreCase("item")) {

            item = new RSSItem();
            inItem = true;
            inChannel = false;
        } else if (qName.equalsIgnoreCase("title")) {
            inTitle = true;
        } else if (qName.equalsIgnoreCase("link")) {
            inLink = true;
        } else if (qName.equalsIgnoreCase("description")) {
            inDesc = true;
        } else if (qName.equalsIgnoreCase("pubDate")) {
            inPubdate = true;
        } else if (qName.equalsIgnoreCase("language")) {
            inLanguage = true;
        } else if (qName.equalsIgnoreCase("guid")) {
            inGuid = true;
        }

    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (inChannel) {
            if (inTitle) {
                feeds.setTitle(temp);
                inTitle = false;
            } else if (inLink) {
                feeds.setLink(temp);
                inLink = false;
            } else if (inDesc) {
                Document doc = Jsoup.parseBodyFragment(temp);
                Element body = doc.body();
                feeds.setDescription(body.text());
                inDesc = false;
            } else if (inLanguage) {
                feeds.setLanguage(temp);
                inLanguage = false;
            }

        } else if (inItem) {
            if (inTitle) {
                item.setTitle(temp);
                inTitle = false;
            } else if (inLink) {
                item.setLink(temp);
                inLink = false;
            } else if (inDesc) {
                Document doc = Jsoup.parseBodyFragment(temp);
                Element body = doc.body();
                item.setDescription(body.text());
                inDesc = false;
            } else if (inPubdate) {
                item.setPubdate(temp);
                inPubdate = false;
            } else if (inGuid) {
                item.setGuid(temp);
                inGuid = false;
            }
        }
        if (qName.equalsIgnoreCase("channel")) {
            if (feeds != null) {
                feeds.setItems(itemsList);
                feedsList.add(feeds);
                itemsList = new ArrayList<RSSItem>();
            }

        }
        if (qName.equalsIgnoreCase("item")) {
            if (item != null)
                itemsList.add(item);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        temp = temp + new String(ch).substring(start, start + length).trim();
    }
}
