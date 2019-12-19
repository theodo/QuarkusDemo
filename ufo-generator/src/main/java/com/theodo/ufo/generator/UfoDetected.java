package com.theodo.ufo.generator;

import com.theodo.ufo.generator.model.Hit;
import io.vertx.core.json.Json;
import org.eclipse.microprofile.reactive.messaging.Incoming;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class UfoDetected {

    @Inject
    UfoGenerator allUfos;

    @Incoming("hits")
    public void newUfoDetected(String hit) {
        Hit touchedUfo = Json.decodeValue(hit, Hit.class);
        if(touchedUfo.isHit()) {
            allUfos.ufoDetected(touchedUfo.getUfoName());
        } else {
            allUfos.ufoSafe(touchedUfo.getUfoName());
        }
    }
}
