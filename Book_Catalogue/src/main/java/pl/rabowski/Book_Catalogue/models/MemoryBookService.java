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
		list.add(new Book(2, "9788324627738", "Rusz glowa, Java.", "Sierra Kathy, Bates	Bert", "Helion", "programming"));
		list.add(new Book(3, "9780130819338", "Java	2.	Podstawy", "Cay	Horstmann,	Gary Cornell", "Helion", "programming"));
	}

	public List<Book> getList() {
		return list;
	}

	public void setList(List<Book> list) {
		this.list = list;
	}
}
