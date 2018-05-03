package com.endre.java.java_ee_exam.frontend.controller;


import com.endre.java.java_ee_exam.backend.entity.Book;
import com.endre.java.java_ee_exam.backend.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.faces.bean.RequestScoped;
import javax.inject.Named;
import java.util.List;

@Named
@RequestScoped
public class AdminController{


    @Autowired
    private BookService bookService;

    private List<Book> books;

    private String title, author, course;

    public List<Book> getBooks() {
        books =  bookService.getAllBooks();
        return books;
    }


    public void deleteBook(String title){
        bookService.deleteBook(title);
    }

    public void createBook(){
        bookService.createBook(title, author, course);
        clearFields();
    }

    private void clearFields() {
        title = "";
        author = "";
        course = "";
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }
}
