package pl.spring.demo.to;

import pl.spring.demo.enumerations.BookStatus;

public class BookTo {
	private Long id;
	private String title;
	private String authors;
	private BookStatus status;

	public BookTo() {
	}

	public BookTo(Long id, String title, String authors, BookStatus status) {
		this.id = id;
		this.title = title;
		this.authors = authors;
		this.setStatus(status);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthors() {
		return authors;
	}

	public void setAuthors(String authors) {
		this.authors = authors;
	}

	public BookStatus getStatus() {
		return status;
	}

	public void setStatus(BookStatus status) {
		this.status = status;
	}

	@Override
	public boolean equals(Object o) {
		if (title.equalsIgnoreCase(((BookTo) o).getTitle()) && authors.equalsIgnoreCase(((BookTo) o).getAuthors())) {
			return true;
		}

		return false;
	}
}
