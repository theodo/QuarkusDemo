package com.theodo.radar;

import com.theodo.radar.model.Radar;
import io.vertx.core.json.Json;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
public class RadarUpdater {
    private static final Logger LOGGER = LoggerFactory.getLogger("RadarUpdater");

    private final Map<String, Radar> allRadars = new HashMap<>();

    Map<String, Radar> getAllRadars() {
        return Collections.unmodifiableMap(allRadars);
    }

    @Incoming("radars")
    public void newRadarCreated(String radar) {
        Radar newRadar = Json.decodeValue(radar, Radar.class);
        LOGGER.info("New Radar '{}' has been created", newRadar.getName());
        allRadars.put(newRadar.name, newRadar);
    }

    void add(Radar radar) {
        allRadars.put(radar.name, radar);
    }
}
