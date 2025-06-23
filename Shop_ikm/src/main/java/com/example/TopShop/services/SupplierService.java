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
     * Получает поставщика по названию.
     * Используется для поиска существующих поставщиков перед созданием новых.
     * 
     * @param name название поставщика для поиска
     * @return найденный поставщик или null при ошибке/отсутствии
     */
    public Supplier getSupplierByName(String name) {
        if (name == null || name.trim().isEmpty()) {
            System.out.println("Ошибка: Название поставщика не может быть пустым");
            return null;
        }

        if (name.trim().length() < 2 || name.trim().length() > 100) {
            System.out.println("Ошибка: Название поставщика должно содержать от 2 до 100 символов");
            return null;
        }

        try {
            return supplierRepository.findByName(name.trim());
        } catch (Exception e) {
            System.out.println("Ошибка при поиске поставщика по названию: " + e.getMessage());
            return null;
        }
    }

    /**
     * Сохраняет поставщика в базе данных.
     * Выполняет полную валидацию перед сохранением.
     * 
     * @param supplier объект поставщика для сохранения
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

        if (supplier.getName().trim().length() < 2 || supplier.getName().trim().length() > 100) {
            System.out.println("Ошибка: Название поставщика должно содержать от 2 до 100 символов");
            return;
        }

        if (supplier.getContact() != null && supplier.getContact().length() > 255) {
            System.out.println("Ошибка: Контактная информация не может превышать 255 символов");
            return;
        }

        // Проверяем уникальность названия поставщика
        Supplier existingSupplier = getSupplierByName(supplier.getName());
        if (existingSupplier != null && !existingSupplier.getId().equals(supplier.getId())) {
            System.out.println("Ошибка: Поставщик с таким названием уже существует");
            return;
        }

        try {
            supplierRepository.save(supplier);
        } catch (Exception e) {
            System.out.println("Ошибка при сохранении поставщика: " + e.getMessage());
        }
    }

    /**
     * Возвращает список всех поставщиков.
     * Используется для отображения в формах выбора.
     * 
     * @return список всех поставщиков или пустой список при ошибке
     */
    public List<Supplier> listSuppliers() {
        try {
            List<Supplier> suppliers = supplierRepository.findAll();
            if (suppliers == null) {
                System.out.println("Предупреждение: Получен null вместо списка поставщиков");
                return List.of();
            }
            return suppliers;
        } catch (Exception e) {
            System.out.println("Ошибка при получении списка поставщиков: " + e.getMessage());
            return List.of();
        }
    }

    /**
     * Получает поставщика по идентификатору.
     * Используется при редактировании товаров.
     * 
     * @param id идентификатор поставщика
     * @return найденный поставщик или null при ошибке/отсутствии
     */
    public Supplier getSupplierById(Long id) {
        if (id == null) {
            System.out.println("Ошибка: ID поставщика не может быть null");
            return null;
        }

        if (id <= 0) {
            System.out.println("Ошибка: ID поставщика должен быть положительным числом");
            return null;
        }

        try {
            return supplierRepository.findById(id).orElse(null);
        } catch (Exception e) {
            System.out.println("Ошибка при получении поставщика по ID: " + e.getMessage());
            return null;
        }
    }

    /**
     * Удаляет поставщика по идентификатору.
     * Проверяет возможность удаления перед выполнением операции.
     * 
     * @param id идентификатор поставщика для удаления
     */
    public void deleteSupplier(Long id) {
        if (id == null) {
            System.out.println("Ошибка: ID поставщика не может быть null");
            return;
        }

        if (id <= 0) {
            System.out.println("Ошибка: ID поставщика должен быть положительным числом");
            return;
        }

        try {
            Supplier supplier = getSupplierById(id);
            if (supplier == null) {
                System.out.println("Ошибка: Поставщик с ID " + id + " не найден");
                return;
            }

            supplierRepository.deleteById(id);
        } catch (Exception e) {
            System.out.println("Ошибка при удалении поставщика: " + e.getMessage());
        }
    }
}