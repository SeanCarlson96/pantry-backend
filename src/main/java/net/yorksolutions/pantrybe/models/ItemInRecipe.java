package net.yorksolutions.pantrybe.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
public class ItemInRecipe {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public Long id;
    @ManyToOne
    @JsonIgnoreProperties("recipes")
    public ItemUnit item;
    public Float weightNeeded;
    @ManyToOne
    @JsonIgnoreProperties("ingredients")
    public Recipe recipe;
    public ItemInRecipe(Long id, ItemUnit item, Float weightNeeded, Recipe recipe) {
        this.id = id;
        this.item = item;
        this.weightNeeded = weightNeeded;
        this.recipe = recipe;
    }
    public ItemInRecipe() {
    }
}
