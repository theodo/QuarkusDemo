package com.theodo.ufo.sse;

import io.reactivex.Flowable;
import io.smallrye.reactive.messaging.annotations.Channel;
import org.reactivestreams.Publisher;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Path("/ufos")
public class UfoResource {

    @Inject
    @Channel("internal-broadcast")
    Flowable<String> jsonUfos;

    @GET
    @Path("/stream")
    @Produces(MediaType.SERVER_SENT_EVENTS)
    public Publisher<List<String>> stream() {
        return jsonUfos.buffer(125, TimeUnit.MILLISECONDS);
    }
}