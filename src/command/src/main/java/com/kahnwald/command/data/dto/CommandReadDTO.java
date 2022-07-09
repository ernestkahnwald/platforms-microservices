package com.kahnwald.command.data.dto;

import lombok.Data;

@Data
public class CommandReadDTO {

    private Long id;
    private String howTo;
    private String commandLine;
//    private Long platformId;
}
