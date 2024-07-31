package com.example.restapi.service;

import com.example.restapi.entity.Categories;
import com.example.restapi.dto.CategoriesDTO;
import com.example.restapi.mapper.CategoriesMapper;
import com.example.restapi.repository.CategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CategoriesService {

    private final CategoriesRepository categoriesRepository;
    private final CategoriesMapper categoriesMapper;

    @Autowired
    public CategoriesService(CategoriesRepository categoriesRepository, CategoriesMapper categoriesMapper) {
        this.categoriesRepository = categoriesRepository;
        this.categoriesMapper = categoriesMapper;
    }

    public CategoriesDTO addCategories(CategoriesDTO categoriesDTO) {
        Categories categories = categoriesMapper.toCategories(categoriesDTO);
        return categoriesMapper.toCategoriesDto(categoriesRepository.save(categories));
    }

    public Optional<CategoriesDTO> readOneCategories(UUID id) {
        return categoriesRepository.findById(id).map(categoriesMapper::toCategoriesDto);
    }

    public List<CategoriesDTO> readCategories() {
        return categoriesRepository.findAll().stream()
                .map(categoriesMapper::toCategoriesDto)
                .collect(Collectors.toList());
    }

    public boolean updateCategories(CategoriesDTO categoriesDTO) {
        boolean test = false;
        try {
            Categories categories = categoriesMapper.toCategories(categoriesDTO);
            categoriesRepository.save(categories);
            test = true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return test;
    }

    public boolean deleteCategories(UUID id) {
        boolean test = false;
        try {
            categoriesRepository.deleteById(id);
            test = true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return test;
    }

}
