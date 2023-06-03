package com.fis.subscription.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

@Entity
@Table(name="subscription")
public class Subscription {
	
	@Id
	@Column(name="SUBSCRIBER_NAME")
	@NotNull(message="subscriber name can't be null")
	@NotBlank(message="subscriber name can't be empty")
	private String subscriberName;
	
	@Column(name="DATE_SUBSCRIBED")
	@NotBlank(message="date subscribed can't be empty")
	private String dateSubscribed;
	
	@Column(name="DATE_RETURNED")
	private String dateReturned;
	
	@Column(name="BOOK_ID")
	@NotNull(message="book id can't be null")
	private String bookId;

	public Subscription(
			@NotNull(message = "subscriber name can't be null") @NotBlank(message = "subscriber name can't be empty") String subscriberName,
			@NotBlank(message = "date subscribed can't be empty") String dateSubscribed,
			String dateReturned,
			@NotNull(message = "book id can't be null") String bookId) {
		super();
		this.subscriberName = subscriberName;
		this.dateSubscribed = dateSubscribed;
		this.dateReturned = dateReturned;
		this.bookId = bookId;
	}
	
	public Subscription() {

	}

	public String getSubscriberName() {
		return subscriberName;
	}

	public void setSubscriberName(String subscriberName) {
		this.subscriberName = subscriberName;
	}

	public String getDateSubscribed() {
		return dateSubscribed;
	}

	public void setDateSubscribed(String dateSubscribed) {
		this.dateSubscribed = dateSubscribed;
	}

	public String getDateReturned() {
		return dateReturned;
	}

	public void setDateReturned(String dateReturned) {
		this.dateReturned = dateReturned;
	}

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}
	
}
