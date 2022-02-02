package com.codestoreapi.core.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@Data
@Entity
@Table(name = "sub_categories")
public class SubCategory implements Serializable {

    private static final long serialVersionUID = 2011321984668041975L;

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

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;

    @Column(name = "status", nullable = false)
    private Integer status;

    @Column (name="created_at", columnDefinition = "timestamp DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createdAt;

    @Column (name="updated_at", columnDefinition = "timestamp DEFAULT CURRENT_TIMESTAMP")
    private Timestamp updatedAt;

    @Column (name="deleted_at", columnDefinition = "timestamp DEFAULT CURRENT_TIMESTAMP")
    private Timestamp deletedAt;

    @ManyToMany()
    @OrderBy
    @JoinTable(
            name = "sub_categories_products", schema = "public",
            joinColumns = @JoinColumn(name = "sub_category_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products;
}
