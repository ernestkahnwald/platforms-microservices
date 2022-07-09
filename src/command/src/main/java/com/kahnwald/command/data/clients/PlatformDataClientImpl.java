package com.kahnwald.command.data.clients;

import com.kahnwald.command.*;
import com.kahnwald.command.domain.models.Platform;
import io.quarkus.grpc.GrpcClient;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class PlatformDataClientImpl {

    @Inject
    @GrpcClient("platforms")
    GrpcPlatform platformsClient;

    public Uni<List<Platform>> getAllPlatforms() {
        return platformsClient
            .getAllPlatforms(PlatformProto.GetAllRequest.newBuilder().build())
            .onItem()
            .transform(platformResponse -> platformResponse
                .getPlatformList()
                .stream()
                .map(this::transformPlatform)
                .collect(Collectors.toList())
            );
    }

    private Platform transformPlatform(PlatformProto.GrpcPlatformModel platformModel) {
        var platform = new Platform();
        platform.setExternalId(platformModel.getPlatformId());
        platform.setName(platformModel.getName());
        return platform;
    }
}
