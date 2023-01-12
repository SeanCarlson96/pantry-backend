package net.yorksolutions.pantrybe.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Pantry {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public Long id;
    @OneToMany
    @JsonIgnoreProperties("pantry")
    public Set<ItemUnit> items;

    public Pantry(Long id, Set<ItemUnit> items) {
        this.id = id;
        this.items = items;
    }
    public Pantry() {
    }
}
