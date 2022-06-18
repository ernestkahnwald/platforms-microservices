package com.kahnwald.platform.domain.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class Platform {

    @Id @GeneratedValue private Long id;
    private String name;
    private String publisher;
    private String cost;

    public Platform(String name, String publisher, String cost) {
         this.name = name;
         this.publisher = publisher;
         this.cost = cost;
    }
}
