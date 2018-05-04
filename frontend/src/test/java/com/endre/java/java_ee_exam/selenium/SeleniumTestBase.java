package com.endre.java.java_ee_exam.selenium;

import com.endre.java.java_ee_exam.selenium.po.BookDetailPO;
import com.endre.java.java_ee_exam.selenium.po.IndexPO;
import com.endre.java.java_ee_exam.selenium.po.LoginPO;
import com.endre.java.java_ee_exam.selenium.po.SignUpPO;
import com.endre.java.java_ee_exam.selenium.po.admin.AdminPO;
import com.endre.java.java_ee_exam.selenium.po.ui.MessagesPO;
import com.endre.java.java_ee_exam.selenium.po.ui.SendMessagePO;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.*;

public abstract class SeleniumTestBase {

    protected abstract WebDriver getDriver();

    protected abstract String getServerHost();

    protected abstract int getServerPort();


    private static final AtomicInteger counter = new AtomicInteger(0);

    private String getUniqueId(){return "email_seleniumTest_" + counter.getAndIncrement() + "@me.com";}
    private String getUniqueTitle(){return "Book_" + counter.getAndIncrement() + "";}

    private IndexPO home;

    private IndexPO createNewUser(String email, String firstname, String surname, String password, boolean isAdmin){
        home.toStartPage();

        SignUpPO signUpPO = home.toSignUp();

        IndexPO indexPO;
        if (isAdmin)
            indexPO = signUpPO.createAdmin(email, firstname, surname, password);
        else
            indexPO = signUpPO.createUser(email, firstname, surname, password);

        assertNotNull(indexPO);
        return indexPO;
    }

    @Before
    public void setUp() throws Exception {
        getDriver().manage().deleteAllCookies();

        home = new IndexPO(getDriver(), getServerHost(), getServerPort());

        home.toStartPage();

        assertTrue("Failed to start from Home Page", home.isOnPage());
    }

    @Test
    public void testCreateAndLogoutUser() {
        assertFalse(home.isLoggedIn());

        String email = getUniqueId();
        String firtname = "foo";
        String surname = "bar";
        String password = "12345678";
        home = createNewUser(email, firtname, surname, password, false);

        assertTrue(home.isLoggedIn());
        assertTrue(home.getDriver().getPageSource().contains(email));
    }

    @Test
    public void testCreateAdminAndGoToAdminPage() {
        assertFalse(home.isLoggedIn());

        String email = getUniqueId();
        String firtname = "foo";
        String surname = "bar";
        String password = "123456789";
        home = createNewUser(email, firtname, surname, password, true);

        AdminPO adminPO = home.goToAdmin();

        assertTrue(adminPO.isOnPage());
    }


    @Test
    public void testSignUpWithNullValue() {
        assertFalse(home.isLoggedIn());

        try {
            String email = getUniqueId();
            String firtname = null;
            String surname = "bar";
            String password = "123456789";
            home = createNewUser(email, firtname, surname, password, true);
            fail();
        }catch (Exception e){

        }

    }

    @Test
    public void testLogInWithNonExistingUser() {

        assertFalse(home.isLoggedIn());

        home.toStartPage();
        LoginPO loginPO = home.toLogin();

        assertNull(loginPO.logInUser(getUniqueId(), "123"));
    }


    @Test
    public void testGoToDetailsPage() {
        assertFalse(home.isLoggedIn());
        home.toStartPage();

        BookDetailPO po =home.goToDetailsPage(0);
        assertTrue(po.isOnPage());

    }


    @Test
    public void testDefaultBooks() {
        assertFalse(home.isLoggedIn());
        home.toStartPage();

        assertTrue(home.getNumberOfDisplayedBooks() > 1);
    }

    @Test
    public void testRegisterSellingNotAllowed() {
        assertFalse(home.isLoggedIn());
        home.toStartPage();

        assertEquals(4, home.getNumberOfColumnsToSeeIfSellColumnIsMissing());

        String email = getUniqueId();
        String firtname = "foo";
        String surname = "bar";
        String password = "12345678";
        home = createNewUser(email, firtname, surname, password, false);

        assertTrue(home.isLoggedIn());

        assertEquals(5, home.getNumberOfColumnsToSeeIfSellColumnIsMissing());
    }


