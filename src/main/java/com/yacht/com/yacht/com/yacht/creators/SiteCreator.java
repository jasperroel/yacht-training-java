package com.yacht.com.yacht.com.yacht.creators;

import com.yacht.com.yacht.model.Vacature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Queue;

public class SiteCreator {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final Queue<Vacature> vacatures;

    public SiteCreator(Queue<Vacature> vacatures) {
        this.vacatures = vacatures;
    }


    public void createSite() {
        log.info("Creating vacature site...");

        try {
            String indexTemplate = new String(Files.readAllBytes(Paths.get("src/main/resources/index.template")));
            String vacatureTemplate = new String(Files.readAllBytes(Paths.get("src/main/resources/vacature.template")));
            log.info(indexTemplate);
            for (Vacature vacature: vacatures) {
                indexTemplate = indexTemplate.replace("COUNT", ""+vacatures.size());
                indexTemplate += "<a href=\"" + vacature.id+".html\">" + vacature.title + "</a><br />";
                String myVacature = vacatureTemplate
                        .replace("TITLE", vacature.title)
                        .replace("VAKGEBIED", vacature.vakgebied)
                        .replace("DESCRIPTION", vacature.description);
                Files.write(Paths.get("src/main/resources/site/"+vacature.id+".html"), myVacature.getBytes());
            }
            Files.write(Paths.get("src/main/resources/site/index.html"), indexTemplate.getBytes());
            log.info("... Wrote the whole site! Go check it out (site/index.html)");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
