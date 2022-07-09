package com.kahnwald.platform.data.clients.sync;

import com.kahnwald.platform.data.clients.CommandClient;
import com.kahnwald.platform.data.dto.PlatformReadDTO;
import com.kahnwald.platform.domain.services.CommandSync;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import java.util.concurrent.CompletionStage;

@ApplicationScoped
public class HttpCommandSync implements CommandSync {

    @RestClient CommandClient client;

    public HttpCommandSync() {
    }

    @Override
    public void sendPlatformToCommand(PlatformReadDTO platform) {
        client.createPlatform(new PlatformReadDTO(
            1L,
            "N",
            "P",
            "C"
        ));
    }
}
