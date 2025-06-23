
/* Продолжение задания ИКМ по java */
/**
 * Репозиторий для работы с товарами.
 * Предоставляет методы доступа к данным товаров в базе данных.
 * Расширяет JpaRepository для получения стандартных CRUD операций.
 */
package com.example.TopShop.repositories;

import com.example.TopShop.models.Product;
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
