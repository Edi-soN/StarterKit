package pl.spring.demo.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.List;

import javax.persistence.OptimisticLockException;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import pl.spring.demo.booksearchcriteria.BookSearchCriteria;
import pl.spring.demo.entity.BookEntity;
import pl.spring.demo.to.BookTo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "CommonServiceTest-context.xml")
public class BookServiceTest {
	@Autowired
	BookService bookService;

	@Test
	// @Ignore
	public void testShouldFindBookByTitleCriteria() {
		// given
		final String libraryName = "Library1";
		BookSearchCriteria bookSearchCriteria = new BookSearchCriteria(null, null, libraryName);
		// when
		List<BookTo> foundBooks = bookService.searchBookByCriteria(bookSearchCriteria);
		// then
		assertFalse(foundBooks.isEmpty());
		assertEquals("Pierwsza książka", foundBooks.get(0).getTitle());
	}

	@Test
	//@Ignore
	public void testShouldFindBookByAuthorCriteria() {
		// given
		final String title = "Pierwsza książka";
		BookSearchCriteria bookSearchCriteria = new BookSearchCriteria(title, null, null);
		// when
		List<BookTo> foundBooks = bookService.searchBookByCriteria(bookSearchCriteria);
		// then
		assertFalse(foundBooks.isEmpty());
		assertEquals("Pierwsza książka", foundBooks.get(0).getTitle());
	}

	@Test
	//@Ignore
	public void testShouldFindBookByLibraryCriteria() {
		// given
		final String author = "Jan";
		BookSearchCriteria bookSearchCriteria = new BookSearchCriteria(null, author, null);
		// when
		List<BookTo> foundBooks = bookService.searchBookByCriteria(bookSearchCriteria);
		// then
		assertFalse(foundBooks.isEmpty());
		assertEquals("Pierwsza książka", foundBooks.get(0).getTitle());
	}

	@Test
	//@Ignore
	public void testShouldFindBookByAllCriteria() {
		// given
		final String title = "Pierwsza książka";
		final String author = "Jan";
		final String libraryName = "Library1";
		BookSearchCriteria bookSearchCriteria = new BookSearchCriteria(title, author, libraryName);
		// when
		List<BookTo> foundBooks = bookService.searchBookByCriteria(bookSearchCriteria);
		// then
		assertFalse(foundBooks.isEmpty());
		assertEquals("Pierwsza książka", foundBooks.get(0).getTitle());
	}

	@Test
	//@Ignore
	public void testShouldFindBookByNoneCriteria() {
		// given
		BookSearchCriteria bookSearchCriteria = new BookSearchCriteria(null, null, null);
		// when
		List<BookTo> foundBooks = bookService.searchBookByCriteria(bookSearchCriteria);
		assertEquals(3, foundBooks.size());
		// then
		assertFalse(foundBooks.isEmpty());
	}
	
	@Test(expected = ObjectOptimisticLockingFailureException.class)
	@Ignore
    public void testShouldThrowException(){
    	// given
		BookTo bookTo = bookService.findAllBooks().get(0);
    	// when
    	bookTo.setTitle("testowy");
    	
    	bookService.saveBook(bookTo);
    	bookTo.setTitle("inny");
    	//bookTo.setVersion(20l);
    	bookService.saveBook(bookTo);
    	
    }
}
