package net.yorksolutions.pantrybe.DTOs;

import java.util.Optional;

public class ItemInRecipeDTO {
    public Optional<Long> id;
    public Long itemId;
    public Float weightNeeded;
    public Long recipeId;
}
