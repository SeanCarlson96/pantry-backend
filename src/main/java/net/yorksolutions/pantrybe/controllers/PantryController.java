package net.yorksolutions.pantrybe.controllers;

import net.yorksolutions.pantrybe.DTOs.PantryDTO;
import net.yorksolutions.pantrybe.models.Pantry;
import net.yorksolutions.pantrybe.services.PantryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@CrossOrigin
@RequestMapping("/pantries")
public class PantryController {
    private final PantryService service;
    public PantryController(PantryService service) {
        this.service = service;
    }
    @GetMapping
    public Iterable<Pantry> getAll() { return service.getAll(); }
    @PostMapping
    public void createItemUnit(@RequestBody PantryDTO pantry){
        try{
            service.createPantry(pantry);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED);
        }
    }
    @DeleteMapping("/{id}")
    public void deleteItemUnitById(@PathVariable Long id){
        try{
            service.deletePantryById(id);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/{id}")
    public void updateItemUnit(@PathVariable Long id, @RequestBody PantryDTO pantry) {
        try {
            service.updatePantry(id, pantry);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
