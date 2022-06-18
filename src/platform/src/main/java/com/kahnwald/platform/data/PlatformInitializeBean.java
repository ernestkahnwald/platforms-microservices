package com.kahnwald.platform.data;

import com.kahnwald.platform.domain.model.Platform;
import com.kahnwald.platform.domain.repository.PlatformRepository;
import io.quarkus.runtime.StartupEvent;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.logging.Logger;

@ApplicationScoped
public class PlatformInitializeBean {

    @Inject PlatformRepository repo;
    private static final Logger LOGGER = Logger.getLogger(PlatformInitializeBean.class.getName());

    @Transactional
    void onStart(@Observes StartupEvent event) {
        List<Platform> platforms = List.of(
            new Platform("JVM", "Oracle", "Free"),
            new Platform("PostgreSQL", "PostgreSQL", "Free"),
            new Platform("Kubernetes", "Cloud Native Computing Foundation", "Free")
        );

        platforms.forEach(platform -> {
            repo.persist(platform);
            if (repo.isPersistent(platform)) {
                LOGGER.info("Platform added: " + platform.getName());
            }
        });
    }
}
