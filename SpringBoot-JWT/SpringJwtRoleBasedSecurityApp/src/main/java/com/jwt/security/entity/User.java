package com.jwt.security.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class User {

    @Id
    private String userName;
    private String userFirstName;
    private String userLastName;
    private String password;

    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name = "USER_ROLE",
       joinColumns = {
            @JoinColumn(name = "USER_ID")
       },inverseJoinColumns = {
            @JoinColumn(name = "ROLE_ID")
    })
    private Set<Role> roles;
}
