
/* Продолжение задания ИКМ по java */
/**
 * Сервис для работы с категориями товаров.
 * Предоставляет методы для создания, получения и управления категориями.
 * Включает валидацию входных данных и обработку ошибок.
 * 
 * @author Система TopShop
 * @version 1.0
 */
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
     * Получает категорию по названию.
     * Используется для поиска существующих категорий перед созданием новых.
     * 
     * @param name название категории для поиска
     * @return найденная категория или null при ошибке/отсутствии
     */
    public Category getCategoryByName(String name) {
        if (name == null || name.trim().isEmpty()) {
            System.out.println("Ошибка: Название категории не может быть пустым");
            return null;
        }
        
        if (name.trim().length() < 2 || name.trim().length() > 50) {
            System.out.println("Ошибка: Название категории должно содержать от 2 до 50 символов");
            return null;
        }
        
        try {
            return categoryRepository.findByName(name.trim());
        } catch (Exception e) {
            System.out.println("Ошибка при поиске категории по названию: " + e.getMessage());
            return null;
        }
    }

    /**
     * Сохраняет категорию в базе данных.
     * Выполняет полную валидацию перед сохранением.
     * 
     * @param category объект категории для сохранения
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
        
        if (category.getName().trim().length() < 2 || category.getName().trim().length() > 50) {
            System.out.println("Ошибка: Название категории должно содержать от 2 до 50 символов");
            return;
        }
        
        // Проверяем уникальность названия категории
        Category existingCategory = getCategoryByName(category.getName());
        if (existingCategory != null && !existingCategory.getId().equals(category.getId())) {
            System.out.println("Ошибка: Категория с таким названием уже существует");
            return;
        }
        
        try {
            categoryRepository.save(category);
        } catch (Exception e) {
            System.out.println("Ошибка при сохранении категории: " + e.getMessage());
        }
    }

    /**
     * Возвращает список всех категорий.
     * Используется для отображения в формах выбора.
     * 
     * @return список всех категорий или пустой список при ошибке
     */
    public List<Category> listCategories() {
        try {
            List<Category> categories = categoryRepository.findAll();
            if (categories == null) {
                System.out.println("Предупреждение: Получен null вместо списка категорий");
                return List.of();
            }
            return categories;
        } catch (Exception e) {
            System.out.println("Ошибка при получении списка категорий: " + e.getMessage());
            return List.of();
        }
    }

    /**
     * Получает категорию по идентификатору.
     * Используется при редактировании товаров.
     * 
     * @param id идентификатор категории
     * @return найденная категория или null при ошибке/отсутствии
     */
    public Category getCategoryById(Long id) {
        if (id == null) {
            System.out.println("Ошибка: ID категории не может быть null");
            return null;
        }
        
        if (id <= 0) {
            System.out.println("Ошибка: ID категории должен быть положительным числом");
            return null;
        }
        
        try {
            return categoryRepository.findById(id).orElse(null);
        } catch (Exception e) {
            System.out.println("Ошибка при получении категории по ID: " + e.getMessage());
            return null;
        }
    }

    /**
     * Удаляет категорию по идентификатору.
     * Проверяет возможность удаления перед выполнением операции.
     * 
     * @param id идентификатор категории для удаления
     */
    public void deleteCategory(Long id) {
        if (id == null) {
            System.out.println("Ошибка: ID категории не может быть null");
            return;
        }
        
        if (id <= 0) {
            System.out.println("Ошибка: ID категории должен быть положительным числом");
            return;
        }
        
        try {
            Category category = getCategoryById(id);
            if (category == null) {
                System.out.println("Ошибка: Категория с ID " + id + " не найдена");
                return;
            }
            
            categoryRepository.deleteById(id);
        } catch (Exception e) {
            System.out.println("Ошибка при удалении категории: " + e.getMessage());
        }
    }
}
