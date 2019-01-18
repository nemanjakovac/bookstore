package rs.nkovac.project.bookstore.service;

import java.util.List;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response.Status;

import org.skife.jdbi.v2.sqlobject.CreateSqlObject;

import rs.nkovac.project.bookstore.dao.BookDao;
import rs.nkovac.project.bookstore.model.Book;

/**
 * @author kovac
 *
 *         Class that handles business logic
 */
public abstract class BookstoreService {
	
	private static final String BOOK_NOT_FOUND = "Book with ISBN %s not found.";
	private static final String BOOK_EXISTS = "Book with ISBN %s already Exists.";

	
	@CreateSqlObject
	abstract BookDao bookDao();

	/**
	 * @return
	 * 
	 * not implemented
	 */
	public List<Book> getBooks() {
//		return bookDao().getBooks();
		return null;
	}

	public Book getBook(String isbn) throws WebApplicationException {
		Book book = bookDao().getBook(isbn);
		if (book == null) {
			throw new WebApplicationException(String.format(BOOK_NOT_FOUND, isbn), Status.NOT_FOUND);
		} else
			return book;
	}

	public Book createBook(Book book) {
		if(bookDao().getBook(book.getIsbn()) == null)
			bookDao().createBook(book);
		else 
			throw new WebApplicationException(String.format(BOOK_EXISTS, book.getIsbn()));
		return bookDao().getBook(book.getIsbn());
	}

}
