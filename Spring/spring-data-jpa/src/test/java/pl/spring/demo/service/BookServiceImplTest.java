package pl.spring.demo.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.spring.demo.exception.BookNotNullIdException;
import pl.spring.demo.to.BookTo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "CommonServiceTest-context.xml")
public class BookServiceImplTest {

	@Autowired
	private BookService bookService;
	@Autowired
	private CacheManager cacheManager;

	@Before
	public void setUp() {
		cacheManager.getCache("booksCache").clear();
	}

	@Test
	public void testShouldFindAllBooks() {
		// when
		List<BookTo> allBooks = bookService.findAllBooks();
		// then
		assertNotNull(allBooks);
		assertFalse(allBooks.isEmpty());
		assertEquals(7, allBooks.size());
	}

	@Test
	@Ignore
	public void testShouldFindAllBooksByTitle() {
		// given
		final String title = "Opium w rosole";
		// when
		List<BookTo> booksByTitle = bookService.findBooksByTitle(title);
		// then
		assertNotNull(booksByTitle);
		assertFalse(booksByTitle.isEmpty());
	}

	@Test(expected = BookNotNullIdException.class)
	public void testShouldThrowBookNotNullIdException() {
		// given
		final BookTo bookToSave = new BookTo();
		bookToSave.setId(22L);
		// when
		bookService.saveBook(bookToSave);
		// then
		fail("test should throw BookNotNullIdException");
	}

	@Test
	public void testShouldReturnIdSeven() {
		// given
		final BookTo bookToSave = new BookTo();
		bookToSave.setId(null);
		// when
		bookService.saveBook(bookToSave);
		Long result = bookToSave.getId();
		// then
		assertEquals(Long.valueOf(7), result);
	}

	@Test
	public void testShouldReturnBookWithGivenTitle() {
		// given
		String title = "Rom";
		// when
		List<BookTo> searchedBook = bookService.findBooksByTitle(title);
		String result = searchedBook.get(0).getTitle();
		// then
		assertEquals("Romeo i Julia", result);
	}

	@Test
	public void testShouldNotReturnBookWithGivenTitle() {
		// given
		String title = "Qwe";
		// when
		List<BookTo> searchedBook = bookService.findBooksByTitle(title);
		// then
		assertTrue(searchedBook.isEmpty());
	}

	@Test
	public void testShouldReturnBookWithGivenAuthor() {
		// given
		String author = "Wil Szek";
		// when
		List<BookTo> searchedBook = bookService.findBooksByAuthor(author);
		String result = searchedBook.get(0).getAuthors().get(0).getFirstName();
		// then
		assertEquals("Wiliam", result);
	}
	
	@Test
	public void testShouldReturnBookWithGivenAuthorIgnoreCase() {
		// given
		String author = "wil sZEK";
		// when
		List<BookTo> searchedBook = bookService.findBooksByAuthor(author);
		String result = searchedBook.get(0).getAuthors().get(0).getFirstName();
		// then
		assertEquals("Wiliam", result);
	}

	@Test
	public void testShouldNotReturnBookWithGivenAuthor() {
		// given
		String author = "Asd Dsa";
		// when
		List<BookTo> searchedBook = bookService.findBooksByAuthor(author);
		// then
		assertTrue(searchedBook.isEmpty());
	}
	
	@Test
	public void testShouldNotReturnBookWithGivenIncoorectAuthor() {
		// given
		String author = "qwerty";
		// when
		List<BookTo> searchedBook = bookService.findBooksByAuthor(author);
		// then
		assertTrue(searchedBook.isEmpty());
	}

}
