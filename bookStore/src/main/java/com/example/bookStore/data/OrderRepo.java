package com.example.bookStore.data;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepo extends CrudRepository<Order,Long> {
    @Query("SELECT MAX(o.orderId) FROM Order o")
    Integer getMaxOrderId();

    List<Order> findByOrderId(@NonNull Long orderId);
}
