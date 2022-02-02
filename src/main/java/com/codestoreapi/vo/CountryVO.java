package com.codestoreapi.vo;

import lombok.Data;

@Data
public class CountryVO {
    private Integer id;
    private String name;
    private String abbreviation;
    private String prefix;
    private Integer status;
}
