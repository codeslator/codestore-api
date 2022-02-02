package com.codestoreapi.vo;

import com.codestoreapi.core.models.Role;
import lombok.Data;

import java.util.Set;

@Data
public class SignUpVO {
    private String username;
    private String email;
    private String password;
    private Set<String> role;
    private String type = "CUSTOMER";
    private Object auth;
    private Integer auth2Factor;
    private String auth2ExpireTime;
    private String auth2DefaultType;
    private Integer status = 0;
    private Boolean isAccept;
}
