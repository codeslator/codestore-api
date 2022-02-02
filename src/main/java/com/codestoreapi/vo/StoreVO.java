package com.codestoreapi.vo;

import lombok.Data;

import java.util.List;

@Data
public class StoreVO {
    private Long id;
    private String name;
    private String description;
    private String address;
    private String email;
    private String password;
    private String picture;
    private CountryVO country;
    private List<ProductVO> products;
}
