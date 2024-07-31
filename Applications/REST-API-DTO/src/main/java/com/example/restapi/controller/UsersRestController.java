package com.example.restapi.controller;

import com.example.restapi.dto.UsersDTO;
import com.example.restapi.service.UsersService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/v1")
public class UsersRestController {

    private final UsersService usersService;
    @Value("${jwt.secret}")
    private String SECRET_KEY;

    public UsersRestController(UsersService usersService){
        this.usersService = usersService;
    }

    @PostMapping("users")
    public UsersDTO createUsers(@RequestBody UsersDTO usersDTO) {

        return usersService.addUsers(usersDTO);
    }

    @GetMapping("users")
    public List<UsersDTO> getAllUser() {
        return usersService.readUsers();
    }

    @GetMapping("user/{id}")
    public Optional<UsersDTO> getOneUser(@PathVariable("id") UUID id) {
        return usersService.readOneUser(id);
    }

    @PutMapping("user/{id}")
    public boolean UpdateUser(@RequestBody UsersDTO usersDTO) {
        boolean resultat = false;
        try{
            resultat = usersService.updateUsers(usersDTO);
            resultat = true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return resultat;
    }

    @DeleteMapping("user/{id}")
    public boolean DeleteUser(@PathVariable("id") UUID id) {
        return usersService.deleteUsers(id);
    }

    @GetMapping("lost/findOne")
    public UsersDTO SearchUser(@RequestParam("firstname") String firstname,
                               @RequestParam("lastname") String lastname,
                               @RequestParam("email") String email,
                               @RequestParam("username") String username) {
        return usersService.searchUsersCriterias(firstname, lastname, email, username);
    }

    @GetMapping("users/email")
    public String findUsersIdByUsersEmail(@RequestParam("email") String email) {
        Optional<UsersDTO> optionalUser = usersService.findUsersByUsersEmail(email);
        return optionalUser.map(usersDTO -> usersDTO.getUsersId().toString()).orElse(null);
    }
}
