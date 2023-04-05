package com.example.bookStore.web;


import com.example.bookStore.data.Book;
import com.example.bookStore.data.Order;
import com.example.bookStore.data.OrderItem;
import com.example.bookStore.service.orderItemService;
import com.example.bookStore.service.orderService;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")
public class OrderItemController {
    @Autowired
    public orderItemService orderItemService;

    @Autowired
    public orderService orderService;

    @GetMapping("/{id}")
    public List<OrderItem> getOrderById(@PathVariable Long id) {
        List<OrderItem> orders = orderItemService.getOrderItemById(id);
        return orders;
    }

    @GetMapping()
    public List<OrderItem> getOrderById() {
        List<OrderItem> orders = orderItemService.getOrderItems();
        return orders;
    }

    @PostMapping
    public void addBookExistingOrder(@RequestBody Map<String, Long> request){
        orderItemService.addBookExistingOrder(request.get("orderId"),request.get("bookId"),request.get("book_quantity"));
    }

    @PostMapping("/createOrder")
    public void createOrder(@RequestBody Map<String, Object> request) {
        String customerName= request.get("customer_name").toString();
        List<Map<String, Object>> items = (List<Map<String, Object>>) request.get("items");
        orderItemService.createNewOrder(customerName,items);
        System.out.println("Customer Name: " + request.get("customer_name"));

    }


}



