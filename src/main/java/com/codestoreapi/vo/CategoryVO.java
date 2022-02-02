package com.codestoreapi.vo;

import lombok.Data;

import java.util.List;

@Data
public class CategoryVO {
    private Long id;
    private String name;
    private String description;
    private String type;
    private Integer status;
    private List<SubCategoryVO> subCategories;
}
