package com.endre.java.java_ee_exam.selenium.po;

import com.endre.java.java_ee_exam.selenium.PageObject;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertTrue;

public class IndexPO extends LayoutPO{

    public IndexPO(WebDriver driver, String host, int port){
        super(driver, host, port);
    }

    public IndexPO(PageObject other) {
        super(other);
    }

    public void toStartPage(){
        getDriver().get(host + ":" + port);
    }

    //TODO: Check this if i change title in index page
    public boolean isOnPage() {
        return getDriver().getTitle().contains("Home");
    }


    public BookDetailPO goToDetailsPage(){
        clickAndWait("goToDetailsId");

        BookDetailPO po = new BookDetailPO(this);
        assertTrue(po.isOnPage());

        return po;
    }

}
