package com.kahnwald.platform.data.clients.async;

import com.kahnwald.platform.data.PlatformInitializeBean;
import com.kahnwald.platform.data.clients.PlatformPublisher;
import com.kahnwald.platform.data.dto.PlatformPublishedDTO;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import javax.enterprise.context.ApplicationScoped;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@ApplicationScoped
public class PlatformPublisherBus implements PlatformPublisher {

    private static final Logger LOGGER = Logger.getLogger(PlatformInitializeBean.class.getName());

    @Channel("platform-requests")
    Emitter<PlatformPublishedDTO> platformPublishEmitter;

    @Override
    public void publishNewPlatform(PlatformPublishedDTO dto) {
        LOGGER.info("Platform -----> Sending");
        platformPublishEmitter.send(dto);
    }
}
