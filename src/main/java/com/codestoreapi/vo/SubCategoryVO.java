package com.codestoreapi.vo;

import lombok.Data;

@Data
public class SubCategoryVO {
    private Long id;
    private String name;
    private String description;
    private Integer status;
    private CategoryVO category;
}
