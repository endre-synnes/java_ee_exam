package com.endre.java.java_ee_exam.backend.service;

import com.endre.java.java_ee_exam.backend.StubApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = StubApplication.class,
    webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class DefaultDataInitializerServiceTest {


    @Test
    public void dummyTest() {
        int i = 1;
        assertEquals(1,i);
    }
}