package com.codestoreapi.vo;

import lombok.Data;

import java.util.List;

@Data
public class OrderProductVO {
    private List<ProductVO> products;
    private Integer quantity;
}
