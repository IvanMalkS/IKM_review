
/* Продолжение задания ИКМ по java */
/**
 * Репозиторий для работы с категориями в базе данных.
 * Предоставляет стандартные CRUD операции и кастомные методы поиска.
 * 
 * @author Система TopShop
 * @version 1.0
 */
package com.example.TopShop.repositories;

import com.example.TopShop.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    
    /**
     * Находит категорию по точному названию
     * Используется для проверки существования категории перед созданием
     */
    Category findByName(String name);
}
