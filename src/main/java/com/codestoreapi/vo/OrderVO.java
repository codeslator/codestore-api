package com.codestoreapi.vo;

import lombok.Data;

import java.sql.Date;
import java.util.List;

@Data
public class OrderVO {
    private Long id;
    private Date date;
    private Float totalPrice;
    private Integer status;
    private List<OrderProductVO> orderProducts;
}
