package net.yorksolutions.pantrybe.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.util.Set;

@Entity
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public Long id;
    @Column(unique = true)
    public String username;
    public String password;
    @OneToMany(cascade = jakarta.persistence.CascadeType.DETACH, orphanRemoval = true)
    @JsonIgnoreProperties("user")
    public Set<Recipe> recipes;

    public AppUser(Long id, String username, String password, Set<Recipe> recipes) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.recipes = recipes;
    }
    public AppUser() {
    }
}
