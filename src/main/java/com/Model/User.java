package com.Model;

import javax.persistence.*;
import java.util.List;

/*@Entity
@Table(name = "usr")*/
public class User {
/*    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)*/
    private Long Id;
    private String username;
    private String password;
    private boolean active;
    private String token;
/*    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})*/
    private List<Role> roles;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<Role> getRoles() {
        return roles;
    }
    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
    @Override
    public String toString() {
        return "{" +
                "id=" + Id +
                ", username='" + username +
                ", password='" +password + '\'' +
                '}';
    }

    public void setToken(String token) {
        this.token="Bearer_"+token;
    }
}
