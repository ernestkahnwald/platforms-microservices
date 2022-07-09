package com.kahnwald.command.data.repository;

import com.kahnwald.command.data.dto.CommandCreateDTO;
import com.kahnwald.command.domain.models.Command;
import com.kahnwald.command.domain.models.Platform;
import com.kahnwald.command.domain.repository.CommandRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class CommandRepositoryImpl implements CommandRepository {

    @Override
    public List<Command> getForPlatform(Long platformId) {
        return list("platform_id = ?1", platformId);
    }

    @Override
    public List<Command> getForExternalPlatform(Long externalPlatformId) {
//        TODO: external
        return list("platform_id = ?1", externalPlatformId);
    }

    @Override
    public Command findForPlatform(Long platformId, Long commandId) {
        return find("id = ?1 and platform_id = ?2", commandId, platformId).firstResult();
    }

    @Override
    public Command findForExternalPlatform(Long externalPlatformId, Long commandId) {
//        TODO: external platform id
        return find("id = ?1", commandId).firstResult();
    }

    //    TODO: How to use just an ID?
    @Override
    public Command createForPlatform(Platform platform, Command command) {
        command.setPlatform(platform);
        persist(command);
        return command;
    }
}
