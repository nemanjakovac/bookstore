package rs.nkovac.project.bookstore.dao;

import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import rs.nkovac.project.bookstore.mapper.BookMapper;
import rs.nkovac.project.bookstore.model.Book;

@RegisterMapper(BookMapper.class)
public interface BookDao {
	
	@SqlQuery("select * from book;")
	public List<Book> getBooks();

	@SqlQuery("select * from book where isbn = :isbn")
	public Book getBook(@Bind("isbn") final String isbn);

	@SqlUpdate("insert into book(isbn, title, authors, genre, pagesNum) values(:isbn, :title, :authors, :genre, :pagesNum)")
	void createBook(@BindBean final Book book);

}
