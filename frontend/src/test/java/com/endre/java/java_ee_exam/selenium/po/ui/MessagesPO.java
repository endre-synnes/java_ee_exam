package com.endre.java.java_ee_exam.selenium.po.ui;

import com.endre.java.java_ee_exam.selenium.PageObject;
import com.endre.java.java_ee_exam.selenium.po.BookDetailPO;
import com.endre.java.java_ee_exam.selenium.po.LayoutPO;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertTrue;

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


    public String receivedMessageText(int message){
        return getDriver().findElement(By.xpath("//LABEL[@id='receivedMessagesTable:"+message+":text']")).getText();
    }

    public String receivedMessageSender(int message){
        return getDriver().findElement(By.xpath("//LABEL[@id='receivedMessagesTable:"+message+":sender']")).getText();
    }

    public String receivedMessageTime(int message){
        return getDriver().findElement(By.xpath("//LABEL[@id='receivedMessagesTable:"+message+":time']")).getText();
    }



    public String sentMessageText(int message){
        return getDriver().findElement(By.xpath("//LABEL[@id='sentMessagesTable:"+message+":textSent']")).getText();
    }

    public String sentMessageReceiver(int message){
        return getDriver().findElement(By.xpath("//LABEL[@id='sentMessagesTable:"+message+":receiverSent']")).getText();
    }

    public String sentMessageTime(int message){
        return getDriver().findElement(By.xpath("//LABEL[@id='sentMessagesTable:"+message+":timeSent']")).getText();
    }



    public SendMessagePO replyToMessage(int message){
        getDriver().findElement(By.xpath("//INPUT[@id='receivedMessagesTable:"+message+":replyToMessageId']")).click();
        waitForPageToLoad();

        SendMessagePO po = new SendMessagePO(this);
        assertTrue(po.isOnPage());

        return po;
    }



}
