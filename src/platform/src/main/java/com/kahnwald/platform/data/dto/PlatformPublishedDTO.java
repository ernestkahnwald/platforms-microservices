package com.kahnwald.platform.data.dto;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Data;

@Data
@RegisterForReflection
public class PlatformPublishedDTO {

    private Long id;
    private String name;
    private String event;
}
