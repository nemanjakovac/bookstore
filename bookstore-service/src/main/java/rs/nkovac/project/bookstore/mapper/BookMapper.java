package rs.nkovac.project.bookstore.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import rs.nkovac.project.bookstore.model.Book;

/**
 * @author kovac
 *
 *         maps database columns to book object
 */
public class BookMapper implements ResultSetMapper<Book> {

	@Override
	public Book map(int index, ResultSet r, StatementContext ctx) throws SQLException {
		return new Book(r.getString("isbn"), r.getString("title"), r.getString("authors"), r.getString("genre"),
				r.getInt("pagesnum"));
	}

}
