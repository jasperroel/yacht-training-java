package com.yacht.com.yacht.actions;

import com.gargoylesoftware.htmlunit.WebResponse;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.salesforce.webdev.sitecrawler.SiteCrawlerAction;
import com.yacht.com.yacht.model.Vacature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;


public class FindVacatures implements SiteCrawlerAction {
    private final AtomicInteger urlCounter = new AtomicInteger();
    private final AtomicInteger errorCounter = new AtomicInteger();

    private Logger log = LoggerFactory.getLogger(getClass());
    private final Queue<Vacature> vacatures = new ConcurrentLinkedQueue<>();


    public void takeAction(HtmlPage htmlPage, List<String> list, Set<String> set) {
        log.info("[{}]Got page with URI: {}", urlCounter.getAndIncrement(), htmlPage);

        Vacature vacature = new Vacature();

        HtmlElement id = htmlPage.getFirstByXPath("//button[@data-save-job]");
        if (Objects.nonNull(id)) {
            String jobId = id.getAttribute("data-save-job");
            log.info(" - id: {}", jobId);
            vacature.id = jobId;
        } else {
            // Not a valid page
            log.info("Current page ({}) is NOT a vacature site, skipping it!", htmlPage);
            return;
        }

        // Find title
        HtmlElement title = htmlPage.getFirstByXPath("//article//h1");
        if (Objects.nonNull(title)) {
            log.info(" - Found title: {}", title.getTextContent());
            vacature.title = title.getTextContent();
        }
        HtmlElement description = htmlPage.getFirstByXPath("//article//div[@class=\"description\"]");
        if (Objects.nonNull(description)) {
            log.info(" - Description: {}", description.getTextContent());
            vacature.description = description.getTextContent();
        }

        HtmlElement vakgebied = htmlPage.getFirstByXPath("//section[contains(@class,\"my-profession-banner\")]/a");
        if (Objects.nonNull(vakgebied)) {
            log.info(" - vakgebied: {}", vakgebied.getTextContent().trim());
            vacature.vakgebied = vakgebied.getTextContent().trim();
        }

        // Final checks (do we have all the information we need?
        if (null != vacature.title && null != vacature.description && null != vacature.vakgebied) {
            vacatures.add(vacature);
        }
    }

    /**
     * This prints the error, but it doesn't actually deal with any of it.
     *
     * @param errorCode
     * @param url
     * @param set
     * @param webResponse
     */
    public void handleError(int errorCode, String url, Set<String> set, WebResponse webResponse) {
        int counter = errorCounter.getAndIncrement();
        log.error("[{}] Got a error code [{}] for URL: {}", counter, errorCode, url);
        for (String item : set) {
            log.error("[{}] - Got item: {}", counter, item);
        }
        log.error("[{}] - Got response Status Message: {}", counter, webResponse.getStatusMessage());
        log.error("[{}] - Got response: {}", counter, webResponse.getContentAsString());
    }

    public Queue<Vacature> getVacatures() {
        return this.vacatures;
    }

}
