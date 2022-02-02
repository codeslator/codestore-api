package com.codestoreapi.vo;

import lombok.Data;

import java.util.List;

@Data
public class ProductVO {
    private Long id;
    private String name;
    private String description;
    private Float price;
    private Float discount;
    private Integer stock;
    private Integer minStock;
    private Integer maxStock;
    private String imageUrl;
    private Integer status;
    private List<SubCategoryVO> subCategories;
    private StoreVO store;
}
