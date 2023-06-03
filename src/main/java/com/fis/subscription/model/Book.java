package com.fis.subscription.model;

public class Book {

	private String bookId;

	private String bookName;

	private String author;

	private int availableCopies;

	private String totalCopies;

	public Book(String bookId, String bookName, String author, int availableCopies, String totalCopies) {
		super();
		this.bookId = bookId;
		this.bookName = bookName;
		this.author = author;
		this.availableCopies = availableCopies;
		this.totalCopies = totalCopies;
	}

	public Book() {

	}

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getAvailableCopies() {
		return availableCopies;
	}

	public void setAvailableCopies(int availableCopies) {
		this.availableCopies = availableCopies;
	}

	public String getTotalCopies() {
		return totalCopies;
	}

	public void setTotalCopies(String totalCopies) {
		this.totalCopies = totalCopies;
	}

}
