package org.ec.entity;

import io.quarkus.hibernate.reactive.panache.PanacheEntity;
import lombok.Getter;
import lombok.Setter;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Cacheable
@Getter
@Setter
@Schema(name = "Movie", description = "Movie representation")
public class Movie extends PanacheEntity {

    @Column(length = 40, unique = true)
    @Schema(required = true)
    private String name;

    public Movie(){}

    public Movie(String name) {
        this.name = name;
    }
}
