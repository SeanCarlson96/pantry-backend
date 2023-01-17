package net.yorksolutions.pantrybe.controllers;

import net.yorksolutions.pantrybe.DTOs.ItemInRecipeDTO;
import net.yorksolutions.pantrybe.models.ItemInRecipe;
import net.yorksolutions.pantrybe.services.ItemInRecipeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@CrossOrigin
@RequestMapping("/iteminrecipes")
public class ItemInRecipeController {
    private final ItemInRecipeService service;
    public ItemInRecipeController(ItemInRecipeService service) {
        this.service = service;
    }
    @GetMapping
    public Iterable<ItemInRecipe> getAll() { return service.getAll(); }
    @PostMapping
    public void createItemInRecipe(@RequestBody ItemInRecipeDTO itemInRecipe){
        try{
            service.createItemInRecipe(itemInRecipe);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED);
        }
    }
    @DeleteMapping("/{id}")
    public void deleteItemInRecipeById(@PathVariable Long id){
        try{
            service.deleteItemInRecipeById(id);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/{id}")
    public void updateItemInRecipe(@PathVariable Long id, @RequestBody ItemInRecipeDTO itemInRecipe) {
        try {
            service.updateItemInRecipe(id, itemInRecipe);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
