package com.codestoreapi.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class AuthVO {
    private Long id;
    private String token;
    private String username;
    private String email;
    private String type;
    private Long storeId;
    private StoreVO store;
    private Long personId;
    private PersonVO person;
    private List<String> roles;
}
