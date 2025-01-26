package com.example.TopShop.controllers;

import com.example.TopShop.models.Category;
import com.example.TopShop.models.Product;
import com.example.TopShop.models.Supplier;
import com.example.TopShop.services.CategoryService;
import com.example.TopShop.services.ProductService;
import com.example.TopShop.services.SupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;
    private final SupplierService supplierService;

    // Главная страница с товарами
    @GetMapping("/")
    public String products(@RequestParam(name = "title", required = false) String title, Model model) {
        model.addAttribute("products", productService.listProducts(title));
        model.addAttribute("categories", categoryService.listCategories());  // Передаем список категорий
        model.addAttribute("suppliers", supplierService.listSuppliers());  // Передаем список поставщиков
        return "products";
    }

    // Страница с подробной информацией о товаре
    @GetMapping("/product/{id}")
    public String productInfo(@PathVariable Long id, Model model) {
        model.addAttribute("product", productService.getProductById(id));
        return "product-info";
    }

    // Создание нового товара
    @PostMapping("/product/create")
    public String createProduct(Product product,
                                @RequestParam String categoryName,
                                @RequestParam String supplierName) {
        // Находим или создаем категорию
        Category category = categoryService.getCategoryByName(categoryName);
        if (category == null) {
            category = new Category();
            category.setName(categoryName);
            categoryService.saveCategory(category);
        }

        // Находим или создаем поставщика
        Supplier supplier = supplierService.getSupplierByName(supplierName);
        if (supplier == null) {
            supplier = new Supplier();
            supplier.setName(supplierName);
            supplierService.saveSupplier(supplier);
        }

        // Устанавливаем категорию и поставщика для товара
        product.setCategory(category);
        product.setSupplier(supplier);

        // Сохраняем товар
        productService.saveProduct(product);
        return "redirect:/"; // Перенаправляем на главную страницу после создания товара
    }

    // Удаление товара
    @PostMapping("/product/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:/";
    }

    // Страница для редактирования товара
    @GetMapping("/product/edit/{id}")
    public String editProduct(@PathVariable Long id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        model.addAttribute("categories", categoryService.listCategories());  // Список категорий для редактирования
        model.addAttribute("suppliers", supplierService.listSuppliers());  // Список поставщиков для редактирования
        return "product-edit";
    }

    // Обновление товара
    @PostMapping("/product/edit/{id}")
    public String updateProduct(@PathVariable Long id, Product product, @RequestParam Long categoryId, @RequestParam Long supplierId) {
        Category category = categoryService.getCategoryById(categoryId);
        Supplier supplier = supplierService.getSupplierById(supplierId);
        product.setCategory(category);
        product.setSupplier(supplier);
        product.setId(id);
        productService.saveProduct(product);
        return "redirect:/";
    }

    // Добавление новой категории
    @PostMapping("/category/create")
    public String createCategory(@RequestParam String category, Model model) {
        Category newCategory = new Category();
        newCategory.setName(category);
        categoryService.saveCategory(newCategory);
        return "redirect:/"; // Перенаправляем на главную страницу после добавления категории
    }

    // Добавление нового поставщика
    @PostMapping("/supplier/create")
    public String createSupplier(@RequestParam String supplier, Model model) {
        Supplier newSupplier = new Supplier();
        newSupplier.setName(supplier);
        supplierService.saveSupplier(newSupplier);
        return "redirect:/"; // Перенаправляем на главную страницу после добавления поставщика
    }
}
