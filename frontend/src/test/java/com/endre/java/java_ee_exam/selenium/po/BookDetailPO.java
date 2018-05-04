package com.endre.java.java_ee_exam.selenium.po;

import com.endre.java.java_ee_exam.selenium.PageObject;
import com.endre.java.java_ee_exam.selenium.po.ui.SendMessagePO;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertTrue;

public class BookDetailPO extends LayoutPO{


    public BookDetailPO(WebDriver driver, String host, int port) {
        super(driver, host, port);
    }

    public BookDetailPO(PageObject other) {
        super(other);
    }

    @Override
    public boolean isOnPage() {
        return getDriver().getTitle().contains("Book Details");
    }

    public SendMessagePO toSendMessagePage(){
        clickAndWait("goToSendMessageId");

        SendMessagePO po = new SendMessagePO(this);
        assertTrue(po.isOnPage());

        return po;
    }
}
