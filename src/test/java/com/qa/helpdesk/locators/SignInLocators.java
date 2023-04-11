package com.qa.helpdesk.locators;

import org.openqa.selenium.By;

public class SignInLocators {

    public static By logIn = By.xpath("//button[text()='Login with SSO']");

    public static By typeEmail = By.xpath("//input[@type='email']");
    public static By typePassword = By.xpath("//input[@type='password']");
    public static By signIn = By.xpath("//input[@id='idSIButton9']");

    public static By newTicket = By.xpath("//button[text()='Create New Ticket']");

    public static By subject = By.xpath("//input[@placeholder='Enter Subject']");

    public static By Type = By.xpath("//input[@id='react-select-2-input']");

    public static By NoBtn = By.xpath("//input[@value='No']");

    public static By nextBtn = By.xpath("//input[@id='idSIButton9']");

    public static By enterCredentials = By.xpath("//div[@class='placeholderContainer']/input");

    public static By desc = By.xpath("//textarea[@id='description']");

    public static By upload_file = By.xpath("//img[@class='upload_uploadIcon__PINtJ']");
    public static By chooseoption_type = By.xpath("//div[text()='Choose Option' and @id='react-select-2-placeholder']");
    public static By signOutBtn = By.xpath("//img[@alt=\"logout\"]");
    public static By supportView = By.xpath("//div[text()='Support View']");
    public static By employeeView = By.xpath("//div[text()='Employee View']");
    public static By errorLoginMessage = By.xpath("//div[@class='error ext-error']");

}

