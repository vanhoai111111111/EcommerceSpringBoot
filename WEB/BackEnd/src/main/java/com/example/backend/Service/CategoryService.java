package com.example.backend.Service;

import com.example.backend.Repository.CategoryRepository;
import com.example.backend.dto.CategoryDto;
import com.example.backend.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    public List<CategoryDto> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream().map(this::convertToDto).toList();
    }

    public CategoryDto getCategoryById(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found"));
        return convertToDto(category);
    }

    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = new Category();
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());
        category = categoryRepository.save(category);
        return convertToDto(category);
    }

    public CategoryDto updateCategory(Long id, CategoryDto categoryDto) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found"));
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());
        category = categoryRepository.save(category);
        return convertToDto(category);
    }

    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

    private CategoryDto convertToDto(Category category) {
        CategoryDto dto = new CategoryDto();
        dto.setId(category.getId());
        dto.setName(category.getName());
        dto.setDescription(category.getDescription());
        return dto;
    }
}
