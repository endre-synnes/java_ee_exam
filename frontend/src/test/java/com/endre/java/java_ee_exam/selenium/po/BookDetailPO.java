package com.endre.java.java_ee_exam.selenium.po;

import com.endre.java.java_ee_exam.selenium.PageObject;
import com.endre.java.java_ee_exam.selenium.po.ui.SendMessagePO;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

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

    public String getSellerEmail(){
        return getDriver().findElement(By.xpath("//LABEL[@id='sellerListTable:0:sellerId']")).getText();
    }

    public SendMessagePO toSendMessagePage(){
        getDriver().findElement(By.xpath("//INPUT[@id='sellerListTable:0:goToSendMessageId']")).click();
        waitForPageToLoad();

        SendMessagePO po = new SendMessagePO(this);
        assertTrue(po.isOnPage());

        return po;
    }
}
