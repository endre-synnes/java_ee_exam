package com.endre.java.java_ee_exam.selenium;

import org.openqa.selenium.WebDriver;

public abstract class SeleniumTestBase {

    protected abstract WebDriver getDriver();

    protected abstract String getServerHost();

    protected abstract int getServerPort();



}
