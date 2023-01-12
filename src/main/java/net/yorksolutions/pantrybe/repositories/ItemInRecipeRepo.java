package net.yorksolutions.pantrybe.repositories;

import net.yorksolutions.pantrybe.models.ItemInRecipe;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemInRecipeRepo extends CrudRepository<ItemInRecipe, Long> {
}
