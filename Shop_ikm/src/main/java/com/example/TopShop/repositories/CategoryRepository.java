
/* Продолжение задания ИКМ по java */
/**
 * Репозиторий для работы с категориями товаров.
 * Предоставляет методы доступа к данным категорий в базе данных.
 * Расширяет JpaRepository для получения стандартных CRUD операций.
 * 
 * @author Система TopShop
 * @version 1.0
 */
package com.example.TopShop.repositories;

import com.example.TopShop.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    
    /**
     * Находит категорию по названию.
     * Используется для поиска существующих категорий перед созданием новых.
     * 
     * @param name название категории для поиска
     * @return найденная категория или null, если категория не найдена
     */
    Category findByName(String name);
}
