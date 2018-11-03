package pl.rabowski.book_catalogue.exception;

public class BookNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private long bookId;

	public BookNotFoundException(long bookId) {
		super();
		this.bookId = bookId;
	}

	public long getBookId() {
		return bookId;
	}

}
