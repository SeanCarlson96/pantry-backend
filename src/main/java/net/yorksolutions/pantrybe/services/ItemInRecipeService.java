package net.yorksolutions.pantrybe.services;

import net.yorksolutions.pantrybe.DTOs.ItemInRecipeDTO;
import net.yorksolutions.pantrybe.models.ItemInRecipe;
import net.yorksolutions.pantrybe.models.ItemUnit;
import net.yorksolutions.pantrybe.models.Recipe;
import net.yorksolutions.pantrybe.repositories.ItemInRecipeRepo;
import net.yorksolutions.pantrybe.repositories.ItemUnitRepo;
import net.yorksolutions.pantrybe.repositories.RecipeRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ItemInRecipeService {
    private final ItemInRecipeRepo itemInRecipeRepo;
    private final RecipeRepo recipeRepo;
    private final ItemUnitRepo itemUnitRepo;
    public ItemInRecipeService(ItemInRecipeRepo itemInRecipeRepo, RecipeRepo recipeRepo, ItemUnitRepo itemUnitRepo) {
        this.itemInRecipeRepo = itemInRecipeRepo;
        this.recipeRepo = recipeRepo;
        this.itemUnitRepo = itemUnitRepo;
    }
    public Iterable<ItemInRecipe> getAll() { return itemInRecipeRepo.findAll(); }
    public void createItemInRecipe(ItemInRecipeDTO newItemInRecipe) throws Exception {
        ItemInRecipe itemInRecipe = new ItemInRecipe();
        itemInRecipe.weightNeeded = newItemInRecipe.weightNeeded;
        //find the item
        Optional<ItemUnit> itemWithId = itemUnitRepo.findById(newItemInRecipe.itemId);
        if(itemWithId.isEmpty())
            throw new Exception();
        itemInRecipe.item = itemWithId.orElse(null);
        //find the applicable recipe
        Optional<Recipe> recipeWithId = recipeRepo.findById(newItemInRecipe.recipeId);
        if(recipeWithId.isEmpty())
            throw new Exception();
        itemInRecipe.recipe = recipeWithId.orElse(null);
        itemInRecipeRepo.save(itemInRecipe);
        recipeWithId.get().ingredients.add(itemInRecipe);
        recipeRepo.save(recipeWithId.get());
    }
    public void deleteItemInRecipeById(Long id) throws Exception {
        Optional<ItemInRecipe> itemInRecipeWithId = itemInRecipeRepo.findById(id);
        if (itemInRecipeWithId.isEmpty())
            throw new Exception();
        itemInRecipeRepo.deleteById(id);
    }
    public void updateItemInRecipe(Long id, ItemInRecipeDTO updatedItemInRecipe) throws Exception {
        Optional<ItemInRecipe> itemInRecipeWithId = itemInRecipeRepo.findById(id);
        if (itemInRecipeWithId.isEmpty())
            throw new Exception();
        ItemInRecipe itemInRecipe = itemInRecipeWithId.get();
        itemInRecipe.weightNeeded = updatedItemInRecipe.weightNeeded;
        //find the item
        Optional<ItemUnit> itemWithId = itemUnitRepo.findById(updatedItemInRecipe.itemId);
        if(itemWithId.isEmpty())
            throw new Exception();
        itemInRecipe.item = itemWithId.orElse(null);
        //find the applicable recipe
        Optional<Recipe> recipeWithId = recipeRepo.findById(updatedItemInRecipe.recipeId);
        if(recipeWithId.isEmpty())
            throw new Exception();
        itemInRecipe.recipe = recipeWithId.orElse(null);
        itemInRecipeRepo.save(itemInRecipe);
        recipeWithId.get().ingredients.add(itemInRecipe);
        recipeRepo.save(recipeWithId.get());
    }
}
