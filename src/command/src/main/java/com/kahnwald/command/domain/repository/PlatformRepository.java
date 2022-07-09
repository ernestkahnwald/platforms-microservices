package com.kahnwald.command.domain.repository;

import com.kahnwald.command.domain.models.Platform;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

public interface PlatformRepository extends PanacheRepository<Platform> {

    Platform findByExternalId(Long externalId);
    boolean externalExists(Long externalId);
}
