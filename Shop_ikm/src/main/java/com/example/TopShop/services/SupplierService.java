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

    public Supplier getSupplierByName(String name) {
        return supplierRepository.findByName(name); // Assuming you have a method in your repository
    }

    public void saveSupplier(Supplier supplier) {
        supplierRepository.save(supplier); // Save the supplier to the database
    }

    public List<Supplier> listSuppliers() {
        return supplierRepository.findAll();
    }

    public Supplier getSupplierById(Long id) {
        return supplierRepository.findById(id).orElse(null);
    }
}
