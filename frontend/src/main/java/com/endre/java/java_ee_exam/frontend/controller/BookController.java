package com.endre.java.java_ee_exam.frontend.controller;

import com.endre.java.java_ee_exam.backend.entity.Book;
import com.endre.java.java_ee_exam.backend.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Named
@SessionScoped
public class BookController implements Serializable {

    @Autowired
    private BookService bookService;

    private Book bookObject;

    private Set<String> sellers;

    private String getUerName(){
        return ((UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
    }

    public String addSellerToBook(String bookTitle){
        bookService.addUserTooBook(getUerName(), bookTitle);
        return "/index.jsf";
    }

    public String removeSellerFromBook(String bookTitle){
        bookService.removeUserFromBook(getUerName(), bookTitle);
        return "/index.jsf";
    }

    public boolean getIfUserInBookList(String bookTitle){
        return bookService.isUserInBookList(getUerName(), bookTitle);
    }


    public String goToDetails(String bookTitle){
        bookObject = bookService.getBook(bookTitle);
        sellers = bookObject.getUsers();
        return "/bookDetail.jsf?faces-redirect=true";
    }

    public Book getBookObject() {
        return bookObject;
    }

    public List<Book> getBooks(){
        return bookService.getAllBooks();
    }

    public Set<String> getSellers() {
        return sellers;
    }
}
