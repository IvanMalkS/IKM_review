
/* Продолжение задания ИКМ по java */
/**
 * Модель категории товаров в системе TopShop.
 * Используется для группировки товаров по типам.
 * 
 * @author Система TopShop
 * @version 1.0
 */
package com.example.TopShop.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "categories")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    /**
     * Уникальный идентификатор категории
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * Название категории
     * Обязательное поле для идентификации категории
     */
    @NotBlank(message = "Название категории не может быть пустым")
    @Size(min = 2, max = 50, message = "Название категории должно содержать от 2 до 50 символов")
    private String name;

    /**
     * Список товаров в данной категории
     * Связь один ко многим с таблицей товаров
     */
    @OneToMany(mappedBy = "category")
    private List<Product> products;
}
