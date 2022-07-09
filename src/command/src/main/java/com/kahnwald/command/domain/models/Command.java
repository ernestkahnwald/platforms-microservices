package com.kahnwald.command.domain.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Command {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String howTo;
    private String commandLine;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private Platform platform;
}
