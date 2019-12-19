package com.theodo.ufo.generator;

import com.theodo.ufo.generator.model.Position;
import com.theodo.ufo.generator.model.Ufo;
import com.theodo.ufo.generator.names.UfoName;
import com.theodo.ufo.generator.names.UfoNameService;
import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import io.vertx.core.json.Json;
import org.eclipse.microprofile.reactive.messaging.Outgoing;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.*;
import java.util.concurrent.TimeUnit;

@ApplicationScoped
public class UfoGenerator {

    private final Map<String, Ufo> allUfos = new HashMap<>();
    private final Set<String> detectedUfos = new HashSet<>();

    @Inject
    @RestClient
    UfoNameService ufoNameService;

    @Outgoing("generated-ufo")
    public Flowable<String> generate() {
        return Flowable.interval(50, TimeUnit.MILLISECONDS)
                .onBackpressureDrop() // If within the 50ms I am not able to compute all new positions, not a big deal...
                .flatMap(tick -> generateNewPositions());
    }

    private Flowable<String> generateNewPositions() {
        return Flowable.fromIterable(allUfos.values()).map(updateUfoStatus()).map(Json::encode);
    }


    Collection<Ufo> add(Ufo ufo) {
        Ufo runningUfo = initUfo(ufo);
        allUfos.put(runningUfo.getName(), runningUfo);
        return Collections.unmodifiableCollection(allUfos.values());
    }

    Collection<Ufo> getAllUfos() {
        return Collections.unmodifiableCollection(allUfos.values());
    }

    void clean() {
        allUfos.clear();
        detectedUfos.clear();
    }

    void ufoDetected(String ufoName) {
        detectedUfos.add(ufoName);
    }

    void ufoSafe(String ufoName) {
        detectedUfos.remove(ufoName);
    }

    private Ufo initUfo(Ufo ufo) {
        if (ufo.name == null) {
            int id = (int) (Math.random() * 400);
            UfoName ufoName = ufoNameService.getUfoName(id);
            ufo.name = ufoName.name;
        }

        if (ufo.start == null) {
            ufo.start = fakePosition();
        }

        if (ufo.destination == null) {
            ufo.destination = fakePosition();
        }

        ufo.current = new Position(ufo.start.getLat(), ufo.start.getLng());
        ufo.moving = true;
        return ufo;
    }

    private static Position fakePosition() {
        return new Position(
                48.86391723487638 + Math.random() * (48.90585217452334 - 48.86391723487638),
                2.197625877990731 + Math.random() * (2.2780492330932702 - 2.197625877990731));
    }

    private Function<Ufo, Ufo> updateUfoStatus() {
        return ufo -> {
            ufo.updatePosition();
            ufo.hit = detectedUfos.contains(ufo.getName());
            return ufo;
        };
    }


}
