package pl.rabowski.book_catalogue.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import pl.rabowski.book_catalogue.exception.BookNotFoundException;
import pl.rabowski.book_catalogue.util.ErrorMessage;
import pl.rabowski.book_catalogue.util.FieldErrorMessage;

@ControllerAdvice
public class ControllerExceptionHandler {
	
	@ResponseBody
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(BookNotFoundException.class)
	private ErrorMessage exceptionHandler(BookNotFoundException e){
		long bookId = e.getBookId();
		return new ErrorMessage("404", "Book with provided id: " + bookId + " could not be found.");
	}
	
	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	private List<FieldError> exceptionHandler(MethodArgumentNotValidException e){
		List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
		fieldErrors.stream().map(fieldError-> new FieldErrorMessage(fieldError.getField(), fieldError.getDefaultMessage())).collect(Collectors.toList());
		return fieldErrors;
	}
}
