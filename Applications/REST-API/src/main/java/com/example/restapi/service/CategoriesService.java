package com.example.restapi.service;

import com.example.restapi.entity.Categories;
import com.example.restapi.repository.CategoriesRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CategoriesService {

    private final CategoriesRepository categoriesRepository;


    public CategoriesService(CategoriesRepository categoriesRepository) {
        this.categoriesRepository = categoriesRepository;
    }


    public Categories addCategories(Categories categories) {
        return categoriesRepository.save(categories);
    }

    public Optional<Categories> readOneCategory(UUID id) {
        return categoriesRepository.findById(id);
    }

    public List<Categories> readCategories() {
        return categoriesRepository.findAll();
    }

    public boolean updateCategories(Categories categories) {
        boolean test = false;
        try {
            categoriesRepository.save(categories);
            test = true;

        } catch (Exception ex) {
            ex.fillInStackTrace();
        }
        return test;
    }


    public boolean deleteCategories(UUID id) {
        boolean test = false;
        try {
            categoriesRepository.deleteById(id);
            test = true;

        } catch (Exception ex) {
            ex.fillInStackTrace();
        }
        return test;
    }

}
