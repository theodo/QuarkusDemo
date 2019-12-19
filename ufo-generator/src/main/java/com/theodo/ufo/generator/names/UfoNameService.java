package com.theodo.ufo.generator.names;

import org.eclipse.microprofile.rest.client.annotation.ClientHeaderParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;


@Path("/v2")
@RegisterRestClient
// UfoName REST Client that calls /pokemon API
// NB: needed to set User-Agent (without this header set, call was KO in 'native mode')
public interface UfoNameService {
    @GET
    @Path("/pokemon/{id}")
    @Produces("application/json")
    @ClientHeaderParam(name="User-Agent", value="curl/7.66.0")
    UfoName getUfoName(@PathParam("id") int id);
}
