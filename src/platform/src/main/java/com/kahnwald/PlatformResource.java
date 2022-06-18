package com.kahnwald;

import com.kahnwald.platform.data.dto.PlatformCreateDTO;
import com.kahnwald.platform.data.dto.PlatformReadDTO;
import com.kahnwald.platform.domain.model.Platform;
import com.kahnwald.platform.domain.repository.PlatformRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@Path("/platforms")
public class PlatformResource {

    @Inject PlatformRepository repo;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response createItem(PlatformCreateDTO dto) {
        Platform platform = new Platform(dto.getName(), dto.getPublisher(), dto.getCost());

        repo.persist(platform);
        if (repo.isPersistent(platform)) {
            return Response.created(URI.create("/platforms")).build();
        }

        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getItem(@PathParam("id") Long id) {
        Platform platform = repo.findById(id);
        if (platform == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response
            .ok(new PlatformReadDTO(
                platform.getId(),
                platform.getName(),
                platform.getPublisher(),
                platform.getCost()
            ))
            .build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getList() {
        List<PlatformReadDTO> platforms = repo
            .listAll()
            .stream()
            .map(platform -> new PlatformReadDTO(
                platform.getId(),
                platform.getName(),
                platform.getPublisher(),
                platform.getCost()
            ))
            .collect(Collectors.toList());
        return Response.ok(platforms).build();
    }
}
