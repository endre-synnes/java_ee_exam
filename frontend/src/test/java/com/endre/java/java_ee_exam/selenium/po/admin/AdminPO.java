package com.endre.java.java_ee_exam.selenium.po.admin;

import com.endre.java.java_ee_exam.selenium.PageObject;
import com.endre.java.java_ee_exam.selenium.po.LayoutPO;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AdminPO extends LayoutPO {

    public AdminPO(WebDriver driver, String host, int port){
        super(driver, host, port);
    }

    public AdminPO(PageObject other){
        super(other);
    }

    //TODO Check if title still the same
    @Override
    public boolean isOnPage() {
        return getDriver().getTitle().contains("Admin Page");
    }



    public void createBook(String title){
        setText("sellerForm:inputTitle", title);
        setText("sellerForm:inputAuthor", "New Author");
        setText("sellerForm:inputCourse", "Default Course");
        clickAndWait("sellerForm:createBookId");
    }


    public void deleteBook(int row){

        getDriver().findElement(By.xpath("//INPUT[@id='bookTable:"+row+":deleteBookId']")).click();
        waitForPageToLoad();
    }
}
