package com.kahnwald.platform.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PlatformReadDTO {
    private Long id;
    private String name;
    private String publisher;
    private String cost;
}
