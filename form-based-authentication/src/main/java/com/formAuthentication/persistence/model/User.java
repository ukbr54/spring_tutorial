package com.formAuthentication.persistence.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Ujjwal Gupta on Dec,2019
 */

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "USERS")
@Entity(name = "USER")
public class User {

    @Id @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "PASSWORD")
    private String password;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID",referencedColumnName = "ID")
    private List<UserRole> roles;


    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
