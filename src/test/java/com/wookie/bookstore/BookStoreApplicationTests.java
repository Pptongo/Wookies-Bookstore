package com.wookie.bookstore;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wookie.bookstore.models.BookModel;
import com.wookie.bookstore.persistance.entities.BookEntity;
import com.wookie.bookstore.persistance.repository.BookRepository;
import com.wookie.bookstore.requests.CredentialsRequest;
import com.wookie.bookstore.requests.PublishBookRequest;
import com.wookie.bookstore.response.ApiResponse;
import com.wookie.bookstore.response.BooksResponse;
import com.wookie.bookstore.service.impl.AuthServiceImpl;
import com.wookie.bookstore.shared.JwtUtil;

@SpringBootTest
@AutoConfigureMockMvc
class BookStoreApplicationTests {
	
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private AuthServiceImpl authService;
	
	@Autowired
	private JwtUtil jwtUtil;

	@Test
	public void testAuthService() throws Exception {
		String username = "admin";
		CredentialsRequest req = new CredentialsRequest(username, "anJ?FS6w");

		ResultActions resultActions = this.mockMvc.perform(post("/api/1.0/auth").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(parseObjToJson(req)))
			.andExpect(status().isOk())
			.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

		ApiResponse apiResponse = getApiResponseFromResultActions(resultActions);

		assertNotNull(apiResponse);
		assertTrue(apiResponse.getStatus());
		assertNull(apiResponse.getMessage());
		assertNotNull(apiResponse.getBody());

		String jwt = (String) apiResponse.getBody();

		assertTrue(jwtUtil.validateToken(jwt, getUserDetails(username)));
	}

	@Test
	public void testBadAuthService() throws Exception {
		String username = "admin";
		CredentialsRequest req = new CredentialsRequest(username, "");

		ResultActions resultActions = this.mockMvc.perform(post("/api/1.0/auth").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(parseObjToJson(req)))
			.andExpect(status().isUnauthorized())
			.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

		ApiResponse apiResponse = getApiResponseFromResultActions(resultActions);

		assertNotNull(apiResponse);
		assertFalse(apiResponse.getStatus());
		assertNotNull(apiResponse.getMessage());
		assertNull(apiResponse.getBody());
	}

	@Test
	public void testGetAllBooks() throws Exception {
		List<BookEntity> booksFromDb = (List<BookEntity>) bookRepository.findAll();
		
		assertNotNull(booksFromDb);
		assertTrue(booksFromDb.size() > 0);

		ResultActions resultActions = this.mockMvc.perform(get("/api/1.0/books").accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk());
		
		ApiResponse apiResponse = getApiResponseFromResultActions(resultActions);

		assertNotNull(apiResponse);
		assertTrue(apiResponse.getStatus());
		assertNull(apiResponse.getMessage());
		assertNotNull(apiResponse.getBody());
		
		BooksResponse books = (BooksResponse) parseBody(apiResponse.getBody(), BooksResponse.class);
		
		assertNotNull(books);
		assertEquals(booksFromDb.size(), books.getBooks().size());
	}

	@Test
	public void testPublishBook() throws Exception {
		List<BookEntity> booksBeforeSaved = (List<BookEntity>) bookRepository.findAll();
		PublishBookRequest req = getBookRequest();

		String jwt = getJwt("admin");
		assertTrue(!jwt.isEmpty());
		
		ResultActions resultActions = this.mockMvc.perform(post("/api/1.0/books/book").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
			.header("Authorization", "Bearer " + jwt).content(parseObjToJson(req)))
			.andExpect(status().isOk());
			
		ApiResponse apiResponse = getApiResponseFromResultActions(resultActions);

		assertNotNull(apiResponse);
		assertTrue(apiResponse.getStatus());
		assertNotNull(apiResponse.getBody());
		assertNull(apiResponse.getMessage());

		BookModel book = (BookModel) parseBody(apiResponse.getBody(), BookModel.class);

		assertNotNull(book);

		List<BookEntity> booksAfterSaved = (List<BookEntity>) bookRepository.findAll();

		assertTrue(booksBeforeSaved.size() < booksAfterSaved.size());
	}

	@Test
	public void testUpdateBook() throws Exception {
		BookEntity bookBeforeUpdate = bookRepository.findById(1l).orElse(null);
		assertNotNull(bookBeforeUpdate);

		PublishBookRequest req = getBookRequest();

		String jwt = getJwt("admin");
		assertTrue(!jwt.isEmpty());

		ResultActions resultActions = this.mockMvc.perform(put("/api/1.0/books/book/" + bookBeforeUpdate.getId())
			.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).header("Authorization", "Bearer " + jwt)
			.content(parseObjToJson(req)))
			.andExpect(status().isOk());

