package com.endre.java.java_ee_exam.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.function.Supplier;

@Service
public class DefaultDataInitializerService {

    @Autowired
    private UserService userService;


    @Autowired
    private BookService bookService;

    @PostConstruct
    public void initialize(){
        attempt(() -> userService.createUser("bar.barer@email.com", "foo","bar", "123",  false));
        attempt(() -> userService.createUser("admin@mail.com","admin", "admin", "123", true));

        attempt(() -> bookService.createBook("Java EE", "John Snow", "Enterprise 1"));
        attempt(() -> bookService.createBook("Algorithms", "Mike Tyson", "Enterprise 2"));
    }

    private <T> T attempt(Supplier<T> lambda){
        try {
            return lambda.get();
        }catch (Exception e){
            return null;
        }
    }
}
