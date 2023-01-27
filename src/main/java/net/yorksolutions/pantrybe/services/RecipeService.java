package net.yorksolutions.pantrybe.services;

import net.yorksolutions.pantrybe.DTOs.RecipeDTO;
import net.yorksolutions.pantrybe.models.AppUser;
import net.yorksolutions.pantrybe.models.ItemInRecipe;
import net.yorksolutions.pantrybe.models.Recipe;
import net.yorksolutions.pantrybe.repositories.AppUserRepo;
import net.yorksolutions.pantrybe.repositories.ItemInRecipeRepo;
import net.yorksolutions.pantrybe.repositories.RecipeRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecipeService {
    private final RecipeRepo recipeRepo;
    private final ItemInRecipeRepo itemInRecipeRepo;
    private final AppUserRepo appUserRepo;
    private final ItemInRecipeService service;

    public RecipeService(RecipeRepo recipeRepo, ItemInRecipeRepo itemInRecipeRepo, AppUserRepo appUserRepo, ItemInRecipeService service) {
        this.recipeRepo = recipeRepo;
        this.itemInRecipeRepo = itemInRecipeRepo;
        this.appUserRepo = appUserRepo;
        this.service = service;
    }
    public Iterable<Recipe> getAll() { return recipeRepo.findAll(); }
    public void createRecipe(RecipeDTO newRecipe) throws Exception {
        Recipe recipe = new Recipe();
        recipe.name = newRecipe.name;
        recipe.image = newRecipe.image;
        recipe.steps = newRecipe.steps;
        for (Long ingredientId : newRecipe.ingredientIds) {
            Optional<ItemInRecipe> ingredientWithId = itemInRecipeRepo.findById(ingredientId);
            if(ingredientWithId.isEmpty())
                throw new Exception();
            ingredientWithId.ifPresent(recipe.ingredients::add);
        }
        Optional<AppUser> userWithId = appUserRepo.findById(newRecipe.userId);
        if(userWithId.isEmpty())
            throw new Exception();
        recipe.user = userWithId.orElse(null);
        recipeRepo.save(recipe);
        AppUser user = userWithId.get();
        user.recipes.add(recipe);
        appUserRepo.save(user);
    }
    public void deleteRecipeById(Long id) throws Exception {
        Optional<Recipe> recipeWithId = recipeRepo.findById(id);
        if (recipeWithId.isEmpty())
            throw new Exception();
        Recipe recipe = recipeWithId.get();
        List<ItemInRecipe> items = itemInRecipeRepo.findAllByRecipe(recipe);
        for(ItemInRecipe itemInRecipe : items){
            service.deleteItemInRecipeById(itemInRecipe.id);
        }
        AppUser appUser = appUserRepo.findById(recipeWithId.get().user.id).get();
        appUser.recipes.remove(recipeWithId.get());
        appUserRepo.save(appUser);
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
        for (Long ingredientId : updatedRecipe.ingredientIds) {
            Optional<ItemInRecipe> ingredientWithId = itemInRecipeRepo.findById(ingredientId);
            if(ingredientWithId.isEmpty())
                throw new Exception();
            ingredientWithId.ifPresent(recipe.ingredients::add);
        }
        AppUser user = appUserRepo.findById(updatedRecipe.userId).get();
        user.recipes.add(recipe);
        appUserRepo.save(user);
        //recipeRepo.save(recipe);
    }
}
