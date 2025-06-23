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

    /**
     * Получает категорию по названию
     * Используется для поиска существующих категорий перед созданием новых
     */
    public Category getCategoryByName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return null;
        }
        return categoryRepository.findByName(name.trim());
    }

    /**
     * Сохраняет категорию в базе данных
     * Выполняет базовую валидацию перед сохранением
     */
    public void saveCategory(Category category) {
        if (category == null) {
            System.out.println("Ошибка: Попытка сохранить пустую категорию");
            return;
        }

        if (category.getName() == null || category.getName().trim().isEmpty()) {
            System.out.println("Ошибка: Название категории не может быть пустым");
            return;
        }

        try {
            categoryRepository.save(category);
        } catch (Exception e) {
            System.out.println("Ошибка при сохранении категории: " + e.getMessage());
        }
    }

    /**
     * Возвращает список всех категорий
     * Используется для отображения в формах выбора
     */
    public List<Category> listCategories() {
        try {
            return categoryRepository.findAll();
        } catch (Exception e) {
            System.out.println("Ошибка при получении списка категорий: " + e.getMessage());
            return List.of();
        }
    }

    /**
     * Получает категорию по идентификатору
     * Возвращает null если категория не найдена
     */
    public Category getCategoryById(Long id) {
        if (id == null || id <= 0) {
            System.out.println("Ошибка: Некорректный ID категории");
            return null;
        }

        try {
            return categoryRepository.findById(id).orElse(null);
        } catch (Exception e) {
            System.out.println("Ошибка при поиске категории: " + e.getMessage());
            return null;
        }
    }
}