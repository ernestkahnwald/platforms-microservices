package com.kahnwald.command.data.repository;

import com.kahnwald.command.domain.models.Platform;
import com.kahnwald.command.domain.repository.PlatformRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PlatformRepositoryImpl implements PlatformRepository {

    @Override
    public Platform findByExternalId(Long externalId) {
        return find("externalId = ?1", externalId).firstResult();
    }

    @Override
    public boolean externalExists(Long externalId) {
        return findByExternalId(externalId) != null;
    }
}
