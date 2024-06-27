package com.application.courselibrary.service;

import com.application.courselibrary.entity.Category;
import com.application.courselibrary.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> findAllCategories() {
        return categoryRepository.findAll();
    }

    public Category findCategoryById(@PathVariable Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found!"));
    }

    public void createCategory(@RequestBody Category category) {
        categoryRepository.save(category);
    }

    public void updateCategory(@PathVariable Long id, @RequestBody Category category) {
        Category category1 = categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found!"));
        category1.setName(category.getName());
        categoryRepository.save(category1);
    }

    public void deleteCategory(@PathVariable Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found!"));
        categoryRepository.deleteById(category.getId());
    }
}
