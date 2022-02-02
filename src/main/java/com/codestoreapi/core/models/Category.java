package com.codestoreapi.core.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@Data
@Entity
@Table(name = "categories")
public class Category implements Serializable {

    private static final long serialVersionUID = 2011311984668041875L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @ToString.Include
    private Long id;

    @Column (name = "name",length = 45)
    private String name;

    @Column (name = "description")
    private String description;

    @Column (name = "type", length = 20)
    private String type;

    @Column(name = "status", nullable=false)
    private Integer status;

    @JsonManagedReference
    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private List<SubCategory> subCategories;

    @Column (name="created_at", columnDefinition = "timestamp DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createdAt;

    @Column (name="updated_at", columnDefinition = "timestamp DEFAULT CURRENT_TIMESTAMP")
    private Timestamp updatedAt;

    @Column (name="deleted_at", columnDefinition = "timestamp DEFAULT CURRENT_TIMESTAMP")
    private Timestamp deletedAt;
}
