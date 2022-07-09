package com.kahnwald.command;

import com.kahnwald.command.data.dto.CommandCreateDTO;
import com.kahnwald.command.data.dto.CommandReadDTO;
import com.kahnwald.command.domain.models.Command;
import com.kahnwald.command.domain.models.Platform;
import com.kahnwald.command.domain.repository.CommandRepository;
import com.kahnwald.command.domain.repository.PlatformRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@Path("/platforms/{platformId}/commands")
public class CommandsResource {

    @Inject
    PlatformRepository platformRepository;
    @Inject
    CommandRepository commandRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCommands(@PathParam("platformId") Long externalPlatformId) {
        List<Command> commands = commandRepository.getForExternalPlatform(externalPlatformId);

        if (commands.size() == 0) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        List<CommandReadDTO> dtos = commands
            .stream()
            .map(command -> {
                CommandReadDTO dto = new CommandReadDTO();
                dto.setId(command.getId());
                dto.setHowTo(command.getHowTo());
                dto.setCommandLine(command.getCommandLine());
                return dto;
            })
            .collect(Collectors.toList());

        return Response.ok(dtos).build();
    }

    @GET
    @Path("/{commandId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCommand(
        @PathParam("platformId") Long externalPlatformId,
        @PathParam("commandId") Long commandId
    ) {
        Command command = commandRepository.findForExternalPlatform(externalPlatformId, commandId);

        if (command == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        CommandReadDTO dto = new CommandReadDTO();
        dto.setId(command.getId());
        dto.setHowTo(command.getHowTo());
        dto.setCommandLine(command.getCommandLine());

        return Response.ok(dto).build();
    }

    @POST
//    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response createCommand(
        @PathParam("platformId") Long externalPlatformId,
        CommandCreateDTO commandDTO
    ) {
        Platform platform = platformRepository.findByExternalId(externalPlatformId);

        if (platform == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        Command command = new Command();
        command.setHowTo(commandDTO.getHowTo());
        command.setCommandLine(commandDTO.getCommandLine());
        commandRepository.createForPlatform(platform, command);

        CommandReadDTO dto = new CommandReadDTO();
        dto.setId(command.getId());
        dto.setHowTo(command.getHowTo());
        dto.setCommandLine(command.getCommandLine());

        return Response.ok(dto).build();
    }
}
