package com.example.restapi.controller;

import com.example.restapi.entity.Users;
import com.example.restapi.service.UsersService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/")
public class UsersRestController {

    private final UsersService usersService;

    public UsersRestController(UsersService usersService){
        this.usersService = usersService;
    }
    @PostMapping("users")
    public Users createUsers(@RequestBody Users users) {
        users.setUsersId(UUID.randomUUID());
        usersService.addUsers(users);
        return users;
    }

    @GetMapping("users")
    public List<Users> getAllUser() {
        return usersService.readUsers();
    }

    @GetMapping("user/{id}")
    public Optional<Users> getOneUser(@PathVariable("id") UUID id) {
        return usersService.readOneUser(id);
    }

    @PutMapping("user/{id}")
    public boolean UpdateUser(@PathVariable("id") UUID id, @RequestBody Users users) {
        Optional<Users> user_test = usersService.readOneUser(id);
        boolean resultat = false;
        if(user_test!=null) {
            users.setUsersId(user_test.get().getUsersId());
            resultat = usersService.updateUsers(users);
        }
        return resultat;
    }

    @DeleteMapping("user/{id}")
    public boolean DeleteUser(@PathVariable("id") UUID id) {
        return usersService.deleteUsers(id);
    }


    @GetMapping("user/lost/findOne")
    public Users SearchUser(@RequestParam("firstname") String firstname,
                            @RequestParam("lastname") String lastname,
                            @RequestParam("email") String email,
                            @RequestParam("username") String username){
        return usersService.searchUsersCriterias(firstname, lastname, email, username);
    }
}
