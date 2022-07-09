package com.kahnwald.command.domain.repository;

import com.kahnwald.command.data.dto.CommandCreateDTO;
import com.kahnwald.command.domain.models.Command;
import com.kahnwald.command.domain.models.Platform;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import java.util.List;

public interface CommandRepository extends PanacheRepository<Command> {

    List<Command> getForPlatform(Long platformId);
    List<Command> getForExternalPlatform(Long externalPlatformId);
    Command findForPlatform(Long platformId, Long commandId);
    Command findForExternalPlatform(Long externalPlatformId, Long commandId);
    Command createForPlatform(Platform platform, Command dto);
}
