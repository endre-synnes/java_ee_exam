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


    public List<Book> getAllBooks(){
        TypedQuery<Book> query = em.createQuery("select b from Book b", Book.class);
        List<Book> books = query.getResultList();

        if (books.size() == 0)
            return null;

        return books;

    }


    public List<Book> getAllBooksForSale(){
        TypedQuery<Book> query = em.createQuery("select b from Book b where size(b.users) > 0", Book.class);
        List<Book> books = query.getResultList();

        if (books.size() == 0)
            return null;

        return books;
    }


    public Book getBook(String title){
        TypedQuery<Book> query = em.createQuery("select b from Book b where b.title=?1", Book.class);
        query.setParameter(1, title);

        List<Book> resultList = query.getResultList();

        if (resultList.size() == 0){
            return null;
        }

        return resultList.get(0);
    }


    public boolean addUserTooBook(String userEmail, String title){
        Book book = getBook(title);

        if (book == null){
            return false;
        }

        if (book.getUsers().contains(userEmail)) {
            return false;
        }

        book.getUsers().add(userEmail);
        return true;
    }

    public boolean removeUserFromBook(String userEmail, String title){
        Book book = getBook(title);

        if (book == null) {
            return false;
        }

        if (!book.getUsers().contains(userEmail)) {
            return false;
        }

        book.getUsers().remove(userEmail);
        return true;
    }

    public boolean deleteBook(String title){
        Book book = getBook(title);
        if (book == null)
            return false;

        em.remove(getBook(title));
        return true;
    }


}
