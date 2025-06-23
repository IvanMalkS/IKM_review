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
 * Включает валидацию входных данных и обработку ошибок.
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
     * Получает товар по идентификатору.
     * Используется для отображения информации о товаре и редактирования.
     * 
     * @param id идентификатор товара
     * @return найденный товар или null при ошибке/отсутствии
     */
    public Product getProductById(Long id) {
        if (id == null) {
            System.out.println("Ошибка: ID товара не может быть null");
            return null;
        }
        
        if (id <= 0) {
            System.out.println("Ошибка: ID товара должен быть положительным числом");
            return null;
        }
        
        try {
            return productRepository.findById(id).orElse(null);
        } catch (Exception e) {
            System.out.println("Ошибка при получении товара по ID: " + e.getMessage());
            return null;
        }
    }

    /**
     * Получает товары по названию.
     * Используется для поиска товаров с конкретным названием.
     * 
     * @param title название товара для поиска
     * @return список найденных товаров или пустой список при ошибке
     */
    public List<Product> getProductsByTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            System.out.println("Ошибка: Название товара не может быть пустым");
            return List.of();
        }
        
        if (title.trim().length() < 2 || title.trim().length() > 100) {
            System.out.println("Ошибка: Название товара должно содержать от 2 до 100 символов");
            return List.of();
        }
        
        try {
            List<Product> products = productRepository.findByTitle(title.trim());
            if (products == null) {
                System.out.println("Предупреждение: Получен null вместо списка товаров");
                return List.of();
            }
            return products;
        } catch (Exception e) {
            System.out.println("Ошибка при поиске товаров по названию: " + e.getMessage());
            return List.of();
        }
    }

    /**
     * Сохраняет товар в базе данных.
     * Выполняет полную валидацию перед сохранением.
     * 
     * @param product объект товара для сохранения
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
        
        if (product.getTitle().trim().length() < 2 || product.getTitle().trim().length() > 100) {
            System.out.println("Ошибка: Название товара должно содержать от 2 до 100 символов");
            return;
        }
        
        if (product.getPrice() == null || product.getPrice() < 1) {
            System.out.println("Ошибка: Цена товара должна быть положительным числом");
            return;
        }
        
        if (product.getDescription() != null && product.getDescription().length() > 1000) {
            System.out.println("Ошибка: Описание товара не может превышать 1000 символов");
            return;
        }
        
        if (product.getCategory() == null) {
            System.out.println("Ошибка: Товар должен иметь категорию");
            return;
        }
        
        if (product.getSupplier() == null) {
            System.out.println("Ошибка: Товар должен иметь поставщика");
            return;
        }
        
        try {
            productRepository.save(product);
        } catch (Exception e) {
            System.out.println("Ошибка при сохранении товара: " + e.getMessage());
        }
    }

    /**
     * Возвращает список всех товаров.
     * Используется для отображения каталога товаров.
     * 
     * @return список всех товаров или пустой список при ошибке
     */
    public List<Product> listProducts() {
        try {
            List<Product> products = productRepository.findAll();
            if (products == null) {
                System.out.println("Предупреждение: Получен null вместо списка товаров");
                return List.of();
            }
            return products;
        } catch (Exception e) {
            System.out.println("Ошибка при получении списка товаров: " + e.getMessage());
            return List.of();
        }
    }

    /**
     * Удаляет товар по идентификатору.
     * Проверяет существование товара перед удалением.
     * 
     * @param id идентификатор товара для удаления
     */
    public void deleteProduct(Long id) {
        if (id == null) {
            System.out.println("Ошибка: ID товара не может быть null");
            return;
        }
        
        if (id <= 0) {
            System.out.println("Ошибка: ID товара должен быть положительным числом");
            return;
        }
        
        try {
            Product product = getProductById(id);
            if (product == null) {
                System.out.println("Ошибка: Товар с ID " + id + " не найден");
                return;
            }
            
            productRepository.deleteById(id);
        } catch (Exception e) {
            System.out.println("Ошибка при удалении товара: " + e.getMessage());
        }
    }
}
