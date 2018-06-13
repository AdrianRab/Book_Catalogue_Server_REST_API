package pl.rabowski.book_catalogue.service;

import java.util.List;

import pl.rabowski.book_catalogue.models.Book;

public interface BookService {

	public List<Book> getList();;

	public void setList(List<Book> list);

	public Book getBook(int bookIndex);

	public void addBook(Book book);

	public void editBook(int id, String isbn, String title, String author, String publisher, String type);

	public void deleteBook(int id);
}
