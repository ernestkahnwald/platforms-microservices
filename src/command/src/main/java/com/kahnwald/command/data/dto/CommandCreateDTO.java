package com.kahnwald.command.data.dto;

import com.kahnwald.command.domain.models.Platform;
import lombok.Data;

@Data
public class CommandCreateDTO {

    private String howTo;
    private String commandLine;
//    platformId sends as query parameter.
}
