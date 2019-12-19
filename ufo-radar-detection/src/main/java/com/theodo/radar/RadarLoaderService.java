package com.theodo.radar;

import com.theodo.radar.model.Radar;
import org.eclipse.microprofile.rest.client.annotation.ClientHeaderParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.Map;


@Path("/radars")
@RegisterRestClient
public interface RadarLoaderService {
    @GET
    @Produces("application/json")
    @ClientHeaderParam(name="User-Agent", value="curl/7.66.0")
    Map<String, Radar> getAllRadars();
}
