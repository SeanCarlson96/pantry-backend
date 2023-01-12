package net.yorksolutions.pantrybe.services;

import net.yorksolutions.pantrybe.DTOs.RecipeDTO;
import net.yorksolutions.pantrybe.models.AppUser;
import net.yorksolutions.pantrybe.models.ItemInRecipe;
import net.yorksolutions.pantrybe.models.Recipe;
import net.yorksolutions.pantrybe.repositories.AppUserRepo;
import net.yorksolutions.pantrybe.repositories.ItemInRecipeRepo;
import net.yorksolutions.pantrybe.repositories.RecipeRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RecipeService {
    private final RecipeRepo recipeRepo;
    private final ItemInRecipeRepo itemInRecipeRepo;
    private final AppUserRepo appUserRepo;

    public RecipeService(RecipeRepo recipeRepo, ItemInRecipeRepo itemInRecipeRepo, AppUserRepo appUserRepo) {
        this.recipeRepo = recipeRepo;
        this.itemInRecipeRepo = itemInRecipeRepo;
        this.appUserRepo = appUserRepo;
    }
    public Iterable<Recipe> getAll() { return recipeRepo.findAll(); }
    public void createRecipe(RecipeDTO newRecipe) throws Exception {
        Recipe recipe = new Recipe();
        recipe.name = newRecipe.name;
        recipe.image = newRecipe.image;
        recipe.steps = newRecipe.steps;
        //loop through newRecipe.ingredientIds
        for (Long ingredientId : newRecipe.ingredientIds) {
            //get that ingredient by its id
            Optional<ItemInRecipe> ingredientWithId = itemInRecipeRepo.findById(ingredientId);
            if(ingredientWithId.isEmpty())
                throw new Exception();
            ingredientWithId.ifPresent(recipe.ingredients::add);
        }
        //find the user to add
        Optional<AppUser> userWithId = appUserRepo.findById(newRecipe.userId);
        if(userWithId.isEmpty())
            throw new Exception();
        recipe.user = userWithId.orElse(null);
        recipeRepo.save(recipe);
    }
    public void deleteRecipeById(Long id) throws Exception {
        Optional<Recipe> recipeWithId = recipeRepo.findById(id);
        if (recipeWithId.isEmpty())
            throw new Exception();
        recipeRepo.deleteById(id);
    }
    public void updateRecipe(Long id, RecipeDTO updatedRecipe) throws Exception {
        Optional<Recipe> recipeWithId = recipeRepo.findById(id);
        if (recipeWithId.isEmpty())
            throw new Exception();
        Recipe recipe = recipeWithId.get();
        recipe.name = updatedRecipe.name;
        recipe.image = updatedRecipe.image;
        recipe.steps = updatedRecipe.steps;
        //loop through updatedRecipe.ingredientIds
        for (Long ingredientId : updatedRecipe.ingredientIds) {
            //get that ingredient by its id
            Optional<ItemInRecipe> ingredientWithId = itemInRecipeRepo.findById(ingredientId);
            if(ingredientWithId.isEmpty())
                throw new Exception();
            ingredientWithId.ifPresent(recipe.ingredients::add);
        }
        recipeRepo.save(recipe);
    }
}