		ApiResponse apiResponse = getApiResponseFromResultActions(resultActions);

		assertNotNull(apiResponse);
		assertTrue(apiResponse.getStatus());
		assertNotNull(apiResponse.getBody());
		assertNull(apiResponse.getMessage());

		BookModel book = (BookModel) parseBody(apiResponse.getBody(), BookModel.class);

		assertNotNull(book);

		BookEntity bookAfterUpdate = bookRepository.findById(1l).orElse(null);

		assertNotNull(bookAfterUpdate);
		assertNotEquals(bookBeforeUpdate.getTitle(), bookAfterUpdate.getTitle());
		assertEquals(book.getTitle(), bookAfterUpdate.getTitle());
	}

	@Test
	public void testDeleteBook() throws Exception {
		List<BookEntity> booksBeforeDelete = (List<BookEntity>) bookRepository.findAll();

		assertTrue(booksBeforeDelete != null && booksBeforeDelete.size() > 0);

		String jwt = getJwt("admin");
		assertTrue(!jwt.isEmpty());

		ResultActions resultActions = this.mockMvc.perform(delete("/api/1.0/books/book/" + 1l)
			.contentType(MediaType.APPLICATION_JSON).header("Authorization", "Bearer " + jwt))
			.andExpect(status().isOk());
		
		ApiResponse apiResponse = getApiResponseFromResultActions(resultActions);

		assertNotNull(apiResponse);
		assertTrue(apiResponse.getStatus());
		assertNull(apiResponse.getBody());
		assertNull(apiResponse.getMessage());

		List<BookEntity> booksAfterDelete = (List<BookEntity>) bookRepository.findAll();

		assertTrue(booksAfterDelete != null && booksAfterDelete.size() > 0);
		assertTrue(booksBeforeDelete.size() > booksAfterDelete.size());
	}

	@Test
	public void testDeleteBookFromAnotherAuthor() throws Exception {
		List<BookEntity> booksBeforeDelete = (List<BookEntity>) bookRepository.findAll();

		assertTrue(booksBeforeDelete != null && booksBeforeDelete.size() > 0);

		String jwt = getJwt("admin");
		assertTrue(!jwt.isEmpty());

		ResultActions resultActions = this.mockMvc.perform(delete("/api/1.0/books/book/" + 2l)
			.contentType(MediaType.APPLICATION_JSON).header("Authorization", "Bearer " + jwt))
			.andExpect(status().isInternalServerError());
		
		ApiResponse apiResponse = getApiResponseFromResultActions(resultActions);

		assertNotNull(apiResponse);
		assertFalse(apiResponse.getStatus());
		assertNull(apiResponse.getBody());
		assertNotNull(apiResponse.getMessage());

		List<BookEntity> booksAfterDelete = (List<BookEntity>) bookRepository.findAll();

		assertTrue(booksAfterDelete != null && booksAfterDelete.size() > 0);
		assertEquals(booksBeforeDelete.size(), booksAfterDelete.size());	
	}

	private String getJwt(String username) {
		final UserDetails userDetails = getUserDetails(username);
        return userDetails != null ? jwtUtil.generateToken(userDetails) : "";
	}

	private UserDetails getUserDetails(String username) {
		return authService.loadUserByUsername(username);
	}

	private PublishBookRequest getBookRequest() {
		PublishBookRequest publishBookRequest = new PublishBookRequest();
		publishBookRequest.setTitle("The Phantom Menace");
		publishBookRequest.setDescription("This is the first episode from the full series of 9 books.");
		publishBookRequest.setCoverImage("https://m.media-amazon.com/images/M/MV5BYTRhNjcwNWQtMGJmMi00NmQyLWE2YzItODVmMTdjNWI0ZDA2XkEyXkFqcGdeQXVyNTAyODkwOQ@@._V1_.jpg");
		publishBookRequest.setPrice(35.0);

		return publishBookRequest;
	}

	private String parseObjToJson(Object obj) throws JsonMappingException, JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(obj);
	}

	private ApiResponse parseResponse(String json) throws JsonMappingException, JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		ApiResponse apiResponse = mapper.readValue(json, ApiResponse.class);

		return apiResponse;
	}

	private <T> Object parseBody(Object body, Class<T> clazz) throws JsonMappingException, JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(body);

		return mapper.readValue(json, clazz);
	}

	private ApiResponse getApiResponseFromResultActions(ResultActions resultActions) throws Exception {
		MvcResult result = resultActions.andReturn();
		String contentAsString = result.getResponse().getContentAsString();

		return parseResponse(contentAsString);
	}

}
