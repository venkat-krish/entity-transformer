package com.ingen.utility.entity;

import java.io.Serializable;

/**
 * Sample value object for testing purpose
 * 
 * @version 1.0.0
 * @since Jul 2014
 * 
 */
//@XmlRootElement
public class BookVO implements Serializable {

	private Long id;
	
	private String title;

	private String isbn;

	private String author;

	private String publishedDate;

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

	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPublishedDate() {
		return publishedDate;
	}

	public void setPublishedDate(String publishedDate) {
		this.publishedDate = publishedDate;
	}

	
}
