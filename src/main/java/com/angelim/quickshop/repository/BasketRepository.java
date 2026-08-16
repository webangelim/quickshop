package com.angelim.quickshop.repository;

import com.angelim.quickshop.entity.Basket;
import com.angelim.quickshop.entity.Status;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BasketRepository extends MongoRepository<Basket, Long> {

    Optional<Basket> findByClientAndStatus(Long client, Status status);
}
