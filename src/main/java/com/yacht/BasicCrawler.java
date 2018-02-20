package com.yacht;

import com.salesforce.webdev.sitecrawler.SiteCrawler;
import com.salesforce.webdev.sitecrawler.SiteCrawlerAction;

import java.util.List;

public class BasicCrawler {

    public static void main(String[] args) {
        basic();
    }
    public static void basic() {
        List<? extends SiteCrawlerAction> siteCrawlerActions = null;
        SiteCrawler siteCrawler = new SiteCrawler(null, "https://www.ziggo.nl", siteCrawlerActions);
        siteCrawler.getAllowedSuffixes().add("");
        siteCrawler.navigate();
    }
}