    @Test
    public void testRegisterSelling() {
        String email = getUniqueId();
        String firtname = "foo";
        String surname = "bar";
        String password = "12345678";
        home = createNewUser(email, firtname, surname, password, false);

        assertTrue(home.isLoggedIn());

        int before = Integer.valueOf(home.getNumberOfSellers(0));
        int otherBefore = Integer.valueOf(home.getNumberOfSellers(1));

        //Marking
        home.markSellBook(0);

        int incensed = Integer.valueOf(home.getNumberOfSellers(0));
        int otherNotIncreased = Integer.valueOf(home.getNumberOfSellers(1));


        assertTrue(incensed > before);
        assertEquals(otherBefore, otherNotIncreased);

        //Un marking
        home.unmarkSellBook(0);

        int backToBefore = Integer.valueOf(home.getNumberOfSellers(0));

        assertEquals(before, backToBefore);

        //Marking it again
        home.markSellBook(0);

        int backToIncreased = Integer.valueOf(home.getNumberOfSellers(0));

        assertTrue(backToIncreased > backToBefore);

        home.doLogout();
        home.toStartPage();
        assertFalse(home.isLoggedIn());

        backToIncreased = Integer.valueOf(home.getNumberOfSellers(0));

        assertTrue(backToIncreased > before);
    }


    @Test
    public void testBookDetails() {
        String emailUserOne = getUniqueId();
        String firtname = "foo";
        String surname = "bar";
        String password = "12345678";
        home = createNewUser(emailUserOne, firtname, surname, password, false);

        String emailUserTwo = "admin@mail.com";

        assertTrue(home.isLoggedIn());

        home.markSellBook(1);

        BookDetailPO bookDetailPO = home.goToDetailsPage(1);

        String testEmailUserOne = bookDetailPO.getSellerEmail();

        assertEquals(emailUserOne, testEmailUserOne);

        home = bookDetailPO.doLogout();
        assertFalse(home.isLoggedIn());
        home.toStartPage();

        LoginPO loginPO = home.toLogin();
        home = loginPO.logInUser(emailUserTwo, "123");

        assertTrue(home.isLoggedIn());

        bookDetailPO = home.goToDetailsPage(1);
        testEmailUserOne = bookDetailPO.getSellerEmail();

        assertEquals(emailUserOne, testEmailUserOne);
    }


    @Test
    public void testMessages() {
        String emailUserOne = "admin@mail.com";

        LoginPO loginPO = home.toLogin();
        home = loginPO.logInUser(emailUserOne, "123");
        assertTrue(home.isLoggedIn());

        home.markSellBook(2);
        home = home.doLogout();


        String emailUserTwo = getUniqueId();
        String firtname = "foo";
        String surname = "bar";
        String password = "123";
        home = createNewUser(emailUserTwo, firtname, surname, password, false);

        assertTrue(home.isLoggedIn());

        BookDetailPO bookDetailPO = home.goToDetailsPage(2);

        SendMessagePO sendMessagePO = bookDetailPO.toSendMessagePage();
        assertTrue(sendMessagePO.isOnPage());

        String message = "hello";
        String anotherMessage = "are you there?";
        sendMessagePO.sendMessage(message);
        sendMessagePO.sendMessage(anotherMessage);

        home = home.doLogout();


        loginPO = home.toLogin();
        home = loginPO.logInUser(emailUserOne, "123");
        assertTrue(home.isLoggedIn());

        MessagesPO messagesPO = home.goToMessages();

        //Getting messages in the right order
        String testMessageOne = messagesPO.receivedMessageText(0);
        String testMessageTwo = messagesPO.receivedMessageText(1);
        assertEquals(anotherMessage, testMessageOne);
        assertEquals(message, testMessageTwo);
        assertEquals(messagesPO.receivedMessageSender(0), emailUserTwo);

        String replyMessage = "hello to you too!";
        sendMessagePO = messagesPO.replyToMessage(0);
        sendMessagePO.sendMessage(replyMessage);

        home = home.goToHomeLoggedIn();
        home = home.doLogout();

        loginPO = home.toLogin();
        home = loginPO.logInUser(emailUserTwo, "123");
        assertNotNull(home);

        messagesPO = home.goToMessages();
        String receivedMessage = messagesPO.receivedMessageText(0);
        assertEquals(receivedMessage, receivedMessage);
    }


    @Test
    public void testAdminCreateAndDeleteBook() {
        String email = "admin@mail.com";

        LoginPO loginPO = home.toLogin();
        home = loginPO.logInUser(email, "123");
        assertTrue(home.isLoggedIn());

        AdminPO adminPO = home.goToAdmin();
        adminPO.createBook(getUniqueTitle());

        adminPO.deleteBook(0);
    }
}
