package pl.rabowski.book_catalogue.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.rabowski.book_catalogue.models.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{
		Book findBookdById(long id);
}
