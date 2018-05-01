package com.endre.java.java_ee_exam.selenium.po;

import com.endre.java.java_ee_exam.selenium.PageObject;
import com.endre.java.java_ee_exam.selenium.po.admin.AdminPO;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertTrue;

public abstract class LayoutPO extends PageObject {

    public LayoutPO(WebDriver driver, String host, int port){
        super(driver, host, port);
    }

    public LayoutPO(PageObject other){
        super(other);
    }

    public SignUpPO toSignUp(){
        clickAndWait("linkToSignUpId");

        SignUpPO po = new SignUpPO(this);
        assertTrue(po.isOnPage());

        return po;
    }

    public AdminPO goToAdmin(){
        clickAndWait("adminId");

        AdminPO po = new AdminPO(this);
        assertTrue(po.isOnPage());

        return po;
    }

    public IndexPO doLogout(){
        clickAndWait("logoutId");

        IndexPO po = new IndexPO(this);
        assertTrue(po.isOnPage());

        return po;
    }

    public boolean isLoggedIn(){
        return getDriver().findElements(By.id("logoutId")).size() > 0 &&
                getDriver().findElements(By.id("linkToSignUpId")).isEmpty();
    }
}
