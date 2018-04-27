package com.endre.java.java_ee_exam;

import org.springframework.boot.SpringApplication;

public class LocalApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, "--spring.profiles.active=test");
    }
}
