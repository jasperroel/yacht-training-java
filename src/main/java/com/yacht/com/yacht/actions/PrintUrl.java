package com.yacht.com.yacht.actions;

import com.gargoylesoftware.htmlunit.WebResponse;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.salesforce.webdev.sitecrawler.SiteCrawlerAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Set;

public class PrintUrl implements SiteCrawlerAction {

    private final Logger log = LoggerFactory.getLogger(getClass());

    public void takeAction(HtmlPage htmlPage, List<String> list, Set<String> set) {
        log.info("Got page with URI: {}", htmlPage);
    }

    public void handleError(int i, String s, Set<String> set, WebResponse webResponse) {
        // Noop
    }
}