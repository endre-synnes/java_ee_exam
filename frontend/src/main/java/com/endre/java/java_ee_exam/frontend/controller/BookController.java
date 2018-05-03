package com.endre.java.java_ee_exam.frontend.controller;

import com.endre.java.java_ee_exam.backend.entity.Book;
import com.endre.java.java_ee_exam.backend.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.util.List;
import java.util.Map;

@Named
@RequestScoped
public class BookController {

    @Autowired
    private BookService bookService;

    public List<Book> getBooks(){
        return bookService.getAllBooks();
    }

    private String getUerName(){
        return ((UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
    }

    public String addSellerToBook(String bookTitle){
        boolean addedSeller = bookService.addUserTooBook(getUerName(), bookTitle);
        System.out.println(addedSeller);
        return "/index.jsf";
    }

    public String removeSellerFromBook(String bookTitle){
        boolean addedSeller = bookService.removeUserFromBook(getUerName(), bookTitle);
        System.out.println(addedSeller);
        return "/index.jsf";
    }

    public boolean getIfUserInBookList(String bookTitle){
        System.out.println("Book title here:........................"+ bookTitle);
        System.out.println("Is user in book list.......!!!!!! "+bookService.isUserInBookList(getUerName(), bookTitle));
        return bookService.isUserInBookList(getUerName(), bookTitle);
    }

}
