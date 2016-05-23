package pl.spring.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pl.spring.demo.entity.BookEntity;

public interface BookRepository extends JpaRepository<BookEntity, Long> {

    @Query("select book from BookEntity book where upper(book.title) like concat(upper(:title), '%')")
    public List<BookEntity> findBookByTitle(@Param("title") String title);

    @Query("select book from BookEntity book JOIN book.authors author where upper(author.firstName) like concat('%', upper(:author), '%') or upper(author.lastName) like concat('%', upper(:author), '%')")
    public List<BookEntity> findBookByAuthor(@Param("author") String author);
    
    @Query("select book from BookEntity book JOIN book.authors author JOIN book.library library"
			+ " where upper(book.title) like concat(upper(:title), '%')"
			+ "AND (upper(author.firstName) like concat(upper(:author), '%') or upper(author.lastName) like concat(upper(:author), '%'))"
			+ " AND upper(library.name) like concat(upper(:libraryName), '%')")
	public List<BookEntity> findBookByCriteria(@Param("title") String title, @Param("author") String author,
			@Param("libraryName") String libraryName);
}
