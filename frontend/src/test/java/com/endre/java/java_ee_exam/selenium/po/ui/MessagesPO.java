package com.endre.java.java_ee_exam.selenium.po.ui;

import com.endre.java.java_ee_exam.selenium.PageObject;
import com.endre.java.java_ee_exam.selenium.po.LayoutPO;
import org.openqa.selenium.WebDriver;

public class MessagesPO extends LayoutPO {


    public MessagesPO(WebDriver driver, String host, int port) {
        super(driver, host, port);
    }

    public MessagesPO(PageObject other) {
        super(other);
    }

    @Override
    public boolean isOnPage() {
        return getDriver().getTitle().contains("Your Messages");
    }
}
