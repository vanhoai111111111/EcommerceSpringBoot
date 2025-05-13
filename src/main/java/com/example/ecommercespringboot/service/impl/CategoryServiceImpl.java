package com.example.ecommercespringboot.service.impl;

import com.example.ecommercespringboot.dto.CategoryDto;
import com.example.ecommercespringboot.entity.Category;
import com.example.ecommercespringboot.repository.CategoryRepository;
import com.example.ecommercespringboot.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public Category createCategory(CategoryDto categoryDto) {
        Category category = new Category();
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());
        return categoryRepository.save(category);
    }

    public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }

}
