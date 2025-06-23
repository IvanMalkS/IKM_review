
/* Продолжение задания ИКМ по java */
/**
 * Главный класс приложения TopShop.
 * Точка входа в Spring Boot приложение для управления товарами.
 * 
 * @author Система TopShop
 * @version 1.0
 */
package com.example.TopShop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TopShopApplication {

    /**
     * Главный метод запуска приложения
     * Инициализирует Spring Boot контекст и запускает веб-сервер
     */
    public static void main(String[] args) {
        SpringApplication.run(TopShopApplication.class, args);
    }

}
