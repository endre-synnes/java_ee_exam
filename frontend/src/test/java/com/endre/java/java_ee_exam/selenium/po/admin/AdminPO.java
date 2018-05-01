package com.endre.java.java_ee_exam.selenium.po.admin;

import com.endre.java.java_ee_exam.selenium.PageObject;
import com.endre.java.java_ee_exam.selenium.po.LayoutPO;
import org.openqa.selenium.WebDriver;

public class AdminPO extends LayoutPO {

    public AdminPO(WebDriver driver, String host, int port){
        super(driver, host, port);
    }

    public AdminPO(PageObject other){
        super(other);
    }

    @Override
    public boolean isOnPage() {
        return getDriver().getTitle().contains("Admin Page");
    }
}
