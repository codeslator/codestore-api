package com.codestoreapi.vo;

import com.codestoreapi.core.models.Country;
import com.codestoreapi.core.models.Role;
import lombok.Data;

import java.sql.Date;
import java.util.List;

@Data
public class UserVO {
    private Long id;
    private String username;
    private String email;
    private String password;
    private String type;
    private PersonVO person;
    private StoreVO store;
    private Integer status;
    private Integer auth2Factor;
    private String auth2ExpireTime;
    private String auth2DefaultType;
    private String verificationCode;
    private List<RoleVO> roles;

    public UserVO() {
        super();
    }

    public UserVO(String username, String email,String password, String type,
                  Integer auth2Factor,
                  String auth2ExpireTime,
                  String auth2DefaultType) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.type = type;
        this.auth2Factor = auth2Factor;
        this.auth2DefaultType = auth2DefaultType;
        this.auth2ExpireTime = auth2ExpireTime;
    }

}
