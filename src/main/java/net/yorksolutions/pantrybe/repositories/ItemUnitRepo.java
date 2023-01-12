package net.yorksolutions.pantrybe.repositories;

import net.yorksolutions.pantrybe.models.ItemUnit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemUnitRepo extends CrudRepository<ItemUnit, Long> {
}
