package com.example.bookStore.service;

import com.example.bookStore.data.Book;
import com.example.bookStore.data.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class bookService {
    @Autowired
    private  BookRepo repo;

    public Book getBookById(Long id) {
        return repo.findById(id).orElseThrow(() -> new NotFoundException("Book not found"));
    }

    public List<Book> getAllBooks() {
        return (List<Book>) repo.findAll();
    }
    public void saveBook(Book book){
        if (book == null){
            throw new RuntimeException("Entered value is null");
        }

        else {repo.save(book);}
    }

    public void deleteBookById(Long id){
        repo.deleteById(id);
    }

    public void editBookByID(Long id,Book newBook){

        Optional<Book> optionalBook=repo.findById(id);
        if(optionalBook.isPresent()){
            Book book=optionalBook.get();
            book.setTitle(newBook.getTitle());
            book.setAuthor(newBook.getAuthor());
            book.setPrice(newBook.getPrice());
            book.setQuantity(newBook.getQuantity());
            Optional.of(repo.save(book));

        }

    }

    public Boolean bookExist(Book book){
        Iterable<Book> books=this.repo.findAll();
        ArrayList<Book>existingBooks=new ArrayList<>();
        books.forEach(book1 -> {existingBooks.add(book1);});

        boolean exists = false;
        for (Book Exbook:existingBooks) {
            if (Exbook.getTitle().equals(book.getTitle()) && Exbook.getAuthor().equals(book.getAuthor())) {
                exists = true;
                break;
            }
        }
        if(exists){
            return Boolean.TRUE;
        }
        else
            return Boolean.FALSE;
    }

    public boolean bookExistById(Long id){
        return repo.findById(id).isPresent();
    }

    private static class NotFoundException extends RuntimeException {
        public NotFoundException(String book_not_found) {
            super(book_not_found);
        }
    }


}
