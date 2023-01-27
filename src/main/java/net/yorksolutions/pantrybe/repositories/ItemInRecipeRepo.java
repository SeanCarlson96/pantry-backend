package net.yorksolutions.pantrybe.repositories;

import net.yorksolutions.pantrybe.models.ItemInRecipe;
import net.yorksolutions.pantrybe.models.Recipe;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemInRecipeRepo extends CrudRepository<ItemInRecipe, Long> {
    List<ItemInRecipe> findAllByRecipe(Recipe recipe);
}
