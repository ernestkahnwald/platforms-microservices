package com.kahnwald.platform;

import com.kahnwald.platform.data.clients.sync.GrpcPlatformService;
import io.quarkus.grpc.GrpcClient;
import io.smallrye.common.annotation.Blocking;
import io.smallrye.mutiny.Uni;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/test")
public class TestResource {

    @GrpcClient("baz")
    GrpcPlatform grpcplatform;

    @GET
    public Uni<String> gTest() {
        var response = grpcplatform.getAllPlatforms(
            PlatformProto
                .GetAllRequest
                .newBuilder()
                .build()
        );

        return response
            .onItem()
            .transform(platformResponse -> platformResponse
                .getPlatformList()
                .toString()
            );
    }
}
