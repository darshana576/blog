package org.springboot.blog.service;

import org.springboot.blog.dto.CategoryDto;
import org.springboot.blog.exception.ResourceNotFoundException;
import org.springboot.blog.model.Category;
import org.springboot.blog.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    // ✅ Create new Category
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = new Category();
        category.setName(categoryDto.getName());

        Category savedCategory = categoryRepository.save(category);

        return mapToDto(savedCategory);
    }

    // ✅ Update existing Category
    public CategoryDto updateCategory(Long categoryId, CategoryDto categoryDto) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + categoryId));

        category.setName(categoryDto.getName());
        Category updatedCategory = categoryRepository.save(category);

        return mapToDto(updatedCategory);
    }

    // ✅ Get all Categories
    public List<CategoryDto> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    // ✅ Get single Category by ID
    public CategoryDto getCategoryById(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + categoryId));

        return mapToDto(category);
    }

    // ✅ Delete Category
    public void deleteCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + categoryId));

        categoryRepository.delete(category);
    }

    // 🔥 Helper method: Entity -> DTO
    private CategoryDto mapToDto(Category category) {
        CategoryDto dto = new CategoryDto();
        dto.setId(category.getId());
        dto.setName(category.getName());
        return dto;
    }
}