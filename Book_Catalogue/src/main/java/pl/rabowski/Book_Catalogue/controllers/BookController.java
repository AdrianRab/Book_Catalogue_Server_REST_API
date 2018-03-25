package pl.rabowski.Book_Catalogue.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pl.rabowski.Book_Catalogue.models.Book;
import pl.rabowski.Book_Catalogue.models.MemoryBookService;

@EnableAutoConfiguration
@RestController
@RequestMapping("/books")
public class BookController {
	@RequestMapping("/hello")
	public String hello() {
		return "{hello: World}";
	}

	@RequestMapping("/helloBook")
	public Book helloBook() {
		return new Book(1, "9788324631766", "Thinking	in	Java", "Bruce	Eckel", "Helion", "programming");
	}

	@Autowired
	private MemoryBookService memoryBookService;

	@RequestMapping("/allBooks")
	public List<Book> listOfAllBooks() {
		return memoryBookService.getList();
	}

	@RequestMapping("/book/{id}")
	public Book listOfAllBooks(@PathVariable int id) {
		return memoryBookService.getBook(id);
	}

	@PostMapping(path = "/addBook")
	public Book addBook(@RequestParam int id, @RequestParam String isbn, @RequestParam String title,
			@RequestParam String author, @RequestParam String publisher, @RequestParam String type) {
		return memoryBookService.addBook(id, isbn, title, author, publisher, type);
	}

	@PutMapping(path = "/editBook/{id}")
	public Book editBook(@PathVariable int id, @RequestParam String isbn, @RequestParam String title,
			@RequestParam String author, @RequestParam String publisher, @RequestParam String type) {
		return memoryBookService.editBook(id, isbn, title, author, publisher, type);
	}
	
	@DeleteMapping(path="deleteBook/{id}")
	public String deleteBook(@PathVariable int id) {
		memoryBookService.deleteBook(id);
		return "Book has been deleted";
	}

}
