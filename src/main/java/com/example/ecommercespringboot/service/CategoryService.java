package com.example.ecommercespringboot.service;

import com.example.ecommercespringboot.dto.CategoryDto;
import com.example.ecommercespringboot.entity.Category;

import java.util.List;

public interface CategoryService {

    Category createCategory(CategoryDto categoryDto);

    List<Category> getAllCategories();

}
