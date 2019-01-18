package rs.nkovac.project.bookstore.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

import org.junit.After;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.jupiter.api.Test;

import io.dropwizard.testing.junit.ResourceTestRule;
import rs.nkovac.project.bookstore.model.Book;
import rs.nkovac.project.bookstore.model.BookTest;
import rs.nkovac.project.bookstore.service.BookstoreService;

public class BookControllerTest extends BookTest {

	private static final BookstoreService service = mock(BookstoreService.class);

	@ClassRule
	public static final ResourceTestRule controller = ResourceTestRule.builder()
			.addResource(new BookController(service)).build();

	private final Book book = new Book(BookTest.TEST_BOOK_ISBN, BookTest.TEST_BOOK_TITLE, BookTest.TEST_BOOK_AUTHORS,
			BookTest.TEST_BOOK_GENRE, BookTest.TEST_BOOK_PAGES_NUM);

	@Before
	public void setup() {
		when(service.getBook(eq(BookTest.TEST_BOOK_ISBN))).thenReturn(book);
		when(service.createBook(any(Book.class))).thenReturn(book);
	}

	@After
	public void tearDown() {
		reset(service);
	}

	@Test
	void testGetBook() {
		Book bookResponse = controller.target("book/get/" + BookTest.TEST_BOOK_ISBN).request().get(Book.class);
		assertThat(bookResponse.getIsbn()).isEqualTo(book.getIsbn());
		assertThat(bookResponse.getTitle()).isEqualTo(book.getTitle());
		assertThat(bookResponse.getAuthors()).isEqualTo(book.getAuthors());
		assertThat(bookResponse.getGenre()).isEqualTo(book.getGenre());
		verify(service).getBook(BookTest.TEST_BOOK_ISBN);
	}

	@Test
	void testAddBook() {
		Book addedBook = controller.target("book/add").request()
				.post(Entity.entity(book, MediaType.APPLICATION_JSON_TYPE), Book.class);
		assertNotNull(addedBook);
		assertThat(addedBook.getIsbn()).isEqualTo(book.getIsbn());
		assertThat(addedBook.getTitle()).isEqualTo(book.getTitle());
		assertThat(addedBook.getAuthors()).isEqualTo(book.getAuthors());
		assertThat(addedBook.getGenre()).isEqualTo(book.getGenre());
		verify(service).createBook(any(Book.class));
	}

}
