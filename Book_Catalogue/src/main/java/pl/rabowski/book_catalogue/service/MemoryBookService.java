package pl.rabowski.book_catalogue.service;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import org.springframework.stereotype.Component;

import pl.rabowski.book_catalogue.models.Book;

@Component
public class MemoryBookService implements BookService {
	private List<Book> list;

	public MemoryBookService() {
		list = new ArrayList<>();
		list.add(new Book(1, "9788324631766", "Thinking in Java", "Bruce Eckel", "Helion", "programming"));
		list.add(
				new Book(2, "9788324627738", "Rusz glowa, Java.", "Sierra Kathy, Bates Bert", "Helion", "programming"));
		list.add(new Book(3, "9780130819338", "Java	2. Podstawy", "Cay Horstmann, Gary Cornell", "Helion",
				"programming"));
	}

	@Override
	public List<Book> getList() {
		return list;
	}

	@Override
	public void setList(List<Book> list) {
		this.list = list;
	}

	@Override
	public Book getBook(long bookIndex) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getId() == bookIndex) {
				return list.get(i);
			}
		}
		return null;
	}

	@Override
	public void addBook(Book book) {
		list.add(book);
	}

	@Override
	public void editBook(int id, String isbn, String title, String author, String publisher, String type) {
		Book editedBook = this.getBook(id);
		editedBook.setIsbn(isbn);
		editedBook.setTitle(title);
		editedBook.setAuthor(author);
		editedBook.setPublisher(publisher);
		editedBook.setType(type);
	}

	@Override
	public void deleteBook(int id) {
		ListIterator<Book> iterator = list.listIterator();
		while (iterator.hasNext()) {
			if (iterator.next().getId() == id) {
				iterator.remove();
			}
		}
	}
}
