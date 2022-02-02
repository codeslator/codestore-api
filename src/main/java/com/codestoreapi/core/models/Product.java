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
@Table(name = "products")
public class Product implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @ToString.Include
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "price", precision = 8, scale = 2, nullable = false)
    private Float price;

    @Column(name = "discount", precision = 8, scale = 2)
    private Float discount;

    @Column(name = "stock")
    private Integer stock;

    @Column(name = "min_stock")
    private Integer minStock;

    @Column(name = "max_stock")
    private Integer maxStock;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "status", nullable = false)
    private Integer status;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "store_id", referencedColumnName = "id")
    private Store store;

    @Column (name="created_at", columnDefinition = "timestamp DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createdAt;

    @Column (name="updated_at", columnDefinition = "timestamp DEFAULT CURRENT_TIMESTAMP")
    private Timestamp updatedAt;

    @Column (name="deleted_at", columnDefinition = "timestamp DEFAULT CURRENT_TIMESTAMP")
    private Timestamp deletedAt;

    @JsonManagedReference
    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
    private List<OrderProduct> orderProducts;

    @ManyToMany()
    @OrderBy
    @JoinTable(
            name = "sub_categories_products", schema = "public",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "sub_category_id")
    )
    private List<SubCategory> subCategories;

    @ManyToMany()
    @OrderBy
    @JoinTable(
            name = "orders_products", schema = "public",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "order_id")
    )
    private List<Order> orders;
}
