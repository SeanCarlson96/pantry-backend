package net.yorksolutions.pantrybe.services;


import net.yorksolutions.pantrybe.DTOs.AppUserDTO;
import net.yorksolutions.pantrybe.models.AppUser;
import net.yorksolutions.pantrybe.models.Recipe;
import net.yorksolutions.pantrybe.repositories.AppUserRepo;
import net.yorksolutions.pantrybe.repositories.RecipeRepo;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AppUserService {
    private final AppUserRepo appUserRepo;
    private final RecipeRepo recipeRepo;
    private final RecipeService service;
    public AppUserService(AppUserRepo appUserRepo, RecipeRepo recipeRepo, RecipeService service) {
        this.appUserRepo = appUserRepo;
        this.recipeRepo = recipeRepo;
        this.service = service;
    }
    public Iterable<AppUser> getAll() {
        return appUserRepo.findAll();
    }
    public AppUser getUserByUsernameAndPassword(String username, String password) throws Exception {
        Optional<AppUser> realAppUser = appUserRepo.findAppUserByUsernameAndPassword(username, password);
        if (realAppUser.isPresent())
            return realAppUser.get();
        throw new Exception();
    }
    public void createAppUser(AppUserDTO newAppUser)  throws Exception {
        Optional<AppUser> appUserWithUsername = appUserRepo.findAppUserByUsername(newAppUser.username);
        if (appUserWithUsername.isPresent())
            throw new Exception();
        AppUser appUser = new AppUser();
        appUser.username = newAppUser.username;
        appUser.password = newAppUser.password;
        appUserRepo.save(appUser);
    }
    public void deleteAppUserById(Long id) throws Exception {
        Optional<AppUser> appUserWithId = appUserRepo.findById(id);
        if (appUserWithId.isEmpty())
            throw new Exception();
        for(Recipe recipe : appUserWithId.get().recipes){
            service.deleteRecipeById(recipe.id);
        }
        appUserRepo.deleteById(id);
    }

    public void updateAppUser(Long id, AppUserDTO updatedAppUser) throws Exception {
        Optional<AppUser> appUserWithId = appUserRepo.findById(id);
        if (appUserWithId.isEmpty())
            throw new Exception();
        AppUser appUser = appUserWithId.get();
        appUser.username = updatedAppUser.username;
        appUser.password = updatedAppUser.password;
        for (Long recipeId : updatedAppUser.recipeIds) {
            Optional<Recipe> recipeWithId = recipeRepo.findById(recipeId);
            if (recipeWithId.isEmpty())
                throw new Exception();
            recipeWithId.ifPresent(appUser.recipes::add);
        }
        appUserRepo.save(appUser);
    }
}