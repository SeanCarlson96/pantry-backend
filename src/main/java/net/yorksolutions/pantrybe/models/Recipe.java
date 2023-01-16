package net.yorksolutions.pantrybe.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.util.Set;

@Entity
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public Long id;
    public String name;
    public String image;
    @OneToMany
    @JsonIgnoreProperties("recipe")
    @Cascade(CascadeType.ALL)
    public Set<ItemInRecipe> ingredients;
    public String steps;
    @ManyToOne
    @JsonIgnoreProperties("recipes")
    public AppUser user;
    public Recipe(Long id, String name, String image, Set<ItemInRecipe> ingredients, String steps, AppUser user) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.ingredients = ingredients;
        this.steps = steps;
        this.user = user;
    }
    public Recipe() {
    }
}