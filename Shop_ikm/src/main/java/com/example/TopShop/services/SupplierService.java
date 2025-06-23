
/* Продолжение задания ИКМ по java */
/**
 * Сервис для работы с поставщиками товаров.
 * Предоставляет методы для создания, получения и управления поставщиками.
 * 
 * @author Система TopShop
 * @version 1.0
 */
package com.example.TopShop.services;

import com.example.TopShop.models.Supplier;
import com.example.TopShop.repositories.SupplierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SupplierService {
    private final SupplierRepository supplierRepository;

    /**
     * Получает поставщика по названию
     * Используется для поиска существующих поставщиков перед созданием новых
     */
    public Supplier getSupplierByName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return null;
        }
        return supplierRepository.findByName(name.trim());
    }

    /**
     * Сохраняет поставщика в базе данных
     * Выполняет базовую валидацию перед сохранением
     */
    public void saveSupplier(Supplier supplier) {
        if (supplier == null) {
            System.out.println("Ошибка: Попытка сохранить пустого поставщика");
            return;
        }
        
        if (supplier.getName() == null || supplier.getName().trim().isEmpty()) {
            System.out.println("Ошибка: Название поставщика не может быть пустым");
            return;
        }
        
        try {
            supplierRepository.save(supplier);
        } catch (Exception e) {
            System.out.println("Ошибка при сохранении поставщика: " + e.getMessage());
        }
    }

    /**
     * Возвращает список всех поставщиков
     * Используется для отображения в формах выбора
     */
    public List<Supplier> listSuppliers() {
        try {
            return supplierRepository.findAll();
        } catch (Exception e) {
            System.out.println("Ошибка при получении списка поставщиков: " + e.getMessage());
            return List.of();
        }
    }

    /**
     * Получает поставщика по идентификатору
     * Возвращает null если поставщик не найден
     */
    public Supplier getSupplierById(Long id) {
        if (id == null || id <= 0) {
            System.out.println("Ошибка: Некорректный ID поставщика");
            return null;
        }
        
        try {
            return supplierRepository.findById(id).orElse(null);
        } catch (Exception e) {
            System.out.println("Ошибка при поиске поставщика: " + e.getMessage());
            return null;
        }
    }
}
