
/* Продолжение задания ИКМ по java */
/**
 * Контроллер для управления товарами в системе TopShop.
 * Обрабатывает HTTP-запросы для создания, чтения, обновления и удаления товаров.
 * Включает валидацию входных данных и обработку ошибок.
 */
package com.example.TopShop.controllers;

import com.example.TopShop.models.Category;
import com.example.TopShop.models.Product;
import com.example.TopShop.models.Supplier;
import com.example.TopShop.services.CategoryService;
import com.example.TopShop.services.ProductService;
import com.example.TopShop.services.SupplierService;
import jakarta.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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

    /**
     * Отображает главную страницу с списком товаров
     * Поддерживает поиск по названию товара
     */
    @GetMapping("/")
    public String products(@RequestParam(name = "title", required = false) String title, Model model) {
        if (title != null && title.trim().isEmpty()) {
            title = null;
        }
        
        model.addAttribute("products", productService.listProducts(title));
        model.addAttribute("categories", categoryService.listCategories());
        model.addAttribute("suppliers", supplierService.listSuppliers());
        return "products";
    }

    /**
     * Отображает подробную информацию о конкретном товаре
     */
    @GetMapping("/product/{id}")
    public String productInfo(@PathVariable Long id, Model model) {
        if (id == null || id <= 0) {
            System.out.println("Ошибка: Некорректный ID товара");
            return "redirect:/";
        }
        
        Product product = productService.getProductById(id);
        if (product == null) {
            System.out.println("Ошибка: Товар с ID " + id + " не найден");
            return "redirect:/";
        }
        
        model.addAttribute("product", product);
        return "product-info";
    }

    /**
     * Создает новый товар с валидацией входных данных
     * Автоматически создает категорию и поставщика, если они не существуют
     */
    @PostMapping("/product/create")
    public String createProduct(@Valid Product product,
                                BindingResult bindingResult,
                                @RequestParam String categoryName,
                                @RequestParam String supplierName,
                                Model model) {
        
        if (categoryName == null || categoryName.trim().isEmpty()) {
            System.out.println("Ошибка: Название категории не может быть пустым");
            return "redirect:/";
        }
        
        if (supplierName == null || supplierName.trim().isEmpty()) {
            System.out.println("Ошибка: Название поставщика не может быть пустым");
            return "redirect:/";
        }

        if (bindingResult.hasErrors()) {
            System.out.println("Ошибка валидации товара: " + bindingResult.getAllErrors());
            model.addAttribute("products", productService.listProducts(null));
            model.addAttribute("categories", categoryService.listCategories());
            model.addAttribute("suppliers", supplierService.listSuppliers());
            return "products";
        }

        try {
            Category category = categoryService.getCategoryByName(categoryName.trim());
            if (category == null) {
                category = new Category();
                category.setName(categoryName.trim());
                categoryService.saveCategory(category);
            }

            Supplier supplier = supplierService.getSupplierByName(supplierName.trim());
            if (supplier == null) {
                supplier = new Supplier();
                supplier.setName(supplierName.trim());
                supplierService.saveSupplier(supplier);
            }

            product.setCategory(category);
            product.setSupplier(supplier);
            productService.saveProduct(product);
            
        } catch (Exception e) {
            System.out.println("Ошибка при создании товара: " + e.getMessage());
            return "redirect:/";
        }

        return "redirect:/";
    }

    /**
     * Удаляет товар по его идентификатору
     */
    @PostMapping("/product/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        if (id == null || id <= 0) {
            System.out.println("Ошибка: Некорректный ID товара для удаления");
            return "redirect:/";
        }
        
        try {
            productService.deleteProduct(id);
        } catch (Exception e) {
            System.out.println("Ошибка при удалении товара: " + e.getMessage());
        }
        
        return "redirect:/";
    }

    /**
     * Отображает страницу редактирования товара
     */
    @GetMapping("/product/edit/{id}")
    public String editProduct(@PathVariable Long id, Model model) {
        if (id == null || id <= 0) {
            System.out.println("Ошибка: Некорректный ID товара для редактирования");
            return "redirect:/";
        }
        
        Product product = productService.getProductById(id);
        if (product == null) {
            System.out.println("Ошибка: Товар с ID " + id + " не найден для редактирования");
            return "redirect:/";
        }
        
        model.addAttribute("product", product);
        model.addAttribute("categories", categoryService.listCategories());
        model.addAttribute("suppliers", supplierService.listSuppliers());
        return "product-edit";
    }

    /**
     * Обновляет существующий товар с валидацией
     */
    @PostMapping("/product/edit/{id}")
    public String updateProduct(@PathVariable Long id, 
                                @Valid Product product, 
                                BindingResult bindingResult,
                                @RequestParam Long categoryId, 
                                @RequestParam Long supplierId,
                                Model model) {
        
        if (id == null || id <= 0) {
            System.out.println("Ошибка: Некорректный ID товара для обновления");
            return "redirect:/";
        }
        
        if (categoryId == null || categoryId <= 0) {
            System.out.println("Ошибка: Некорректный ID категории");
            return "redirect:/";
        }
        
        if (supplierId == null || supplierId <= 0) {
            System.out.println("Ошибка: Некорректный ID поставщика");
            return "redirect:/";
        }
        
        if (bindingResult.hasErrors()) {
            System.out.println("Ошибка валидации при обновлении товара: " + bindingResult.getAllErrors());
            model.addAttribute("product", product);
            model.addAttribute("categories", categoryService.listCategories());
            model.addAttribute("suppliers", supplierService.listSuppliers());
            return "product-edit";
        }
        
        try {
            Category category = categoryService.getCategoryById(categoryId);
            Supplier supplier = supplierService.getSupplierById(supplierId);
            
            if (category == null) {
                System.out.println("Ошибка: Категория с ID " + categoryId + " не найдена");
                return "redirect:/";
            }
            
            if (supplier == null) {
                System.out.println("Ошибка: Поставщик с ID " + supplierId + " не найден");
                return "redirect:/";
            }
            
            product.setCategory(category);
            product.setSupplier(supplier);
            product.setId(id);
            productService.saveProduct(product);
            
        } catch (Exception e) {
            System.out.println("Ошибка при обновлении товара: " + e.getMessage());
            return "redirect:/";
        }
        
        return "redirect:/";
    }

    /**
     * Создает новую категорию
     */
    @PostMapping("/category/create")
    public String createCategory(@RequestParam String category) {
        if (category == null || category.trim().isEmpty()) {
            System.out.println("Ошибка: Название категории не может быть пустым");
            return "redirect:/";
        }
        
        if (category.trim().length() < 2 || category.trim().length() > 50) {
            System.out.println("Ошибка: Название категории должно содержать от 2 до 50 символов");
            return "redirect:/";
        }
        
        try {
            Category newCategory = new Category();
            newCategory.setName(category.trim());
            categoryService.saveCategory(newCategory);
        } catch (Exception e) {
            System.out.println("Ошибка при создании категории: " + e.getMessage());
        }
        
        return "redirect:/";
    }
}
