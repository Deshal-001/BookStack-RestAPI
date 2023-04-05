package com.example.bookStore.service;

import com.example.bookStore.data.Order;
import com.example.bookStore.data.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class orderService {
    @Autowired
    private OrderRepo orderRepo;

    public Order getBookById(Long id) {
        return orderRepo.findById(id).orElseThrow(() -> new orderService.NotFoundException("Book not found"));
    }

    public void saveOrder(Order order) {
        orderRepo.save(order);
    }

    private static class NotFoundException extends RuntimeException {
        public NotFoundException(String book_not_found) {
            super(book_not_found);
        }
    }
}
