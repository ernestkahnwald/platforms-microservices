package com.kahnwald.command.data;

import com.kahnwald.command.domain.models.Command;
import com.kahnwald.command.domain.models.Platform;
import com.kahnwald.command.domain.repository.CommandRepository;
import com.kahnwald.command.domain.repository.PlatformRepository;
import io.quarkus.runtime.StartupEvent;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.logging.Logger;

@ApplicationScoped
public class CommandInitializeBean {

    @Inject
    public CommandRepository commandRepo;
    @Inject
    PlatformRepository platformRepo;

    private static final Logger LOGGER = Logger.getLogger(CommandInitializeBean.class.getName());

    @Transactional
    void onStart(@Observes StartupEvent event) {
//        TODO: initialize data based on platform service via gRPC.

        Platform platform = new Platform();
        platform.setName("Plat");
        platform.setExternalId(1L);
        platformRepo.persist(platform);

        Command command = new Command();
        command.setHowTo("how");
        command.setCommandLine("cd");
        command.setPlatform(platform);
        commandRepo.persist(command);

        List<Platform> plats = platformRepo.listAll();
        LOGGER.info(String.valueOf(plats.size()));
        plats.forEach(plat -> {
            LOGGER.info(plat.toString());
        });

        List<Command> comms = commandRepo.listAll();
        LOGGER.info(String.valueOf(comms.size()));
        comms.forEach(comm -> {
            LOGGER.info(comm.toString());
        });
    }
}
