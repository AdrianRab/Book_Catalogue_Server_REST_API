package pl.rabowski.Book_Catalogue.models;

import java.util.concurrent.atomic.AtomicLong;

public class Book {
	private long id;
	private String isbn;
	private String title;
	private String author;
	private String publisher;
	private String type;
	public static final AtomicLong counter = new AtomicLong(1);

	public Book(long id, String isbn, String title, String author, String publisher, String type) {
		this.id = id;
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.type = type;
	}
	
	public Book(String isbn, String title, String author, String publisher, String type) {
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.type = type;
	}

	public Book() {
	}

	public String toStirng() {
		return "id " + this.id + "\n isbn; " + this.isbn + "\n title: " + this.title + "\n author: " + this.author
				+ "\n  publisher: " + this.publisher + "\n type: " + this.type;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
