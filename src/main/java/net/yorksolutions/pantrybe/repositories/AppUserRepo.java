package net.yorksolutions.pantrybe.repositories;

import net.yorksolutions.pantrybe.models.AppUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppUserRepo extends CrudRepository<AppUser, Long> {
    Optional<AppUser> findAppUserByUsernameAndPassword(String username, String password);
    Optional<AppUser> findAppUserByUsername(String username);
}
