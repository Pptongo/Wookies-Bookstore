package com.wookie.bookstore;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import java.util.List;

import com.wookie.bookstore.persistance.entities.BookEntity;
import com.wookie.bookstore.persistance.repository.BookRepository;
import com.wookie.bookstore.service.BookService;
import com.wookie.bookstore.service.impl.AuthServiceImpl;
import com.wookie.bookstore.shared.JwtUtil;

@SpringBootTest
@AutoConfigureMockMvc
class BookStoreApplicationTests {
	
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private BookService bookService;

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private AuthServiceImpl authService;
	
	@Autowired
	private JwtUtil jwtUtil;

	@Test
	public void testAuthService() throws Exception {
		String request = "{\"username\": \"admin\", \"password\": \"anJ?FS6w\"}";

		this.mockMvc.perform(post("/api/1.0/auth").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(request))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
	}

	@Test
	public void testBadAuthService() throws Exception {
		String request = "{\"username\": \"admin\", \"password\": \"nonpassword\"}";

		this.mockMvc.perform(post("/api/1.0/auth").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(request))
			.andDo(print())
			.andExpect(status().isUnauthorized())
			.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
	}

	@Test
	public void testPublicBookList() throws Exception {
		List<BookEntity> books = bookService.getAll();
		assertNotNull(books);
		assertTrue(books.size() > 0);

		this.mockMvc.perform(get("/api/1.0/books/"))
			.andDo(print())
			.andExpect(status().isOk());
	}

	@Test
	public void testPublishBook() throws Exception {
		List<BookEntity> books = (List<BookEntity>) bookRepository.findAll();

		String request = "{\"title\": \"My second book\", \"description\": \"This is my second published book\", \"coverImage\": \"https://cdn.shopify.com/s/files/1/0748/0217/products/ewok-no-evil_skaggs.jpg?v=1553735580\", \"price\": \"30.99\"}";
		String jwt = getJwt("admin");

		assertTrue(!jwt.isEmpty());
		
		this.mockMvc.perform(post("/api/1.0/books/book").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).header("Authorization", "Bearer " + jwt).content(request))
			.andDo(print())
			.andExpect(status().isOk());

		List<BookEntity> newBooksList = (List<BookEntity>) bookRepository.findAll();

		assertTrue(books.size() < newBooksList.size());
	}

	@Test
	public void testUpdateBook() throws Exception {
		String username = "admin";
		String newTitle = "My new title";
		String request = "{\"title\": \"" + newTitle + "\", \"description\": \"This is my second published book\", \"coverImage\": \"https://cdn.shopify.com/s/files/1/0748/0217/products/ewok-no-evil_skaggs.jpg?v=1553735580\", \"price\": \"30.99\"}";
		String jwt = getJwt("admin");
		
		List<BookEntity> books = (List<BookEntity>) bookRepository.findAll();
		BookEntity book = books != null ? books.stream().filter(b -> b.getAuthor().getUsername().equals(username)).findFirst().orElse(null) : null;
		
		assertNotNull(book);
		assertTrue(!jwt.isEmpty());
		
		this.mockMvc.perform(put("/api/1.0/books/book/" + book.getId()).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).header("Authorization", "Bearer " + jwt).content(request))
			.andDo(print())
			.andExpect(status().isOk());
		
		BookEntity updatedBook = bookRepository.findById(book.getId()).orElse(null);

		assertNotNull(updatedBook);
	}

	@Test
	public void testDeleteBook() throws Exception {
		String username = "admin";

		List<BookEntity> books = (List<BookEntity>) bookRepository.findAll();
		long bookId = books != null ? books.stream().filter(book -> book.getAuthor().getUsername().equals(username)).findFirst().orElse(new BookEntity()).getId() : 0;

		String jwt = getJwt(username);

		assertTrue(!jwt.isEmpty());
		assertNotNull(books);
		assertTrue(bookId > 0);

		this.mockMvc.perform(delete("/api/1.0/books/book/" + bookId).header("Authorization", "Bearer " + jwt))
			.andDo(print())
			.andExpect(status().isOk());
	}

	private String getJwt(String username) {
		final UserDetails userDetails = authService.loadUserByUsername(username);
        return userDetails != null ? jwtUtil.generateToken(userDetails) : "";
	}

}
