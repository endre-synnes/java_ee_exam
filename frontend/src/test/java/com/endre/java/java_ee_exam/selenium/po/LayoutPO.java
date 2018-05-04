package com.endre.java.java_ee_exam.selenium.po;

import com.endre.java.java_ee_exam.selenium.PageObject;
import com.endre.java.java_ee_exam.selenium.po.admin.AdminPO;
import com.endre.java.java_ee_exam.selenium.po.ui.MessagesPO;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public abstract class LayoutPO extends PageObject {

    public LayoutPO(WebDriver driver, String host, int port){
        super(driver, host, port);
    }

    public LayoutPO(PageObject other){
        super(other);
    }

    //TODO Check if name still the same
    public SignUpPO toSignUp(){
        clickAndWait("linkToSignUpId");

        SignUpPO po = new SignUpPO(this);
        assertTrue(po.isOnPage());

        return po;
    }

    public LoginPO toLogin(){
        clickAndWait("linkToLoginId");

        LoginPO po = new LoginPO(this);
        assertTrue(po.isOnPage());

        return po;
    }

    //TODO Check if name still the same
    public AdminPO goToAdmin(){
        clickAndWait("adminId");

        AdminPO po = new AdminPO(this);
        assertTrue(po.isOnPage());

        return po;
    }

    //TODO Check if name still the same
    public IndexPO doLogout(){
        clickAndWait("logoutId");

        IndexPO po = new IndexPO(this);
        assertTrue(po.isOnPage());

        return po;
    }

    public MessagesPO goToMessages(){
        clickAndWait("linkToMessagesId");

        MessagesPO po = new MessagesPO(this);
        assertTrue(po.isOnPage());

        return po;
    }

    public IndexPO goToHomeLoggedIn(){
        clickAndWait("linkToHomeId");

        IndexPO po = new IndexPO(this);
        assertTrue(po.isLoggedIn());
        assertTrue(po.isOnPage());

        return po;
    }

    public IndexPO goToHomeNotLoggedIn(){
        clickAndWait("linkToHomeNotLoggedIn");

        IndexPO po = new IndexPO(this);
        assertFalse(po.isLoggedIn());
        assertTrue(po.isOnPage());

        return po;
    }

    //TODO Check if name still the same
    public boolean isLoggedIn(){
        return getDriver().findElements(By.id("logoutId")).size() > 0 &&
                getDriver().findElements(By.id("linkToSignUpId")).isEmpty();
    }
}
