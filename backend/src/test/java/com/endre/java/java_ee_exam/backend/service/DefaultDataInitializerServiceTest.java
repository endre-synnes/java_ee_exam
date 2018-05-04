package com.endre.java.java_ee_exam.backend.service;

import com.endre.java.java_ee_exam.backend.StubApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = StubApplication.class,
    webEnvironment = SpringBootTest.WebEnvironment.NONE)

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class DefaultDataInitializerServiceTest {


    @Autowired
    private BookService bookService;


    @Test
    public void testInit() {

        assertTrue(bookService.getAllBooks().size() > 0);

        assertNotNull(bookService.getBook("Java EE"));
    }
}