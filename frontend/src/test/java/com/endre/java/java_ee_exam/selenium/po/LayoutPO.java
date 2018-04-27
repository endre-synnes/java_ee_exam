package com.endre.java.java_ee_exam.selenium.po;

import com.endre.java.java_ee_exam.selenium.PageObject;
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

        SignUpPO po = new IndexPO(this);
        assertTrue(po.isOnPage());

        return po;
    }
}
