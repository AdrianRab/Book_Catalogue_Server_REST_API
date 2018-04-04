package pl.rabowski.Book_Catalogue.models;

import java.util.List;

public interface BookService {

	public List<Book> getList();;

	public void setList(List<Book> list);

	public Book getBook(int bookIndex);

	public void addBook(Book book);

	public void editBook(int id, String isbn, String title, String author, String publisher, String type);

	public void deleteBook(int id);
}
