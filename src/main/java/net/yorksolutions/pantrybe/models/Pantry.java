package net.yorksolutions.pantrybe.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

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
