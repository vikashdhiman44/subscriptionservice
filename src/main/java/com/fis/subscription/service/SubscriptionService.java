package com.fis.subscription.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.fis.subscription.exception.NoDataFoundException;
import com.fis.subscription.model.Book;
import com.fis.subscription.model.Subscription;
import com.fis.subscription.repo.SubscriptionRepository;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class SubscriptionService {

	Logger logger = LoggerFactory.getLogger(SubscriptionService.class);

	@Autowired
	public SubscriptionRepository subscriptionRepo;

	private static final String RESILIENCE4J_INSTANCE_NAME = "subscriptionService";
	private static final String FALLBACK_METHOD = "getBooks";

	public List<Subscription> getAllSubscriptions() throws NoDataFoundException {
		List<Subscription> subscriptions = new ArrayList<>();
		subscriptionRepo.findAll().forEach(subscriptions::add);
		if (subscriptions.isEmpty()) {
			throw new NoDataFoundException();
		}
		return subscriptions;
	}

	public Subscription saveSubscription(Subscription subscription) throws HttpClientErrorException {
		Subscription newSubscription = null;
		Book[] books = getBooks();
		for (Book book : books) {
			if (book.getBookId().equalsIgnoreCase(subscription.getBookId()) && book.getAvailableCopies() > 0) {
				newSubscription = subscriptionRepo.save(subscription);
			}
		}
		return newSubscription;
	}

	@CircuitBreaker(name = RESILIENCE4J_INSTANCE_NAME, fallbackMethod = FALLBACK_METHOD)
	public Book[] getBooks() throws HttpClientErrorException {
		String uri = "http://localhost:8061/books";
		String env_uri = System.getenv("bookserviceuri");
		env_uri = env_uri!=null && !env_uri.isEmpty() ? env_uri : System.getProperty("bookserviceuri");
		//Contact[] contacts = restTemplate.getForObject("http://localhost:9002/contacts/" + userId, Contact[].class);
				//Contact[] contacts = this.restTemplate.getForObject(instance.getUri() + "/contacts/" + userId, Contact[].class);
				//Contact[] contacts = this.restTemplate.getForObject("http://CONTACT-SERVICE/contacts/" + userId, Contact[].class);
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Book[]> response = restTemplate.getForEntity(env_uri!=null && !env_uri.isEmpty() ? env_uri : uri, Book[].class);
		return response.getBody();
	}

	public Book[] getBooks(Throwable t) throws HttpClientErrorException {
		logger.error("Book Service is not working!");
		return null;
	}

}
