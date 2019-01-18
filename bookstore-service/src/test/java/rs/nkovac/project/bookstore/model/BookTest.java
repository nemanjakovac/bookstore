package rs.nkovac.project.bookstore.model;

import static io.dropwizard.testing.FixtureHelpers.*;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import io.dropwizard.jackson.Jackson;
import com.fasterxml.jackson.databind.ObjectMapper;



public class BookTest {

	private static final ObjectMapper MAPPER = Jackson.newObjectMapper();
	private static final String BOOK_JSON = "fixtures/book.json";
	protected static final String TEST_BOOK_ISBN = "testISBN";
	protected static final String TEST_BOOK_TITLE = "testTitle";
	protected static final String TEST_BOOK_AUTHORS = "testAuthors";
	protected static final String TEST_BOOK_GENRE = "testGenre";
	protected static final int TEST_BOOK_PAGES_NUM = 1000;

	@Test
	public void serializesToJSON() throws Exception {
		final Book book = new Book(TEST_BOOK_ISBN, TEST_BOOK_TITLE, TEST_BOOK_AUTHORS, TEST_BOOK_GENRE,
				TEST_BOOK_PAGES_NUM);
		final String expected = MAPPER.writeValueAsString(MAPPER.readValue(fixture(BOOK_JSON), Book.class));
		assertThat(MAPPER.writeValueAsString(book)).isEqualTo(expected);
	}

	@Test
	public void deserializesFromJSON() throws Exception {
		final Book book = new Book(TEST_BOOK_ISBN, TEST_BOOK_TITLE, TEST_BOOK_AUTHORS, TEST_BOOK_GENRE,
				TEST_BOOK_PAGES_NUM);
		assertThat(MAPPER.readValue(fixture(BOOK_JSON), Book.class).getIsbn()).isEqualTo(book.getIsbn());
		assertThat(MAPPER.readValue(fixture(BOOK_JSON), Book.class).getTitle()).isEqualTo(book.getTitle());
		assertThat(MAPPER.readValue(fixture(BOOK_JSON), Book.class).getAuthors()).isEqualTo(book.getAuthors());
		assertThat(MAPPER.readValue(fixture(BOOK_JSON), Book.class).getGenre()).isEqualTo(book.getGenre());
		assertThat(MAPPER.readValue(fixture(BOOK_JSON), Book.class).getPagesNum() == book.getPagesNum());
	}

}
