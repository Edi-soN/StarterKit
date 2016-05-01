package pl.spring.demo.dao.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pl.spring.demo.annotation.NullableId;
import pl.spring.demo.dao.BookDao;
import pl.spring.demo.to.BookTo;
import pl.spring.demo.to.AuthorTo;
import pl.spring.demo.to.BookEntity;

@Repository
public class BookDaoImpl implements BookDao {

	private final Set<BookEntity> ALL_BOOKS = new HashSet<>();
	@Autowired
	private BookMapper bookMapper;

	public BookDaoImpl() {
		addTestBooks();
	}

	@Override
	public List<BookTo> findAll() {
		List<BookTo> booksList = new ArrayList<>();
		for (BookEntity entity : ALL_BOOKS) {
			booksList.add(bookMapper.convertFromBookEntityIntoBookTo(entity));
		}
		return booksList;
	}

	@Override
	public List<BookTo> findBookByTitle(String title) {
		List<BookTo> foundBooks = new ArrayList<>();
		if (title == null || title == "") {
			return foundBooks;
		}
		for (BookEntity entity : this.ALL_BOOKS) {
			String source = "";
			if (entity.getTitle() != null && entity.getTitle().length() >= title.length()) {
				source = entity.getTitle().substring(0, title.length());
			}
			if (title.equalsIgnoreCase(source)) {
				foundBooks.add(bookMapper.convertFromBookEntityIntoBookTo(entity));
			}
		}
		return foundBooks;
	}

	@Override
	public List<BookTo> findBooksByAuthor(String author) {
		List<BookTo> foundBooks = new ArrayList<>();
		if (author == null || author == "") {
			return foundBooks;
		}
		String[] splittedAuthor = author.split("\\s");
		if (splittedAuthor.length != 2) {
			return foundBooks;
		}
		String firstName = splittedAuthor[0];
		String lastName = splittedAuthor[1];
		for (BookEntity entity : this.ALL_BOOKS) {
			for (AuthorTo authorTo : bookMapper.convertFromBookEntityIntoBookTo(entity).getAuthors()) {
				if (authorTo != null && authorTo.getFirstName().length() >= firstName.length()
						&& authorTo.getLastName().length() >= lastName.length()
						&& authorTo.getFirstName().substring(0, firstName.length()).equalsIgnoreCase(firstName)
						&& authorTo.getLastName().substring(0, lastName.length()).equalsIgnoreCase(lastName)) {
					foundBooks.add(bookMapper.convertFromBookEntityIntoBookTo(entity));
				}
			}
		}
		return foundBooks;
	}

	@Override
	@NullableId
	public BookTo save(BookTo book) {
		ALL_BOOKS.add(bookMapper.convertFromBookToIntoBookEntity(book));
		return book;
	}

	private void addTestBooks() {
		ALL_BOOKS.add(new BookEntity(1L, "Romeo i Julia", "Wiliam Szekspir"));
		ALL_BOOKS.add(new BookEntity(2L, "Opium w rosole", "Hanna Ożogowska"));
		ALL_BOOKS.add(new BookEntity(3L, "Przygody Odyseusza", "Jan Parandowski"));
		ALL_BOOKS.add(new BookEntity(4L, "Awantura w Niekłaju", "Edmund Niziurski"));
		ALL_BOOKS.add(new BookEntity(5L, "Pan Samochodzik i Fantomas", "Zbigniew Nienacki"));
		ALL_BOOKS.add(new BookEntity(6L, "Zemsta", "Aleksander Fredro"));
	}
}
