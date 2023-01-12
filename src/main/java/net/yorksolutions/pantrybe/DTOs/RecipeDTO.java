package net.yorksolutions.pantrybe.DTOs;

import java.util.Optional;

public class RecipeDTO {
    public Optional<Long> id;
    public String name;
    public String image;
    public Iterable<Long> ingredientIds;
    public String steps;
    public Long userId;
}
