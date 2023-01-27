package net.yorksolutions.pantrybe.controllers;

import net.yorksolutions.pantrybe.DTOs.RecipeDTO;
import net.yorksolutions.pantrybe.models.Recipe;
import net.yorksolutions.pantrybe.services.RecipeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@CrossOrigin
@RequestMapping("/recipes")
public class RecipeController {
    private final RecipeService service;
    public RecipeController(RecipeService service) {
        this.service = service;
    }
    @GetMapping
    public Iterable<Recipe> getAll() { return service.getAll(); }
    @PostMapping
    public void createRecipe(@RequestBody RecipeDTO recipe){
        try{
            service.createRecipe(recipe);
        } catch (Exception e){
            System.out.println(e);
            throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED);
        }
    }
    @DeleteMapping("/{id}")
    public void deleteRecipeById(@PathVariable Long id){
        try{
            service.deleteRecipeById(id);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/{id}")
    public void updateRecipe(@PathVariable Long id, @RequestBody RecipeDTO recipe) {
        try {
            service.updateRecipe(id, recipe);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
