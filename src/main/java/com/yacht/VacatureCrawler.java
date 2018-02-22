package com.yacht;

import com.salesforce.webdev.sitecrawler.SiteCrawler;
import com.salesforce.webdev.sitecrawler.SiteCrawlerAction;
import com.yacht.com.yacht.actions.PrintUrl;
import com.yacht.com.yacht.actions.FindVacatures;
import com.yacht.com.yacht.com.yacht.creators.SiteCreator;
import com.yacht.com.yacht.model.Vacature;

import java.util.*;

/**
 * <p>Main entrypoint for our Yacht Vacature crawler.</p>
 *
 * <p>There are a few ideas presented here, building up from
 * "basic" (which just shows the basic usage of the SiteCrawler), up to
 * "findVacatures" and "createSite", which parses a portion of the Yacht Vacature site
 * and creates a new "Google search result page" from it.</p>
 */
public class VacatureCrawler {

    public static void main(String[] args) {
//        basic();
//        basicWorking();
//        runningWithOneAction();

        VacatureCrawler vacatureCrawler = new VacatureCrawler();
        Queue<Vacature> vacatures = vacatureCrawler.findVacatures();
        vacatureCrawler.createSite(vacatures);
    }

    public static void basic() {
        List<? extends SiteCrawlerAction> siteCrawlerActions = new LinkedList<>();
        SiteCrawler siteCrawler = new SiteCrawler(null, "https://www.yacht.nl", siteCrawlerActions);
        siteCrawler.navigate();
    }

    public static void basicWorking() {
        List<? extends SiteCrawlerAction> siteCrawlerActions = new LinkedList<>();
        SiteCrawler siteCrawler = new SiteCrawler(null, "https://www.yacht.nl", siteCrawlerActions);
        siteCrawler.getAllowedSuffixes().add("");
        siteCrawler.setShortCircuitAfter(100);
        siteCrawler.navigate();
    }

    public static void runningWithOneAction() {
        List<SiteCrawlerAction> siteCrawlerActions = new LinkedList<>();
        siteCrawlerActions.add(new PrintUrl());

        SiteCrawler siteCrawler = new SiteCrawler(null, "https://www.yacht.nl", siteCrawlerActions);
        siteCrawler.getAllowedSuffixes().add("");
        siteCrawler.setShortCircuitAfter(100);
        siteCrawler.navigate();
    }

    /**
     * Uses the SiteCrawler library to go through the Yacht Vacactures.
     * The "PrintVacatures"
     * @return
     */
    private Queue<Vacature> findVacatures() {
        List<SiteCrawlerAction> siteCrawlerActions = new LinkedList<>();
        FindVacatures actionFindVacatures = new FindVacatures();
        siteCrawlerActions.add(actionFindVacatures);

        SiteCrawler siteCrawler = new SiteCrawler(null, "https://www.yacht.nl", siteCrawlerActions);

        // To stop some of the "ssl issues" that yacht.nl throws
        System.setProperty("https.protocols", "TLSv1,TLSv1.1,TLSv1.2");

        // Yacht doesn't use standard file extension, so we add the "blank" one
        siteCrawler.getAllowedSuffixes().add("");
        // After 300 page in rapid succession, the site starts hating us. Limit at a max of 200
        siteCrawler.setShortCircuitAfter(20);

        Collection<String> startUrls = Collections.singletonList("/vacatures");
        siteCrawler.setIncludePath(startUrls);
        Collection<String> allowedPaths = Collections.singletonList("/vacatures");
        siteCrawler.setAllowed(allowedPaths);
        Collection<String> blockedPaths = Collections.singletonList("/vacatures/verlopen-vacature");
        siteCrawler.setBlocked(blockedPaths);
        siteCrawler.getUrlCleaner().getOptions().addAllowedParameters("pagina");

        siteCrawler.navigate();
        return actionFindVacatures.getVacatures();
    }

    private void createSite(Queue<Vacature> vacatures) {
        SiteCreator siteCreator = new SiteCreator(vacatures);
        siteCreator.createSite();

    }

}
