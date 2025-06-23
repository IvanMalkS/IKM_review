
/* Продолжение задания ИКМ по java */
/**
 * Репозиторий для работы с поставщиками товаров.
 * Предоставляет методы доступа к данным поставщиков в базе данных.
 * Расширяет JpaRepository для получения стандартных CRUD операций.
 * 
 * @author Система TopShop
 * @version 1.0
 */
package com.example.TopShop.repositories;

import com.example.TopShop.models.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    
    /**
     * Находит поставщика по названию.
     * Используется для поиска существующих поставщиков перед созданием новых.
     * 
     * @param name название поставщика для поиска
     * @return найденный поставщик или null, если поставщик не найден
     */
    Supplier findByName(String name);
}
