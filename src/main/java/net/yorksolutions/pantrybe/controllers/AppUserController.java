package net.yorksolutions.pantrybe.controllers;

import net.yorksolutions.pantrybe.DTOs.AppUserDTO;
import net.yorksolutions.pantrybe.models.AppUser;
import net.yorksolutions.pantrybe.services.AppUserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@CrossOrigin
@RequestMapping("/appusers")
public class AppUserController {
    private final AppUserService service;
    public AppUserController(AppUserService service) {
        this.service = service;
    }
    @GetMapping
    public Iterable<AppUser> getAll(){
        return service.getAll();
    }
    @GetMapping(params = {"username", "password"})
    public AppUser getUserByUsernameAndPassword(@RequestParam String username, @RequestParam String password){
        try{
            return service.getUserByUsernameAndPassword(username, password);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
    }
    @PostMapping
    public void createAppUser(@RequestBody AppUserDTO appUser){
        try{
            service.createAppUser(appUser);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED);
        }
    }
    @DeleteMapping("/{id}")
    public void deleteAppUserById(@PathVariable Long id){
        try{
            service.deleteAppUserById(id);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/{id}")
    public void updateAppUser(@PathVariable Long id, @RequestBody AppUserDTO appUser) {
        try {
            service.updateAppUser(id, appUser);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
