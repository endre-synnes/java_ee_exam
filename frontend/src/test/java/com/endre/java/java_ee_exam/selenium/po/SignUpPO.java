package com.endre.java.java_ee_exam.selenium.po;

import com.endre.java.java_ee_exam.selenium.PageObject;
import org.openqa.selenium.By;

public class SignUpPO extends LayoutPO {


    public SignUpPO(PageObject other) {
        super(other);
    }

    //TODO: Check title value
    public boolean isOnPage() {
        return getDriver().getTitle().contains("Sign Up");
    }

    //TODO Check if name still the same
    public IndexPO createUser(String email, String firstname, String surname, String password){

        setText("email", email);
        setText("firstname", firstname);
        setText("surname", surname);
        setText("password", password);
        clickAndWait("submit");

        IndexPO po = new IndexPO(this);
        if (po.isOnPage()){
            return po;
        }
        return null;
    }

    //TODO Check if name still the same
    public IndexPO createAdmin(String email, String firstname, String surname, String password){
        setText("email", email);
        setText("firstname", firstname);
        setText("surname", surname);
        setText("password", password);
        driver.findElement(By.id("ceckIsAdmin")).click();
        clickAndWait("submit");

        IndexPO po = new IndexPO(this);
        if (po.isOnPage()){
            return po;
        }

        return null;
    }
}
