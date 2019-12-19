package com.theodo.ufo.generator;

import com.theodo.ufo.generator.model.Ufo;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collection;

@Path("/ufos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UfoResource {

    @Inject
    UfoGenerator allUfos;

    @POST
    public Collection<Ufo> add(Ufo ufo) {
        return allUfos.add(ufo);
    }

    @GET
    public Collection<Ufo> get() {
        return allUfos.getAllUfos();
    }

    @POST
    @Path("/clean")
    public void clean() {
        allUfos.clean();
    }

}