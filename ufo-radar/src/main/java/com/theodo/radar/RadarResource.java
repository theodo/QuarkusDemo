package com.theodo.radar;

import com.theodo.radar.model.Radar;
import io.smallrye.reactive.messaging.annotations.Channel;
import io.smallrye.reactive.messaging.annotations.Emitter;
import io.vertx.core.json.Json;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Path("/radars")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RadarResource {

    private final Map<String, Radar> radars = new HashMap<>();

    @Inject
    @Channel("radars")
    Emitter<String> radarsChannel;

    @POST
    public Radar add(Radar radar) {
        radars.put(radar.name, radar);
        radarsChannel.send(Json.encode(radar));
        return radar;
    }

    @GET
    public Map<String, Radar> getAll(){ return Collections.unmodifiableMap(radars); }

}