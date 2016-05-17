package pl.spring.demo.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import pl.spring.demo.to.AuthorTo;
import pl.spring.demo.to.BookTo;
import pl.spring.demo.to.BookEntity;

@Component
public class BookMapper {

	public BookTo convertFromBookEntityIntoBookTo(BookEntity book) {
		return new BookTo(book.getId(), book.getTitle(), convertAuthorFromStringIntoAuthorTo(book.getAuthors()));
	}

	public BookEntity convertFromBookToIntoBookEntity(BookTo book) {
		return new BookEntity(book.getId(), book.getTitle(), convertAuthorFromAuthorToIntoString(book.getAuthors()));
	}

	private List<AuthorTo> convertAuthorFromStringIntoAuthorTo(String authors) {
		List<AuthorTo> convertedAuthors = new ArrayList<>();
		if (authors == null) {
			return convertedAuthors;
		}
		String[] splittedAuthors = authors.split("\\s");
		Long authorId = 1L;
		for (int i = 0; i < splittedAuthors.length - 1; i += 2) {
			convertedAuthors.add(new AuthorTo(authorId, splittedAuthors[i], splittedAuthors[i + 1]));
			++authorId;
		}
		return convertedAuthors;
	}

	private String convertAuthorFromAuthorToIntoString(List<AuthorTo> authors) {
		StringBuilder sbAuthors = new StringBuilder();
		if (authors == null) {
			return sbAuthors.toString();
		}
		for (AuthorTo authorTo : authors) {
			sbAuthors.append(authorTo.getFirstName() + " " + authorTo.getLastName() + " ");
		}
		return sbAuthors.toString();
	}
}
