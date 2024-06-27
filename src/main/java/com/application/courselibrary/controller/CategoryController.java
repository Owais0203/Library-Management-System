package com.application.courselibrary.controller;

import com.application.courselibrary.entity.Book;
import com.application.courselibrary.entity.Category;
import com.application.courselibrary.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Comparator;
import java.util.List;

@Controller
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    public String findAllCategories(Model model) {
        List<Category> categories = categoryService.findAllCategories();

        // Sorting the categories by ID in ascending order
        categories.sort(Comparator.comparing(Category::getId));

        model.addAttribute("categories", categories);
        return "categories";
    }

    @GetMapping("/remove-category/{id}")
    public String deleteCategory(@PathVariable Long id, Model model) {
        categoryService.deleteCategory(id);
        List<Category> categories = categoryService.findAllCategories();

        // Sorting the categories by ID in ascending order
        categories.sort(Comparator.comparing(Category::getId));

        model.addAttribute("categories", categories);
        return "categories";
    }

    @GetMapping("/update-category/{id}")
    public String updateCategory(@PathVariable Long id, Model model) {
        Category category = categoryService.findCategoryById(id);
        model.addAttribute("category", category);
        return "update-category";
    }

    @PostMapping("/save-update-category/{id}")
    public String saveCategory(@PathVariable Long id, Category category, BindingResult result, Model model) {
        if(result.hasErrors()) {
            return "update-category";
        }
        categoryService.updateCategory(id, category);
        List<Category> categories = categoryService.findAllCategories();

        // Sorting the categories by ID in ascending order
        categories.sort(Comparator.comparing(Category::getId));

        model.addAttribute("categories", categories);
        return "redirect:/categories";
    }

    @GetMapping("/add-category")
    public String showCreateCategory(Category category) {
        return "add-category";
    }

    @PostMapping("/save-category")
    public String saveCategory(Category category, BindingResult result, Model model) {
        if(result.hasErrors()) {
            return "add-category";
        }
        categoryService.createCategory(category);
        List<Category> categories = categoryService.findAllCategories();

        // Sorting the categories by ID in ascending order
        categories.sort(Comparator.comparing(Category::getId));

        model.addAttribute("categories", categories);
        return "redirect:/categories";
    }
}
