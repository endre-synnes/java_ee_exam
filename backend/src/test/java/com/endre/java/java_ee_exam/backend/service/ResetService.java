package com.endre.java.java_ee_exam.backend.service;

import com.endre.java.java_ee_exam.backend.entity.Book;
import com.endre.java.java_ee_exam.backend.entity.Message;
import com.endre.java.java_ee_exam.backend.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Service
@Transactional
public class ResetService {
    
    @PersistenceContext
    private EntityManager em;
    
    public void resetDatabase(){
        Query delete_from_user_roles = em.createNativeQuery("delete from user_roles");
        delete_from_user_roles.executeUpdate();
        Query delete_from_book_user = em.createNativeQuery("delete from book_users");
        delete_from_book_user.executeUpdate();

        deleteEntities(Book.class);
        deleteEntities(User.class);
        deleteEntities(Message.class);
    }

    private void deleteEntities(Class<?> entity) {
        if (entity == null || entity.getAnnotation(Entity.class) == null){
            throw new IllegalArgumentException("Invalid non-entity class");
        }
        String name = entity.getSimpleName();

        Query query = em.createQuery("delete from " + name);
        query.executeUpdate();
    }
}
