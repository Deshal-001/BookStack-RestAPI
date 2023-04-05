package com.example.bookStore.web;

import com.example.bookStore.data.Book;
import com.example.bookStore.service.bookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/book")
public class BookController {
    @Autowired
    public bookService bookService;




    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id){
        Book book=bookService.getBookById(id);
        return ResponseEntity.ok(book);
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks(){
        List<Book> books=bookService.getAllBooks();
        return ResponseEntity.ok(books);    }

    @PostMapping
    public ResponseEntity<?> createBook(@RequestBody Book book) {
        if (bookService.bookExist(book)){
            String existErrorMessage="Book with the title "+book.getTitle()+" is exist";
            return ResponseEntity.badRequest().body(existErrorMessage);        }
        else {
            bookService.saveBook(book);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
    }

    @DeleteMapping("/{id}")
    public void deleteBookById(@PathVariable Long id){
        bookService.deleteBookById(id);
        //return ResponseEntity.ok(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editBookById(@PathVariable Long id,@RequestBody Book book) {
        if (bookService.bookExistById(id)){
            bookService.editBookByID(id,book);
        return ResponseEntity.ok(book);}
        else {
            return ResponseEntity.badRequest().body("Can not find the book Id");
        }
    }

}
