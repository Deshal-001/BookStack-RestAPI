package com.example.bookStore.data;


import jakarta.persistence.*;

@Entity
@Table(name = "order_item_table")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_item_id", nullable = false)
    private Long order_item_id;


    @ManyToOne(optional = false)
    @JoinColumn(name = "book_id",nullable = false)
    private Book book;

    @ManyToOne(optional = false)
    @JoinColumn(name = "order_order_id", nullable = false)
    private Order order;


    @Column(name = "quantity",nullable = false)
    private Long quantity;
    @Column(name = "total",nullable = false)
    private Long total;

    public OrderItem() {
    }

    public OrderItem(Book book, Order order, Long quantity, Long total) {
        this.book = book;
        this.order = order;
        this.quantity = quantity;
        this.total = total;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Long getOrder_item_id() {
        return order_item_id;
    }

    public void setOrder_item_id(Long order_item_id) {
        this.order_item_id = order_item_id;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "order_item_id=" + order_item_id +
                ", book=" + book +
                ", order=" + order +
                '}';
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}
