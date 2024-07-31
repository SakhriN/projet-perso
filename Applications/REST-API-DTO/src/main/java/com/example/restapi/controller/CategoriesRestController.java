package com.example.restapi.controller;

import com.example.restapi.dto.CategoriesDTO;
import com.example.restapi.service.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class CategoriesRestController {

    @Autowired
    private CategoriesService categoriesService;

    @PostMapping("categories")
    public CategoriesDTO createCategories(@RequestBody CategoriesDTO categoriesDTO) {
        return categoriesService.addCategories(categoriesDTO);
    }

    @GetMapping("categories")
    public List<CategoriesDTO> getAllCategories() {
        return categoriesService.readCategories();
    }

    @GetMapping("categories/{id}")
    public Optional<CategoriesDTO> getOneCategories(@PathVariable("id") UUID id) {
        return categoriesService.readOneCategories(id);
    }

    @PutMapping("categories/{id}")
    public boolean UpdateCategories(@RequestBody CategoriesDTO categoriesDTO) {
        boolean resultat = false;
        try{
            resultat = categoriesService.updateCategories(categoriesDTO);
            resultat = true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return resultat;
    }

    @DeleteMapping("categories/{id}")
    public boolean DeleteCategories(@PathVariable("id") UUID id) {
        return categoriesService.deleteCategories(id);
    }
}
