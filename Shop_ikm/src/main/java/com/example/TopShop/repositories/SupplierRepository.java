
/* Продолжение задания ИКМ по java */
/**
 * Репозиторий для работы с поставщиками в базе данных.
 * Предоставляет стандартные CRUD операции и кастомные методы поиска.
 * 
 * @author Система TopShop
 * @version 1.0
 */
package com.example.TopShop.repositories;

import com.example.TopShop.models.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    
    /**
     * Находит поставщика по точному названию
     * Используется для проверки существования поставщика перед созданием
     */
    Supplier findByName(String name);
}
