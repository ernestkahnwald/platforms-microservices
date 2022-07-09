package com.kahnwald.command.domain.repository;

import com.kahnwald.command.domain.models.Command;
import com.kahnwald.command.domain.models.Platform;

import java.util.Set;

public interface NormalRepository {
    // TODO: White my own implementation

    boolean saveChanges();

    Set<Platform> getAllPlatforms();
    void createPlatform();
    boolean platformExists();

    Set<Command> getCommandsForPlatform(Long platformId);
    void getCommand(Long platformId, Long commandId);
    boolean createCommand(Long platformId, Command command);
}
