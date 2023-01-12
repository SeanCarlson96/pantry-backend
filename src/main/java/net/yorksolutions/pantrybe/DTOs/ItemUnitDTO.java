package net.yorksolutions.pantrybe.DTOs;

import java.util.Optional;

public class ItemUnitDTO {
    public Optional<Long> id;
    public String name;
    public String image;
    public Float weightPerUnit;
    public Float caloriesPerUnit;
    public Integer pantryQuantity;
    public Long pantryId;
    public Iterable<Long> thisItemInRecipeIds;
}
