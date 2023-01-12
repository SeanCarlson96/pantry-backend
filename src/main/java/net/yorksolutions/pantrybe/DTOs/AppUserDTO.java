package net.yorksolutions.pantrybe.DTOs;

import java.util.Optional;

public class AppUserDTO {
    public Optional<Long> id;
    public String username;
    public String password;
    public Iterable<Long> recipeIds;
}
