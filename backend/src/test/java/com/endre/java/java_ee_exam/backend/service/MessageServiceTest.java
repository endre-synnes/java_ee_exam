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
public class MessageServiceTest extends ServiceTestBase {


    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;



    private static final AtomicInteger counter = new AtomicInteger(0);

    private String getUniqueId(){return "foo_email_" + counter.getAndIncrement();}

    private String generateDefaultUsers(){
        String userId = getUniqueId();
        userService.createUser(userId, "foo", "foo", "123", false);
        return userId;
    }

    @Test
    public void testSendMessage() {
        String sender = generateDefaultUsers();
        String receiver = generateDefaultUsers();

        assertTrue(messageService.createMessage(sender, receiver, "test"));
    }

    @Test
    public void testGetReceivedMessages() {

        String sender = generateDefaultUsers();
        String receiver = generateDefaultUsers();

        messageService.createMessage(sender, receiver, "test");
        assertTrue(messageService.getReceivedMessages(receiver).size() > 0);
    }


    @Test
    public void testGetSentMessages() {

        String sender = generateDefaultUsers();
        String receiver = generateDefaultUsers();

        messageService.createMessage(sender, receiver, "test");
        assertTrue(messageService.getSendtMessages(sender).size() > 0);
    }

    @Test
    public void testNoMessage() {

        String sender = generateDefaultUsers();
        String receiver = generateDefaultUsers();

        assertNull(messageService.getSendtMessages(sender));
        assertNull(messageService.getReceivedMessages(receiver));
    }

    @Test
    public void testMissingSender() {
        String receiver = generateDefaultUsers();

        try {
            messageService.createMessage(getUniqueId(), receiver, "test");
            fail();
        }catch (IllegalArgumentException e){
            //Do Nothing
        }
    }


    @Test
    public void testMissingReceiver() {
        String sender = generateDefaultUsers();

        try {
            messageService.createMessage(sender, getUniqueId(), "test");
            fail();
        }catch (IllegalArgumentException e){
            //Do nothing
        }
    }

    @Test
    public void testGetMessageMissingSender() {
        try {
            messageService.getSendtMessages(getUniqueId());
            fail();
        }catch (IllegalArgumentException e){
            //Do nothing
        }
    }

    @Test
    public void testGetMessageMissingReceiver() {
        try {
            messageService.getReceivedMessages(getUniqueId());
            fail();
        }catch (IllegalArgumentException e){
            //Do nothing
        }
    }
}