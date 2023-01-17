package net.yorksolutions.pantrybe.controllers;

import net.yorksolutions.pantrybe.DTOs.ItemInRecipeDTO;
import net.yorksolutions.pantrybe.DTOs.ItemUnitDTO;
import net.yorksolutions.pantrybe.models.ItemInRecipe;
import net.yorksolutions.pantrybe.models.ItemUnit;
import net.yorksolutions.pantrybe.services.ItemUnitService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@CrossOrigin
@RequestMapping("/itemunits")
public class ItemUnitController {
    private final ItemUnitService service;
    public ItemUnitController(ItemUnitService service) {
        this.service = service;
    }
    @GetMapping
    public Iterable<ItemUnit> getAll() { return service.getAll(); }
    @PostMapping
    public void createItemUnit(@RequestBody ItemUnitDTO itemUnit){
        try{
            service.createItemUnit(itemUnit);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED);
        }
    }
    @DeleteMapping("/{id}")
    public void deleteItemUnitById(@PathVariable Long id){
        try{
            service.deleteItemUnitById(id);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/{id}")
    public void updateItemUnit(@PathVariable Long id, @RequestBody ItemUnitDTO itemUnit) {
        try {
            service.updateItemUnit(id, itemUnit);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
