package com.endre.java.java_ee_exam.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.function.Supplier;

@Service
public class DefaultDataInitializerService {

    @Autowired
    private UserService userService;

    @PostConstruct
    public void initialize(){
        attempt(() -> userService.createUser("bar.barer@email.com", "foo","bar", "123",  false));


    }

    private <T> T attempt(Supplier<T> lambda){
        try {
            return lambda.get();
        }catch (Exception e){
            return null;
        }
    }
}
