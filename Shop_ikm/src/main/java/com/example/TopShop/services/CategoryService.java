package com.example.TopShop.services;

import com.example.TopShop.models.Category;
import com.example.TopShop.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public Category getCategoryByName(String name) {
        return categoryRepository.findByName(name); // Assuming you have a method in your repository
    }

    public void saveCategory(Category category) {
        categoryRepository.save(category); // Save the category to the database
    }

    public List<Category> listCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id); // Метод для удаления категории по ID
    }
}
