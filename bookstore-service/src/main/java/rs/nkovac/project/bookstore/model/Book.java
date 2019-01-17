package rs.nkovac.project.bookstore.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Book {

	private String isbn;
	private String title;
	private String authors;
	private String genre;
	private int pagesNum;

	public Book() {
	}

	public Book(String isbn, String title, String authors, String genre, int pagesNum) {
		this.isbn = isbn;
		this.title = title;
		this.authors = authors;
		this.genre = genre;
		this.pagesNum = pagesNum;
	}

	@JsonProperty
	public String getIsbn() {
		return isbn;
	}

	@JsonProperty
	public String getTitle() {
		return title;
	}

	@JsonProperty
	public String getAuthors() {
		return authors;
	}

	@JsonProperty
	public String getGenre() {
		return genre;
	}

	@JsonProperty
	public int getPagesNum() {
		return pagesNum;
	}

}
