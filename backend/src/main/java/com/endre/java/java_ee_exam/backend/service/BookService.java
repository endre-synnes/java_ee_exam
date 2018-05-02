package com.endre.java.java_ee_exam.backend.service;

import com.endre.java.java_ee_exam.backend.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Service
@Transactional
public class BookService {

    @Autowired
    private EntityManager em;


    public boolean createBook(String title, String author, String course, boolean isUsed){

        TypedQuery<Book> query = em.createQuery("select b from Book b where b.title=?1", Book.class);
        query.setParameter(1, title);

        List<Book> resultList = query.getResultList();

        if (resultList.size() > 0){
            return false;
        }

        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        book.setCourse(course);
        book.setUsed(isUsed);

        em.persist(book);

        return true;
    }


    public List<Book> getAllBooks(boolean withUser){

        TypedQuery<Book> query = em.createQuery("select b from Book b", Book.class);
        List<Book> books = query.getResultList();

        if (withUser){
            books.forEach( book -> book.getUsers().size());
        }

        return books;
    }


    public List<Book> getAllBooksForSale(boolean withUser){
        TypedQuery<Book> query = em.createQuery("select b from Book b where count(b.users) > 0", Book.class);
        List<Book> books = query.getResultList();

        if (withUser){
            books.forEach( book -> book.getUsers().size());
        }

        return books;
    }



    public boolean deleteBook(String title){

        TypedQuery<Book> deleteQuery = em.createQuery("delete from Book b where b.title=?1", Book.class);
        deleteQuery.setParameter(1, title);

        em.persist(deleteQuery);
        return true;
    }


}
