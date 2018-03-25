package pl.rabowski.Book_Catalogue.models;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class MemoryBookService {
	private List<Book> list;

	public MemoryBookService() {
		list = new ArrayList<>();
		list.add(new Book(1, "9788324631766", "Thinking in Java", "Bruce Eckel", "Helion", "programming"));
		list.add(new Book(2, "9788324627738", "Rusz glowa, Java.", "Sierra Kathy, Bates	Bert", "Helion",
				"programming"));
		list.add(new Book(3, "9780130819338", "Java	2.	Podstawy", "Cay	Horstmann,	Gary Cornell", "Helion",
				"programming"));
	}

	public List<Book> getList() {
		return list;
	}

	public void setList(List<Book> list) {
		this.list = list;
	}

	public Book getBook(int bookIndex) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getId() == bookIndex) {
				return list.get(i);
			}
		}
		return null;
	}

	public Book addBook(int id, String isbn, String title, String author, String publisher, String type) {
		Book book = new Book(id, isbn, title, author, publisher, type);
		list.add(book);
		return book;
	}

	public Book editBook(int id, String isbn, String title, String author, String publisher, String type) {
		Book editedBook = this.getBook(id);
		editedBook.setIsbn(isbn);
		editedBook.setTitle(title);
		editedBook.setAuthor(author);
		editedBook.setPublisher(publisher);
		editedBook.setType(type);
		return editedBook;
	}

	public void deleteBook(int id) {
		list.remove(this.getBook(id));
	}
}
