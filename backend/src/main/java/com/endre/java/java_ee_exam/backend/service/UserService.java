package com.endre.java.java_ee_exam.backend.service;

import com.endre.java.java_ee_exam.backend.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Collections;

@Service
@Transactional
public class UserService {


    @Autowired
    private EntityManager em;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean createUser(String email, String firstname, String surname, String password, boolean isAdmin){

        String hashedPassword = passwordEncoder.encode(password);

        if (em.find(User.class, email) != null){
            return false;
        }

        User user = new User();
        user.setEmail(email);
        user.setFirstname(firstname);
        user.setSurname(surname);
        user.setPassword(hashedPassword);
        if (isAdmin)
            user.setRoles(Collections.singleton("ROLE_ADMIN"));
        else
            user.setRoles(Collections.singleton("ROLE_USER"));
        user.setEnabled(true);

        em.persist(user);

        return true;
    }

}
