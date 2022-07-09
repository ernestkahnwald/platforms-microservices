package com.kahnwald.platform.data.clients.sync;

import com.kahnwald.platform.GrpcPlatform;
import com.kahnwald.platform.PlatformProto.PlatformResponse;
import com.kahnwald.platform.PlatformProto.GrpcPlatformModel;
import com.kahnwald.platform.PlatformProto.GetAllRequest;
import com.kahnwald.platform.domain.repository.PlatformRepository;
import io.quarkus.grpc.GrpcService;
import io.smallrye.common.annotation.Blocking;
import io.smallrye.mutiny.Uni;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@GrpcService
public class GrpcPlatformService implements GrpcPlatform {

    @Inject
    PlatformRepository platformRepository;

    @Override
    @Blocking
    public Uni<PlatformResponse> getAllPlatforms(GetAllRequest request) {
        var platforms = platformRepository
            .listAll()
            .stream()
            .map(platform -> GrpcPlatformModel
                .newBuilder()
                .setPlatformId(platform.getId())
                .setName(platform.getName())
                .setPublisher(platform.getPublisher())
                .build()
            )
            .collect(Collectors.toList());

        var response = PlatformResponse
            .newBuilder()
            .addAllPlatform(platforms)
            .build();

        return Uni.createFrom().item(() -> response);
    }
}
