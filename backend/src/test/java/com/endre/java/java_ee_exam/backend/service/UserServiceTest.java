package com.endre.java.java_ee_exam.backend.service;

import com.endre.java.java_ee_exam.backend.StubApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = StubApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class UserServiceTest {

    @Autowired
    private UserService userService;


    private static final AtomicInteger counter = new AtomicInteger(0);

    private String getUniqueId(){return "foo_seleniumTest_" + counter.getAndIncrement();}

    @Test
    public void testCreateUser() {
        assertTrue(userService.createUser(getUniqueId(), "123", false));
    }


    @Test
    public void testCreateDuplicateUser() {
        String username = getUniqueId();

        assertTrue(userService.createUser(username, "123", false));
        assertFalse(userService.createUser(username, "123", false));
    }
}