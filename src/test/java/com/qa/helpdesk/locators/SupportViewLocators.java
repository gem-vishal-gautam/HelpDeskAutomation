package com.qa.helpdesk.locators;

import org.openqa.selenium.By;

public class SupportViewLocators {

    public static By enterSubjectSupportView = By.xpath("//input[@placeholder='Enter subject']");
    public static By enterDescriptionSupportView = By.xpath("//textarea[@name='description']");
    public static By uploadFIleSupportView = By.xpath("//span[text()=' Click to upload ']");
    public static By callerSearchNameSupportView = By.xpath("(//input[@autocapitalize=\"none\"])[1]");
    public static By infoSupportView = By.xpath("//span[@role=\"button\"]/img[@alt='info']");
    public static By historySupportView = By.xpath("//span[@role=\"button\"]/img[@alt='history']");
    public static By channelSupportView = By.xpath("//div[text()='Choose channel']");

    public static By channelType(String channelName) {
        return By.xpath("//div[text()='" + channelName + "']");
    }

    public static By headerName(String headName) {
        return By.xpath("//div[text()='" + headName + "']");
    }

    public static By loggedUserName = By.xpath("//div[@class='leftmenu_empName__hP5QR']");
    public static By loggedUserDesignation = By.xpath("//div[@class='leftmenu_empRole__I-c4k']");
    public static By assignedToTab = By.xpath("//tbody/tr/td[5]");
    public static By assignedTab = By.xpath("//div[text()='Assigned']");

}
