package com.example.restapi.controller;

import com.example.restapi.entity.Categories;
import com.example.restapi.service.CategoriesService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/")
public class CategoriesRestController {

private final CategoriesService categoriesService;

public CategoriesRestController(CategoriesService categoriesService){
        this.categoriesService = categoriesService;
        }
@PostMapping("categories")
public Categories createCategories(@RequestBody Categories categories) {
        categories.setCategoriesId(UUID.randomUUID());
        categoriesService.addCategories(categories);
        return categories;
        }

@GetMapping("categories")
public List<Categories> getAllCategories() {
        return categoriesService.readCategories();
        }

@GetMapping("category/{id}")
public Optional<Categories> getOneCategories(@PathVariable("id") UUID id) {
        return categoriesService.readOneCategory(id);
        }

@PutMapping("category/{id}")
public boolean UpdateCategories(@PathVariable("id") UUID id, @RequestBody Categories categories) {
        Optional<Categories> category_test = categoriesService.readOneCategory(id);
        boolean resultat = false;
        if(category_test!=null) {
        resultat = categoriesService.updateCategories(categories);
        }
        return resultat;
        }

@DeleteMapping("category/{id}")
public boolean DeleteCategories(@PathVariable("id") UUID id) {
        return categoriesService.deleteCategories(id);
        }
        }
