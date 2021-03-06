package com.kahnwald.platform.domain.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Platform {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String publisher;
    private String cost;

    public Platform(String name, String publisher, String cost) {
         this.name = name;
         this.publisher = publisher;
         this.cost = cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
            return false;
        }
        Platform platform = (Platform) o;
        return id != null && Objects.equals(id, platform.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
