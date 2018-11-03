package pl.rabowski.book_catalogue.controllers;

import java.util.List;

import javax.validation.Valid;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.rabowski.book_catalogue.exception.BookNotFoundException;
import pl.rabowski.book_catalogue.models.Book;
import pl.rabowski.book_catalogue.repository.BookRepository;
import pl.rabowski.book_catalogue.service.MemoryBookService;

@CrossOrigin(origins = "http://localhost:8090")
@RestController
@RequestMapping("/books")
public class BookController {
	
	@Autowired
	private MemoryBookService memoryBookService;
	
	@Autowired
	private BookRepository bookRepository;
	
	
	@GetMapping("/hello")
	public String hello() {
		return "{hello: World}";
	}

	@GetMapping("/helloBook")
	public Book helloBook() {
		return new Book(1, "9788324631766", "Thinking	in	Java", "Bruce	Eckel", "Helion", "programming");
	}


	@GetMapping(path = "/all", produces = MediaType.APPLICATION_JSON)
	public List<Book> listOfAllBooks() {
		return bookRepository.findAll();
	}

	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON)
	public Book showBook(@PathVariable long id) {
		Book book = memoryBookService.getBook(id);
		if(book==null) {
			throw new BookNotFoundException(id);
		}
		return book;
	}

	@PostMapping(path = "/", consumes=MediaType.APPLICATION_JSON)
	public Book addBook(@Valid @RequestBody Book book){
		return bookRepository.save(book);
	}

	@PutMapping(consumes = MediaType.APPLICATION_JSON)
	public Book editBook(@Valid @RequestBody Book book) {
		Book bookToEdit = memoryBookService.getBook(book.getId());
		if(bookToEdit == null) {
			throw new BookNotFoundException(book.getId());
		}
		return bookRepository.save(book);
	}

	@DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON)
	public String deleteBook(@PathVariable long id) {
		Book book = memoryBookService.getBook(id);
		if(book == null) {
			throw new BookNotFoundException(id);
		}
		String title = book.getTitle();
		String author = book.getAuthor();
		bookRepository.delete(book);
		return "Book "+ id + ". " + title + " by " + author+ " has been succesfully deleted";
	}

}
