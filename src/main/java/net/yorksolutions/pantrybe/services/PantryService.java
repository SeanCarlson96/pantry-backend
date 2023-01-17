package net.yorksolutions.pantrybe.services;

import net.yorksolutions.pantrybe.DTOs.PantryDTO;
import net.yorksolutions.pantrybe.models.ItemUnit;
import net.yorksolutions.pantrybe.models.Pantry;
import net.yorksolutions.pantrybe.repositories.ItemUnitRepo;
import net.yorksolutions.pantrybe.repositories.PantryRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PantryService {
    private final PantryRepo pantryRepo;
    private final ItemUnitRepo itemUnitRepo;
    public PantryService(PantryRepo pantryRepo, ItemUnitRepo itemUnitRepo) {
        this.pantryRepo = pantryRepo;
        this.itemUnitRepo = itemUnitRepo;
    }
    public Iterable<Pantry> getAll() { return pantryRepo.findAll(); }
    public void createPantry(PantryDTO newPantry) throws Exception {
        Pantry pantry = new Pantry();
        for (Long itemUnitId : newPantry.itemIds) {
            Optional<ItemUnit> itemUnitWithId = itemUnitRepo.findById(itemUnitId);
            if (itemUnitWithId.isEmpty())
                throw new Exception();
            itemUnitWithId.ifPresent(pantry.items::add);
        }
        pantryRepo.save(pantry);
    }
    public void deletePantryById(Long id) throws Exception {
        Optional<Pantry> pantryWithId = pantryRepo.findById(id);
        if (pantryWithId.isEmpty())
            throw new Exception();
        pantryRepo.deleteById(id);
    }
    public void updatePantry(Long id, PantryDTO updatedPantry) throws Exception {
        Optional<Pantry> pantryWithId = pantryRepo.findById(id);
        if (pantryWithId.isEmpty())
            throw new Exception();
        Pantry pantry = pantryWithId.get();
        for (Long itemUnitId : updatedPantry.itemIds) {
            Optional<ItemUnit> itemUnitWithId = itemUnitRepo.findById(itemUnitId);
            if (itemUnitWithId.isEmpty())
                throw new Exception();
            itemUnitWithId.ifPresent(pantry.items::add);
        }
        pantryRepo.save(pantry);
    }
}
