package com.fis.subscription.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import com.fis.subscription.exception.NoDataFoundException;
import com.fis.subscription.model.Subscription;
import com.fis.subscription.service.SubscriptionService;

@RestController
public class SubscriptionController {

	Logger logger = LoggerFactory.getLogger(SubscriptionController.class);

	@Autowired
	private SubscriptionService subscriptionService;

	@RequestMapping("/subscriptions")
	public List<Subscription> getAllSubscriptions() throws NoDataFoundException {
		return subscriptionService.getAllSubscriptions();
	}

	@PostMapping(value = "/subscription", consumes = { "application/json" })
	public ResponseEntity<Subscription> addSubscription(@RequestBody @Valid Subscription subscription) {
		Subscription newSubscription = null;
		try {
			newSubscription = subscriptionService.saveSubscription(subscription);
		} catch (HttpClientErrorException ex) {
			return new ResponseEntity<>(newSubscription, HttpStatus.UNPROCESSABLE_ENTITY);
		}
		HttpStatus status = HttpStatus.CREATED;
		if (newSubscription == null) {
			status = HttpStatus.UNPROCESSABLE_ENTITY;
		}
		return new ResponseEntity<>(newSubscription, status);
	}
}
