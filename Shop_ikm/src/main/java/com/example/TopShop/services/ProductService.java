/**
 * Сервис для работы с товарами.
 * Предоставляет методы для создания, получения, обновления и удаления товаров.
 * Включает валидацию входных данных и обработку ошибок.
 * 
 */
package com.example.TopShop.services;

import com.example.TopShop.models.Product;
import com.example.TopShop.repositories.ProductRepository;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    /**
     * Возвращает список товаров с возможностью фильтрации по названию.
     * Используется для отображения товаров на главной странице.
     * 
     * @param title название товара для поиска (может быть null)
     * @return список товаров или пустой список при ошибке
     */
    public List<Product> listProducts(String title) {
        try {
            if (title != null && !title.trim().isEmpty()) {
                return productRepository.findByTitle(title.trim());
            }
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

        if (product.getTitle().trim().length() < 1 || product.getTitle().trim().length() > 255) {
            System.out.println("Ошибка: Название товара должно содержать от 1 до 255 символов");
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

        if (product.getCity() == null || product.getCity().trim().isEmpty()) {
            System.out.println("Ошибка: Город не может быть пустым");
            return;
        }

        if (product.getAuthor() == null || product.getAuthor().trim().isEmpty()) {
            System.out.println("Ошибка: Автор не может быть пустым");
            return;
        }

        try {
            productRepository.save(product);
        } catch (Exception e) {
            System.out.println("Ошибка при сохранении товара: " + e.getMessage());
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

    /**
     * Получает товар по идентификатору.
     * Используется для отображения подробной информации о товаре.
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
}