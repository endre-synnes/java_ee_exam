package com.endre.java.java_ee_exam.selenium;

import com.endre.java.java_ee_exam.selenium.po.IndexPO;
import com.endre.java.java_ee_exam.selenium.po.SignUpPO;
import com.endre.java.java_ee_exam.selenium.po.admin.AdminPO;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public abstract class SeleniumTestBase {

    protected abstract WebDriver getDriver();

    protected abstract String getServerHost();

    protected abstract int getServerPort();


    private static final AtomicInteger counter = new AtomicInteger(0);

    private String getUniqueId(){return "email_seleniumTest_" + counter.getAndIncrement() + "@me.com";}

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
    public void testLogInWithNonExistingUser() {

        //TODO Test if log in with non existing user


    }
}
