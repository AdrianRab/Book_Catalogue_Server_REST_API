package pl.rabowski.book_catalogue.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.rabowski.book_catalogue.models.Book;
import pl.rabowski.book_catalogue.repository.BookRepository;

@Service
public class MemoryBookService {
	
	@Autowired
	private BookRepository bookRepository;
	
	
	public Book getBook(long bookIndex) {
		Book book = bookRepository.findBookdById(bookIndex);
		return book;
	}
}
