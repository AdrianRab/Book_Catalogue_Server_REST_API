package pl.rabowski.Book_Catalogue.controllers;


import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.rabowski.Book_Catalogue.models.Book;

@EnableAutoConfiguration
@RestController
@RequestMapping("/books")
public class BookController	{
	@RequestMapping("/hello")
	public	String	hello(){
		return "{hello: World}";
	}

	@RequestMapping("/helloBook")
	public	Book helloBook(){
		return new Book(1,"9788324631766","Thinking	in	Java",
			"Bruce	Eckel","Helion","programming");
	}
}
