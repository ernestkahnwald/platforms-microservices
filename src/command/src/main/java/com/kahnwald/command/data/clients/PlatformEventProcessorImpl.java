package com.kahnwald.command.data.clients;

import com.kahnwald.command.data.CommandInitializeBean;
import com.kahnwald.command.data.dto.GenericEventDTO;
import com.kahnwald.command.data.dto.PlatformReceiveDTO;
import com.kahnwald.command.domain.models.Platform;
import com.kahnwald.command.domain.repository.PlatformRepository;
import io.smallrye.common.annotation.Blocking;
import io.vertx.core.json.JsonObject;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Message;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Objects;
import java.util.concurrent.CompletionStage;
import java.util.logging.Logger;

@ApplicationScoped
//public class PlatformEventProcessorImpl implements PlatformEventProcessor {
public class PlatformEventProcessorImpl {

    private static final Logger LOGGER = Logger.getLogger(CommandInitializeBean.class.getName());

    @Inject
    PlatformRepository platformRepository;

    public void determineEvent(GenericEventDTO dto) {
        if (EventType.PLATFORM_PUBLISHED.getEventName().equals(dto.getEvent())) {
            this.processAddPlatform((PlatformReceiveDTO) dto);
        }
    }

    @Incoming("platforms")
    @Blocking
    @Transactional
    public void addPlatform(JsonObject data) {
        LOGGER.info(String.format("------> %s", data.toString()));

        String event = data.getString("event");

        if (EventType.PLATFORM_PUBLISHED.getEventName().equals(event)) {
            PlatformReceiveDTO dto = new PlatformReceiveDTO();
            dto.setId(data.getLong("id"));
            dto.setName(data.getString("name"));
            this.processAddPlatform(dto);
        }

        LOGGER.info("---> add-platform event");
    }

    private void processAddPlatform(PlatformReceiveDTO dto) {
        LOGGER.info(String.format("------> %s", dto.toString()));

        if (platformRepository.externalExists(dto.getId())) {
            LOGGER.info("---> has already added");
            return;
        }

        Platform platform = new Platform();
        platform.setName(dto.getName());
        platform.setExternalId(dto.getId());
        LOGGER.info(String.format("------> %s", platform.toString()));
        platformRepository.persist(platform);

        LOGGER.info("---> platform is added");
    }
}
