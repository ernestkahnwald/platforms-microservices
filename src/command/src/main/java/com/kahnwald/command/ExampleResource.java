package com.kahnwald.command;

import com.kahnwald.command.data.CommandInitializeBean;
import com.kahnwald.command.data.clients.PlatformDataClientImpl;
import com.kahnwald.command.data.dto.PlatformReadDTO;
import com.kahnwald.command.domain.models.Platform;
import io.smallrye.mutiny.Uni;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Path("/c/test")
public class ExampleResource {

    private static final Logger LOGGER = Logger.getLogger(CommandInitializeBean.class.getName());

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<List<PlatformReadDTO>> gTest() {
        LOGGER.info("----> new grpc message from platform");

        return new PlatformDataClientImpl()
            .getAllPlatforms()
            .onItem()
            .transform(platforms -> platforms
                .stream()
                .map(platform -> {
                    var dto = new PlatformReadDTO();
                    dto.setId(platform.getId());
                    dto.setName(platform.getName());
                    return dto;
                })
                .collect(Collectors.toList())
            );
    }
}
