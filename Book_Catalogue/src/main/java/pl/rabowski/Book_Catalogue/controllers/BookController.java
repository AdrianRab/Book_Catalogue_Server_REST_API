package pl.rabowski.Book_Catalogue.controllers;

import java.util.List;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pl.rabowski.Book_Catalogue.models.Book;
import pl.rabowski.Book_Catalogue.models.MemoryBookService;

@CrossOrigin(origins = "http://localhost:8090")
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

	@RequestMapping(path = "/allBooks", method = RequestMethod.GET)
	public List<Book> listOfAllBooks() {
		return memoryBookService.getList();
	}

	@RequestMapping(path = "/book/{id}", method = RequestMethod.GET)
	public Book listOfAllBooks(@PathVariable int id) {
		return memoryBookService.getBook(id);
	}

	@PostMapping(path = "/")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addBook(@RequestBody Book book) {
		long id = Book.counter.getAndIncrement();
		book.setId(id);
		memoryBookService.addBook(book);
		return Response.status(201).entity(book).build();
	}

	// w poniższym przypadku mam SyntaxError: JSON.parse: unexpected end of data at
	// line 1 column 1 of the JSON data, w powyższym tylko error...
	// @PostMapping(path = "/")
	// @Produces(MediaType.APPLICATION_JSON)
	// public void addBook (@RequestBody Book book) {
	// long id = Book.counter.getAndIncrement();
	// book.setId(id);
	// memoryBookService.addBook(book);
	// }

	@PutMapping(path = "/editBook/{id}")
	public void editBook(@PathVariable int id, @RequestParam String isbn, @RequestParam String title,
			@RequestParam String author, @RequestParam String publisher, @RequestParam String type) {
		memoryBookService.editBook(id, isbn, title, author, publisher, type);
	}

	@DeleteMapping(path = "/deleteBook/{id}")
	public String deleteBook(@PathVariable int id) {
		memoryBookService.deleteBook(id);
		return "Book has been deleted";
	}

}
