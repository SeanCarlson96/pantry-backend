package net.yorksolutions.pantrybe.repositories;

import net.yorksolutions.pantrybe.models.Pantry;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PantryRepo extends CrudRepository<Pantry, Long> {
}
