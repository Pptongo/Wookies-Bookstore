package com.wookie.bookstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring Boot Main class used to initialize the entire project.
 * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
 * @version 1.0
 * @since 1.0
 */
@SpringBootApplication
public class BookStoreApplication {

	/**
	 * Main method. This is the first method launched to start the full solution.
	 * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
	 * @version 1.0
	 * @since 1.0
	 * @param args An array of arguments passed to the solution.
	 */
	public static void main(String[] args) {
		SpringApplication.run(BookStoreApplication.class, args);
	}

}
