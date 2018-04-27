package com.endre.java.java_ee_exam.backend.service;

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
        Query query = em.createNativeQuery("delete from user_roles");
        query.executeUpdate();
        
        deleteEntities(User.class);
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
