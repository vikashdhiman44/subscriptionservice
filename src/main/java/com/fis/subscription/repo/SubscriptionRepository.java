package com.fis.subscription.repo;

import org.springframework.data.repository.CrudRepository;

import com.fis.subscription.model.Subscription;

public interface SubscriptionRepository extends CrudRepository<Subscription,String> {

}
