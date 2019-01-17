package rs.nkovac.project.bookstore.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

import com.codahale.metrics.annotation.Timed;

import rs.nkovac.project.bookstore.model.Book;
import rs.nkovac.project.bookstore.service.BookstoreService;

/**
 * @author kovac
 *
 *         Class that handles incoming requests
 */
@Path("/book")
@Produces(MediaType.APPLICATION_JSON)
public class BookController {

	private final BookstoreService bookstoreService;

	public BookController(BookstoreService bookstoreService) {
		this.bookstoreService = bookstoreService;
	}

	@GET
	@Timed
	@Path("/all")
	public List<Book> getBooks() {
		List<Book> bookList = bookstoreService.getBooks();
		return bookList;
	}

	@GET
	@Timed
	@Path("/get/{isbn}")
	public Book getBook(@PathParam("isbn") final String isbn) throws WebApplicationException{
		Book book = bookstoreService.getBook(isbn);
		return book;
	}

	@POST
	@Timed
	@Path("/add")
	public String addBook(@NotNull @Valid final Book book) {
		Book createdBook = bookstoreService.createBook(book);
		if (createdBook != null)
			return "Book successfully added.";
		else
			return "Book not added";
	}

}
