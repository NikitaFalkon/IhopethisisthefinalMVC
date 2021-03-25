package com.Model;

import javax.persistence.*;
import java.util.List;

/*@Entity
@Table(name = "roles")*/
public class Role {
/*    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)*/
    private Long id;

//    @Column(name = "name")
    private String name;

//    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private List<User> users;

    @Override
    public String toString() {
        return "Role{" +
                "id: " + getId() + ", " +
                "name: " + name + "}";
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}