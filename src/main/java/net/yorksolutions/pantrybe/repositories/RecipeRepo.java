package net.yorksolutions.pantrybe.repositories;

import net.yorksolutions.pantrybe.models.Recipe;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepo extends CrudRepository<Recipe, Long> {
}
