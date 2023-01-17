package net.yorksolutions.pantrybe.services;

import net.yorksolutions.pantrybe.DTOs.ItemUnitDTO;
import net.yorksolutions.pantrybe.models.ItemInRecipe;
import net.yorksolutions.pantrybe.models.ItemUnit;
import net.yorksolutions.pantrybe.models.Pantry;
import net.yorksolutions.pantrybe.repositories.ItemInRecipeRepo;
import net.yorksolutions.pantrybe.repositories.ItemUnitRepo;
import net.yorksolutions.pantrybe.repositories.PantryRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ItemUnitService {
    private final ItemUnitRepo itemUnitRepo;
    private final ItemInRecipeRepo itemInRecipeRepo;
    private final PantryRepo pantryRepo;

    public ItemUnitService(ItemUnitRepo itemUnitRepo, ItemInRecipeRepo itemInRecipeRepo, PantryRepo pantryRepo) {
        this.itemUnitRepo = itemUnitRepo;
        this.itemInRecipeRepo = itemInRecipeRepo;
        this.pantryRepo = pantryRepo;
    }
    public Iterable<ItemUnit> getAll() { return itemUnitRepo.findAll(); }
    public void createItemUnit(ItemUnitDTO newItemUnit) { //throws Exception
        ItemUnit itemUnit = new ItemUnit();
        itemUnit.name = newItemUnit.name;
        itemUnit.image = newItemUnit.image;
        itemUnit.weightPerUnit = newItemUnit.weightPerUnit;
        itemUnit.caloriesPerUnit = newItemUnit.caloriesPerUnit;
        itemUnit.pantryQuantity = newItemUnit.pantryQuantity;
        //find the pantry
        Optional<Pantry> pantryWithId = pantryRepo.findById(newItemUnit.pantryId);
//        if(pantryWithId.isEmpty()) {
//            throw new Exception();
//        }
        itemUnit.pantry = pantryWithId.orElse(null);

        itemUnitRepo.save(itemUnit);
        pantryWithId.get().items.add(itemUnit);
        pantryRepo.save(pantryWithId.get());
    }
    public void deleteItemUnitById(Long id) throws Exception {
        Optional<ItemUnit> itemUnitWithId = itemUnitRepo.findById(id);
        if (itemUnitWithId.isEmpty()) {
            throw new Exception();
        }
//        ItemUnit itemUnit = itemUnitWithId.get();
//
//        Pantry pantry = pantryRepo.findById(itemUnit.pantry.id).get();
//        Long itemId = 0L;
//        for ( ItemUnit item : pantry.items ) {
//            if(item.id == itemUnit.id) {
//                itemId = item.id;
//            }
//        }
//        if(itemId > 0){
//            pantry.items.remove(itemId);
//            System.out.println("here");
//        }
//        pantryRepo.save(pantry);

        itemUnitRepo.deleteById(id);
    }
    public void updateItemUnit(Long id, ItemUnitDTO updatedItemUnit) throws Exception {
        Optional<ItemUnit> itemUnitWithId = itemUnitRepo.findById(id);
        if (itemUnitWithId.isEmpty())
            throw new Exception();
        ItemUnit itemUnit = itemUnitWithId.get();
        itemUnit.name = updatedItemUnit.name;
        itemUnit.image = updatedItemUnit.image;
        itemUnit.weightPerUnit = updatedItemUnit.weightPerUnit;
        itemUnit.caloriesPerUnit = updatedItemUnit.caloriesPerUnit;
        itemUnit.pantryQuantity = updatedItemUnit.pantryQuantity;
        //find the pantry
        Optional<Pantry> pantryWithId = pantryRepo.findById(updatedItemUnit.pantryId);
        if(pantryWithId.isEmpty())
            throw new Exception();
        itemUnit.pantry = pantryWithId.orElse(null);
        //find the applicable ItemInRecipes
        //this is not something the user can update
//        for (Long itemInRecipeId : updatedItemUnit.thisItemInRecipeIds) {
//            Optional<ItemInRecipe> itemInRecipeWithId = itemInRecipeRepo.findById(itemInRecipeId);
//            if (itemInRecipeWithId.isEmpty())
//                throw new Exception();
//            itemInRecipeWithId.ifPresent(itemUnit.thisItemInRecipes::add);
//        }
        ///
        itemUnitRepo.save(itemUnit);
    }
}
