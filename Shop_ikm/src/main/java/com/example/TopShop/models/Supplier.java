
/* Продолжение задания ИКМ по java */
/**
 * Модель поставщика товаров в системе TopShop.
 * Содержит информацию о поставщиках и их контактные данные.
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
@Table(name = "suppliers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Supplier {
    /**
     * Уникальный идентификатор поставщика
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * Название поставщика
     * Обязательное поле для идентификации поставщика
     */
    @NotBlank(message = "Название поставщика не может быть пустым")
    @Size(min = 2, max = 100, message = "Название поставщика должно содержать от 2 до 100 символов")
    private String name;

    /**
     * Контактная информация поставщика
     * Телефон, email или другие способы связи
     */
    @Size(max = 200, message = "Контактная информация не может превышать 200 символов")
    private String contact;

    /**
     * Список товаров от данного поставщика
     * Связь один ко многим с таблицей товаров
     */
    @OneToMany(mappedBy = "supplier")
    private List<Product> products;
}
