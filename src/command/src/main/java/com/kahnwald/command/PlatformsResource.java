package com.kahnwald.command;

import com.kahnwald.command.data.dto.PlatformReadDTO;
import com.kahnwald.command.domain.models.Command;
import com.kahnwald.command.domain.models.Platform;
import com.kahnwald.command.domain.repository.CommandRepository;
import com.kahnwald.command.domain.repository.PlatformRepository;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Path("/c/platforms")
public class PlatformsResource {

    @Inject
    public CommandRepository commandRepo;
    @Inject
    public PlatformRepository platformRepo;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
//    public Response createPlatform(PlatformCreateDTO platformData) {
    public Response createPlatform() {
        Logger logger = Logger.getLogger(PlatformsResource.class.getName());
        logger.info("----> external created");
        return Response.ok("wanna add platform?").build();
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPlatforms() {
        List<PlatformReadDTO> dtos = platformRepo
            .listAll()
            .stream()
            .map(platform -> {
                PlatformReadDTO dto = new PlatformReadDTO();
                dto.setId(platform.getId());
                dto.setName(platform.getName());
                return dto;
            })
            .collect(Collectors.toList());

        Map<String, Object> res = new HashMap<>();

        res.put("count", dtos.size());
        res.put("items", dtos);

        return Response.ok(res).build();
    }
}
