package com.example.bookStore.data;

import jdk.jfr.Registered;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;

import java.util.List;

@Registered
public interface OrderItemRepo extends CrudRepository <OrderItem,Long> {
    List<OrderItem> findByOrder_OrderId(@NonNull Long orderId);

}
