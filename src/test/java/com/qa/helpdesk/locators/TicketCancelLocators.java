package com.qa.helpdesk.locators;

import org.openqa.selenium.By;

public class TicketCancelLocators {


    public static By threeDotsBtn = By.xpath("//img[@alt=\"more\"]");
    public static By cancelTicketBtn = By.xpath("//span[text()='Cancel Ticket']");
    public static By typeReason = By.xpath("//textarea[@id='userConsent']");
    public static By finalCancelBtn = By.xpath("//button[text()='Cancel Ticket']");
    public static By cancelTicketStatus = By.xpath("(//tbody/tr/td[7])[1]");
    public static By reasonRequiredWarning = By.xpath("//span[text()='Reason is required']");
    public static By ticketCancelFromDetailsPage = By.xpath("//span[text()='Cancel Ticket']");


}
