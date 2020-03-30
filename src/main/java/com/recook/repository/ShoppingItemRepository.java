package com.recook.repository;

import com.recook.model.ShoppingItem;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingItemRepository extends MongoRepository<ShoppingItem, Long> {
}
