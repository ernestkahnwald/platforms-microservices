package com.kahnwald.platform.data.clients;

import com.kahnwald.platform.data.dto.PlatformReadDTO;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("/platforms")
@RegisterRestClient(configKey = "commands-api")
public interface CommandClient {

    @POST
    // TODO: reactive CompletionStage<Void>
    void createPlatform(PlatformReadDTO platform);

}
