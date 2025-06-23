
/* Продолжение задания ИКМ по java */
/**
 * Модель товара в системе TopShop.
 * Содержит основную информацию о товаре, его категории и поставщике.
 * 
 * @author Система TopShop
 * @version 1.0
 */
package com.example.TopShop.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "products")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    /**
     * Уникальный идентификатор товара
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    /**
     * Название товара
     * Обязательное поле, не может быть пустым
     */
    @Column(name = "title")
    @NotBlank(message = "Название товара не может быть пустым")
    @Size(min = 2, max = 100, message = "Название должно содержать от 2 до 100 символов")
    private String title;

    /**
     * Описание товара
     * Дополнительная информация о товаре
     */
    @Column(name = "description", columnDefinition = "text")
    @Size(max = 1000, message = "Описание не может превышать 1000 символов")
    private String description;

    /**
     * Цена товара в копейках
     * Должна быть положительным числом
     */
    @Column(name = "price")
    @NotNull(message = "Цена товара обязательна")
    @Min(value = 1, message = "Цена должна быть больше 0")
    @Max(value = 999999999, message = "Цена не может превышать 999999999")
    private Integer price;

    /**
     * Город, где находится товар
     */
    @Column(name = "city")
    @NotBlank(message = "Город не может быть пустым")
    @Size(min = 2, max = 50, message = "Название города должно содержать от 2 до 50 символов")
    private String city;

    /**
     * Автор/владелец товара
     */
    @Column(name = "author")
    @NotBlank(message = "Автор не может быть пустым")
    @Size(min = 2, max = 50, message = "Имя автора должно содержать от 2 до 50 символов")
    private String author;

    /**
     * Категория товара
     * Связь многие к одному с таблицей категорий
     */
    @ManyToOne
    @JoinColumn(name = "category_id")
    @NotNull(message = "Категория товара обязательна")
    private Category category;

    /**
     * Поставщик товара
     * Связь многие к одному с таблицей поставщиков
     */
    @ManyToOne
    @JoinColumn(name = "supplier_id")
    @NotNull(message = "Поставщик товара обязателен")
    private Supplier supplier;
}
