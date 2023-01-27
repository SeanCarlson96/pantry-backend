package net.yorksolutions.pantrybe.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.util.Set;

@Entity
public class ItemUnit {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public Long id;
    public String name;
    public String image;
    public Float weightPerUnit;
    public Float caloriesPerUnit;
    public Integer pantryQuantity;
    @ManyToOne(cascade = jakarta.persistence.CascadeType.DETACH)
    @JsonIgnoreProperties("items")
    public Pantry pantry;
    @OneToMany(cascade = jakarta.persistence.CascadeType.DETACH, orphanRemoval = true)
    @JsonIgnoreProperties("item")
    public Set<ItemInRecipe> thisItemInRecipes;

    public ItemUnit(Long id, String name, String image, Float weightPerUnit, Float caloriesPerUnit, Integer pantryQuantity, Pantry pantry, Set<ItemInRecipe> thisItemInRecipes) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.weightPerUnit = weightPerUnit;
        this.caloriesPerUnit = caloriesPerUnit;
        this.pantryQuantity = pantryQuantity;
        this.pantry = pantry;
        this.thisItemInRecipes = thisItemInRecipes;
    }
    public ItemUnit() {
    }
}
