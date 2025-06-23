/* Продолжение задания ИКМ по java */
/**
 * Главный класс приложения TopShop.
 * Запускает Spring Boot приложение и настраивает все необходимые компоненты.
 */
package com.example.TopShop;

import org.springframework.boot.SpringApplication;


@SpringBootApplication
public class TopShopApplication {

	/**
	 * Точка входа в приложение.
	 * Запускает Spring Boot приложение.
	 *
	 * @param args аргументы командной строки
	 */
	public static void main(String[] args) {
		SpringApplication.run(TopShopApplication.class, args);
	}

}
