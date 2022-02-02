package com.codestoreapi.core.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "countries")
public class Country implements Serializable {


    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @ToString.Include
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column (name = "abbreviation", length = 4)
    private String abbreviation;

    @Column (name = "prefix", length = 10)
    private String prefix;

    @Column(name = "status", nullable=false)
    private Integer status;

    @Column (name="created_at", columnDefinition = "timestamp DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createdAt;

    @Column (name="updated_at", columnDefinition = "timestamp DEFAULT CURRENT_TIMESTAMP")
    private Timestamp updatedAt;

    @Column (name="deleted_at", columnDefinition = "timestamp DEFAULT CURRENT_TIMESTAMP")
    private Timestamp deletedAt;

}
