
/* Продолжение задания ИКМ по java */
/**
 * Репозиторий для работы с товарами в базе данных.
 * Предоставляет стандартные CRUD операции и кастомные методы поиска.
 * 
 * @author Система TopShop
 * @version 1.0
 */
package com.example.TopShop.repositories;

import com.example.TopShop.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    
    /**
     * Находит товары по названию
     * Возвращает список товаров с указанным названием
     */
    List<Product> findByTitle(String title);
}
