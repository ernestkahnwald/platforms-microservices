package com.kahnwald.command.data.dto;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Data;

@Data
@RegisterForReflection
public class PlatformReceiveDTO {

    private Long id;
    private String name;
    private String event;
}
