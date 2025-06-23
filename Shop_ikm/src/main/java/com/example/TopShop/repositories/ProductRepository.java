
/* Продолжение задания ИКМ по java */
/**
 * Репозиторий для работы с товарами.
 * Предоставляет методы доступа к данным товаров в базе данных.
 * Расширяет JpaRepository для получения стандартных CRUD операций.
 * 
 * @author Система TopShop
 * @version 1.0
 */
package com.example.TopShop.repositories;

import com.example.TopShop.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    
    /**
     * Находит товары по названию.
     * Используется для поиска товаров с конкретным названием.
     * 
     * @param title название товара для поиска
     * @return список найденных товаров
     */
    List<Product> findByTitle(String title);
}
