package com.sd.springfinalproject.entity;

import javax.persistence.*;
@Entity
@Table(name="authorities")
public class Authorities {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="auth_id")
    private int authId;

    @Column(name = "authority")
    private String authority;

    @ManyToOne(fetch=FetchType.LAZY, cascade= {CascadeType.DETACH,
            CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "user_id")
    private Users user;

    @Column(name = "username")
    private String username;

    public Authorities() {
    }

    public Authorities(String authority, Users user, String username) {
        this.authority = authority;
        this.user = user;
        this.username = username;
    }

    public int getAuthId() {
        return authId;
    }

    public void setAuthId(int authId) {
        this.authId = authId;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
