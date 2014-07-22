package com.ingen.utility.entity;

/**
 * Sample entity object for testing purpose
 * 
 * @version 1.0.0
 * @since Jul 2014
 * 
 */
//@Entity
public class Book {
	
	private Long id;

	private String title;

	private String isbn;

	private String publishedDate;

	private String author;

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

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	
	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublishedDate() {
		return publishedDate;
	}

	public void setPublishedDate(String publishedDate) {
		this.publishedDate = publishedDate;
	}

	
	
}
