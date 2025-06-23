package com.example.TopShop.services;

import com.example.TopShop.models.Product;
import com.example.TopShop.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> listProducts(String title) {
        if (title != null) return productRepository.findByTitle(title);
        return productRepository.findAll();
    }


    public void saveProduct(Product product) {
        log.info("Saving new {}", product);
        productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }
}
/* Продолжение задания ИКМ по java */
/**
 * Сервис для работы с товарами.
 * Предоставляет методы для создания, получения, обновления и удаления товаров.
 * 
 * @author Система TopShop
 * @version 1.0
 */
package com.example.TopShop.services;

import com.example.TopShop.models.Product;
import com.example.TopShop.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    /**
     * Получает товар по идентификатору
     * Возвращает null если товар не найден
     */
    public Product getProductById(Long id) {
        if (id == null || id <= 0) {
            System.out.println("Ошибка: Некорректный ID товара");
            return null;
        }
        
        try {
            return productRepository.findById(id).orElse(null);
        } catch (Exception e) {
            System.out.println("Ошибка при поиске товара: " + e.getMessage());
            return null;
        }
    }

    /**
     * Сохраняет товар в базе данных
     * Выполняет валидацию перед сохранением
     */
    public void saveProduct(Product product) {
        if (product == null) {
            System.out.println("Ошибка: Попытка сохранить пустой товар");
            return;
        }
        
        if (product.getTitle() == null || product.getTitle().trim().isEmpty()) {
            System.out.println("Ошибка: Название товара не может быть пустым");
            return;
        }
        
        if (product.getPrice() == null || product.getPrice() < 0) {
            System.out.println("Ошибка: Цена товара должна быть положительной");
            return;
        }
        
        try {
            productRepository.save(product);
        } catch (Exception e) {
            System.out.println("Ошибка при сохранении товара: " + e.getMessage());
        }
    }

    /**
     * Возвращает список всех товаров
     * Используется для отображения на главной странице
     */
    public List<Product> listProducts() {
        try {
            return productRepository.findAll();
        } catch (Exception e) {
            System.out.println("Ошибка при получении списка товаров: " + e.getMessage());
            return List.of();
        }
    }

    /**
     * Удаляет товар по идентификатору
     * Проверяет существование товара перед удалением
     */
    public void deleteProduct(Long id) {
        if (id == null || id <= 0) {
            System.out.println("Ошибка: Некорректный ID товара для удаления");
            return;
        }
        
        try {
            if (productRepository.existsById(id)) {
                productRepository.deleteById(id);
            } else {
                System.out.println("Ошибка: Товар с ID " + id + " не найден");
            }
        } catch (Exception e) {
            System.out.println("Ошибка при удалении товара: " + e.getMessage());
        }
    }

    /**
     * Находит товары по названию
     * Возвращает список товаров с указанным названием
     */
    public List<Product> findProductsByTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            System.out.println("Ошибка: Название для поиска не может быть пустым");
            return List.of();
        }
        
        try {
            return productRepository.findByTitle(title.trim());
        } catch (Exception e) {
            System.out.println("Ошибка при поиске товаров: " + e.getMessage());
            return List.of();
        }
    }
}
