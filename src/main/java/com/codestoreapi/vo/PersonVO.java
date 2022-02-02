package com.codestoreapi.vo;

import lombok.Data;

import java.sql.Date;

@Data
public class PersonVO {
    private Long id;
    private String firstName;
    private String lastName;
    private String address;
    private String phone;
    private String picture;
    private String gender;
    private Date birthDate;
    private Integer status;
    private CountryVO country;
}
