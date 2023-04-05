package com.example.bookStore.service;

import com.example.bookStore.data.*;
import jakarta.persistence.GeneratedValue;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

@Service
public class orderItemService {
    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private BookRepo bookRepo;

    @Autowired
    private OrderItemRepo orderItemRepo;

    @Autowired
    private JdbcTemplate jdbcTemplate;


    //get order items by id
    public List<OrderItem> getOrderItemById(Long order_order_id){
        return orderItemRepo.findByOrder_OrderId(order_order_id);
    }

// get all order items
public List<OrderItem> getOrderItems(){
    return (List<OrderItem>) orderItemRepo.findAll();
}

   // create an order item
    public void addBookExistingOrder(Long orderId,Long bookId,Long book_quantity){
        System.out.println("In this method *******************************");
        Book book=bookRepo.findById(bookId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found"));
        OrderItem orderItem=new OrderItem();
        Order order=orderRepo.findById(orderId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found"));
        orderItem.setOrder(order);
        orderItem.setBook(book);
        orderItem.setQuantity(book_quantity);
        orderItem.setTotal(book.getPrice()*book_quantity);
        orderItemRepo.save(orderItem);
    }



    public Book getBookById(Long id) {
        return bookRepo.findById(id).orElseThrow(() -> new orderItemService.NotFoundException("Book not found"));
    }

    public List<Book> getAllBooks() {
        return (List<Book>) bookRepo.findAll();
    }

    public void createNewOrder(String customerName, List<Map<String, Object>> items) {
        Order order = new Order();
        int totQuantity = 0;
        int total=0;
        LocalDate localDate = LocalDate.now();
        // Convert LocalDate to Date
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        order.setOrderDate(date);
        order.setCustomerName(customerName);
        for (int i = 0; i < items.size(); i++) {
            Map<String, Object> item = items.get(i);
            Long bookId = Long.valueOf(String.valueOf(item.get("book_id")));
            Long quantity = Long.valueOf(String.valueOf(item.get("quantity")));
            Long total_=bookRepo.findById(bookId).orElseThrow(NoSuchElementException::new).getPrice()*quantity;
            total+=total_;
            totQuantity += quantity;
        }
        order.setQuantity((long) totQuantity);
        orderRepo.save(order);

        for (int i = 0; i < items.size(); i++) {
            Map<String, Object> item = items.get(i);
            Long bookId = Long.valueOf(String.valueOf(item.get("book_id")));
            OrderItem orderItem=new OrderItem();
            orderItem.setBook(bookRepo.findById(bookId).orElseThrow(NoSuchElementException::new));
            orderItem.setOrder(orderRepo.findById(order.getOrderId()).orElseThrow(NoSuchElementException::new));
            orderItem.setQuantity(order.getQuantity());
            orderItem.setTotal((long) total);
            orderItemRepo.save(orderItem);
        }



    }

    private static class NotFoundException extends RuntimeException {
        public NotFoundException(String book_not_found) {
            super(book_not_found);
        }
    }
}
