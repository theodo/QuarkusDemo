package com.theodo.radar;

import com.theodo.radar.model.Radar;
import io.quarkus.runtime.StartupEvent;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class AtStartupRadarLoading {
    private static final Logger LOGGER = LoggerFactory.getLogger("AtStartupRadarLoading");

    @Inject
    @RestClient
    RadarLoaderService radarLoaderService;

    @Inject
    RadarUpdater radarUpdater;

    void onStart(@Observes StartupEvent ev) {
        try {
            Map<String, Radar> allRadars = radarLoaderService.getAllRadars();
            allRadars.values().forEach(radar -> radarUpdater.add(radar));
            LOGGER.info("Loaded {} radars at startup", allRadars.size());
        } catch (Exception e) {
            LOGGER.error("Cannot read Radars at startup", e);
        }
    }

}
