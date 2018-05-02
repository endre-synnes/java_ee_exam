//package com.endre.java.java_ee_exam.backend.entity;
//
//import org.hibernate.validator.constraints.NotBlank;
//
//import javax.persistence.*;
//import javax.validation.constraints.Size;
//import java.util.ArrayList;
//import java.util.List;
//
//@Entity
//@Table(name = "BOOKS")
//public class Book {
//
//    @Id
//    @GeneratedValue
//    private Long id;
//
//    @NotBlank
//    @Size(max = 128)
//    @Column(unique = true)
//    private String title;
//
//    @NotBlank
//    @Size(max = 128)
//    private String author;
//
//    @NotBlank
//    @Size(max = 128)
//    private String course;
//
//    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
//    private List<User> users;
//
//    public Book() {
//        users = new ArrayList<>();
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public String getAuthor() {
//        return author;
//    }
//
//    public void setAuthor(String author) {
//        this.author = author;
//    }
//
//    public String getCourse() {
//        return course;
//    }
//
//    public void setCourse(String course) {
//        this.course = course;
//    }
//
//    public List<User> getUsers() {
//        return users;
//    }
//
//    public void setUsers(List<User> users) {
//        this.users = users;
//    }
//}
