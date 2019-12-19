package com.theodo.radar;

import com.theodo.radar.model.Hit;
import com.theodo.radar.model.Ufo;
import io.smallrye.reactive.messaging.annotations.Channel;
import io.smallrye.reactive.messaging.annotations.Emitter;
import io.vertx.core.json.Json;
import org.eclipse.microprofile.reactive.messaging.Incoming;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.concurrent.atomic.AtomicBoolean;

@ApplicationScoped
public class UfoDetection {

    @Inject
    RadarUpdater allRadars;


    @Inject
    @Channel("hits")
    Emitter<String> hitsChannel;

    @Incoming("spying-ufos")
    public void checkingUfo(String ufo) {
        Ufo detectedUfo = Json.decodeValue(ufo, Ufo.class);

        AtomicBoolean detected = new AtomicBoolean(false);
        allRadars.getAllRadars().values().forEach(radar -> {
            double distance = distance(detectedUfo.getCurrent().getLat(), radar.position.getLat(),
                    detectedUfo.getCurrent().getLng(), radar.position.getLng());

            if(distance < radar.distance){
                detected.set(true);
            }
        });

        if(detected.get()){
            hitsChannel.send(Json.encode(new Hit(detectedUfo.getName(), true)));
        } else {
            hitsChannel.send(Json.encode(new Hit(detectedUfo.getName(), false)));
        }
    }

    /**
     * Calculate distance between two points in latitude and longitude.
     *
     * lat1, lon1 Start point lat2, lon2 End point
     * @return Distance in Meters
     */
    private static double distance(double lat1, double lat2, double lon1, double lon2) {

        final int R = 6371; // Radius of the earth

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c * 1000;
    }
}
