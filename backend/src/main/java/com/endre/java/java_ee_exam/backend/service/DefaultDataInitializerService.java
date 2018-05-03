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

        attempt(() -> bookService.createBook("Java EE", "Jhon Snow", "EnterPRISE 1", true));
        attempt(() -> bookService.createBook("Algorithms", "Jhon Snow", "EnterPRISE 1", false));
    }

    private <T> T attempt(Supplier<T> lambda){
        try {
            return lambda.get();
        }catch (Exception e){
            return null;
        }
    }
}
