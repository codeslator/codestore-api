package com.codestoreapi.core.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@Data
@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @ToString.Include
    private Long id;



    @Column(name = "username", length = 20, nullable = false)
    private String username;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "auth_2_factor", nullable=false)
    private Integer auth2Factor;

    @Column (name = "auth_2_expire_time", nullable=true,length=50)
    private String auth2ExpireTime;

    @Column (name = "auth_2_default_type", nullable=true,length=10)
    private String auth2DefaultType;

    @Column(name = "verification_code", nullable = true, length = 8)
    private String verificationCode;

    @Column(name = "type", nullable = true, length = 10)
    private String type;

    @Column(name = "status", insertable = false, columnDefinition = "smallint DEFAULT 0")
    private Integer status;

    @ManyToMany()
    @OrderBy
    @JoinTable(
            name = "users_roles", schema = "public",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles;

    @OneToOne(cascade = {
            CascadeType.MERGE,
            CascadeType.PERSIST
    },fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person person;

    @OneToOne(cascade = {
            CascadeType.MERGE,
            CascadeType.PERSIST
    },fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", referencedColumnName = "id")
    private Store store;

    @Column (name="created_at", columnDefinition = "timestamp DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createdAt;

    @Column (name="updated_at", columnDefinition = "timestamp DEFAULT CURRENT_TIMESTAMP")
    private Timestamp updatedAt;

    @Column (name="deleted_at", columnDefinition = "timestamp DEFAULT CURRENT_TIMESTAMP")
    private Timestamp deletedAt;


}
