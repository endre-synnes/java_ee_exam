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

    private String getUniqueId(){return "foo_email_" + counter.getAndIncrement();}

    @Test
    public void testCreateUser() {
        assertTrue(userService.createUser(getUniqueId(), "foo", "bar", "123", false));
    }


    @Test
    public void testCreateDuplicateUser() {
        String email = getUniqueId();

        assertTrue(userService.createUser(email, "bar", "barer","123", false));
        assertFalse(userService.createUser(email, "bar", "barer","123",  false));
    }
}