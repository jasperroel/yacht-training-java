package com.yacht;

import com.salesforce.webdev.sitecrawler.SiteCrawler;
import com.salesforce.webdev.sitecrawler.SiteCrawlerAction;

import java.util.LinkedList;
import java.util.List;

public class BasicCrawler {

    public static void main(String[] args) {
        basic();
    }
    private static void basic() {
        List<? extends SiteCrawlerAction> siteCrawlerActions = new LinkedList<>();
        SiteCrawler siteCrawler = new SiteCrawler(null, "https://www.ziggo.nl", siteCrawlerActions);
        siteCrawler.getAllowedSuffixes().add("");
        siteCrawler.navigate();
    }
}
