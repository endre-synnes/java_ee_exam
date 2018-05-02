package com.endre.java.java_ee_exam.selenium.po;

import com.endre.java.java_ee_exam.selenium.PageObject;

public class LoginPO extends LayoutPO {


    public LoginPO(PageObject other) {
        super(other);
    }

    //TODO: Check title value
    public boolean isOnPage() {
        return getDriver().getTitle().contains("Log In");
    }


    public IndexPO logInUser(String email, String password){

        setText("username", email);
        setText("password", password);
        clickAndWait("submit");

        IndexPO po = new IndexPO(this);
        if (po.isOnPage()){
            return po;
        }
        return null;
    }
}
