package com.endre.java.java_ee_exam.backend.service;

import com.endre.java.java_ee_exam.backend.StubApplication;
import com.endre.java.java_ee_exam.backend.entity.Message;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
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

        assertNotNull(messageService.createMessage(sender, receiver, "test"));
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
        assertTrue(messageService.getSentMessages(sender).size() > 0);
    }

    @Test
    public void testNoMessage() {

        String sender = generateDefaultUsers();
        String receiver = generateDefaultUsers();

        assertNull(messageService.getSentMessages(sender));
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
            messageService.getSentMessages(getUniqueId());
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

    @Test
    public void testAllInfoInMessageStillTheSame() {
        String sender = generateDefaultUsers();
        String receiver = generateDefaultUsers();
        String text = "test";


        Long id = messageService.createMessage(sender, receiver, text);

        List<Message> messages = messageService.getSentMessages(sender);
        Message message = messages.get(0);

        assertEquals(sender, message.getSender());
        assertEquals(receiver, message.getReceiver());
        assertEquals(text, message.getText());
        assertEquals(id, message.getId());
    }


    @Test
    public void testSortedByTimeDescending() {
        String sender = generateDefaultUsers();
        String receiver = generateDefaultUsers();
        String text = "test";

        Long a = messageService.createMessage(sender, receiver, text);
        Long b = messageService.createMessage(sender, receiver, text);
        Long c = messageService.createMessage(sender, receiver, text);


        List<Message> messages = messageService.getSentMessages(sender);

//        assertTrue(messages.get(0).getDate().after(messages.get(1).getDate()));

        assertEquals(c, messages.get(0).getId());
        assertEquals(b, messages.get(1).getId());
        assertEquals(a, messages.get(2).getId());

    }
}