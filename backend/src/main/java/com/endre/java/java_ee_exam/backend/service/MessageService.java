package com.endre.java.java_ee_exam.backend.service;

import com.endre.java.java_ee_exam.backend.entity.Message;
import com.endre.java.java_ee_exam.backend.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class MessageService {

    @Autowired
    private EntityManager em;

    public boolean createMessage(String sender, String receiver, String text){

        User senderUser = em.find(User.class, sender);
        User receiverUser = em.find(User.class, receiver);
        if (senderUser == null || receiverUser == null){
            throw new IllegalArgumentException("Sender or does not exist");
        }

        Message message = new Message();
        message.setSender(sender);
        message.setReceiver(receiver);
        message.setText(text);
        message.setDate(new Date());

        em.persist(message);
        return true;
    }

    public List<Message> getReceivedMessages(String receiver){
        User user = em.find(User.class, receiver);
        if (user == null){
            throw new IllegalArgumentException("User with email: " + receiver + " does not exist!");
        }

        TypedQuery<Message> query = em.createQuery("select m from Message m where m.receiver=?1", Message.class);
        query.setParameter(1, receiver);

        List<Message> messages = query.getResultList();

        if (messages.size() == 0){
            return null;
        }

        return messages;
    }

    public List<Message> getSendtMessages(String sender){
        User user = em.find(User.class, sender);
        if (user == null){
            throw new IllegalArgumentException("User with email: " + sender + " does not exist!");
        }

        TypedQuery<Message> query = em.createQuery("select m from Message m where m.sender=?1", Message.class);
        query.setParameter(1, sender);

        List<Message> messages = query.getResultList();

        if (messages.size() == 0){
            return null;
        }

        return messages;
    }

}
