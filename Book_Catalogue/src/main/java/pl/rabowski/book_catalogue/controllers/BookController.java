package pl.rabowski.book_catalogue.controllers;

import java.util.List;

import javax.validation.Valid;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pl.rabowski.book_catalogue.exception.BookNotFoundException;
import pl.rabowski.book_catalogue.models.Book;
import pl.rabowski.book_catalogue.service.MemoryBookService;

@CrossOrigin(origins = "http://localhost:8090")
@EnableAutoConfiguration
@RestController
@RequestMapping("/books")
public class BookController {
	
	@Autowired
	private MemoryBookService memoryBookService;
	
	@RequestMapping("/hello")
	public String hello() {
		return "{hello: World}";
	}

	@RequestMapping("/helloBook")
	public Book helloBook() {
		return new Book(1, "9788324631766", "Thinking	in	Java", "Bruce	Eckel", "Helion", "programming");
	}


	@GetMapping(path = "/allBooks", produces = MediaType.APPLICATION_JSON)
	public List<Book> listOfAllBooks() {
		return memoryBookService.getList();
	}

	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON)
	public Book listOfAllBooks(@PathVariable int id) {
		Book book = memoryBookService.getBook(id);
		if(book==null) {
			throw new BookNotFoundException(id);
		}
		return book;
	}

	@PostMapping(path = "/", consumes=MediaType.APPLICATION_JSON)
	public Book addBook(@Valid @RequestBody Book book){
			long id = Book.counter.getAndIncrement();
			book.setId(id);
			memoryBookService.addBook(book);
			return memoryBookService.getBook(book.getId()); 
	}

	@PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON)
	public void editBook(@PathVariable int id, @RequestParam String isbn, @RequestParam String title,
			@RequestParam String author, @RequestParam String publisher, @RequestParam String type) {
		Book book = memoryBookService.getBook(id);
		if(book == null) {
			throw new BookNotFoundException(id);
		}
		memoryBookService.editBook(id, isbn, title, author, publisher, type);
	}

	@DeleteMapping(path = "/{id}")
	public String deleteBook(@PathVariable int id) {
		Book book = memoryBookService.getBook(id);
		if(book == null) {
			throw new BookNotFoundException(id);
		}
		memoryBookService.deleteBook(id);
		return "Book has been deleted";
	}

}
