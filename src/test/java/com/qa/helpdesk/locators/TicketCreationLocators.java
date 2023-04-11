package com.qa.helpdesk.locators;

import org.openqa.selenium.By;

public class TicketCreationLocators {
    public static By newTicket = By.xpath("//button[contains(text(),'Create')]");
    public static By subject = By.xpath("//input[@placeholder='Enter Subject']");
    public static By description = By.xpath("//textarea[@id='description']");
    public static By fileUpload = By.xpath("//div[@role='presentation']/input");
    public static By ticketTypeIncident = By.xpath("//div[text()='Incident']");
    public static By ticketTypeRequest = By.xpath("//div[text()='Request']");
    public static By typeOption = By.xpath("//div[text()='Choose Option']");
    public static By departmentOption = By.xpath("//label[@for='department']/following-sibling::div/div");

    public static By optionType(String deptName) {
        return By.xpath("//div[text()= '" + deptName + "']");
    }

    public static By hrOption = By.xpath("//div[text()='HR']");
    public static By adminOption = By.xpath("//div[text()='Admin']");
    public static By chooseCategory = By.xpath("//div[text()='Choose category']");
    public static By chooseSubCategory = By.xpath("//div[text()='Choose sub-category']");
    public static By previewBtn = By.xpath("//button[text()='Preview']");
    public static By submitBtn = By.xpath("//button[text()='Submit']");
    public static By previewSubject = By.xpath("(//label[text()='Subject']/following::p)[1]");
    public static By previewDescription = By.xpath("(//label[text()='Description']/following::p)[1]");
    public static By previewType = By.xpath("(//label[text()='Type']/following::p)[1]");
    public static By previewDepartmentType = By.xpath("(//label[text()='Department']/following::p)[1]");
    public static By continueBtn = By.xpath("//button[text()='Continue']");
    public static By previewCategory = By.xpath("(//label[text()='Category']/following::p)[1]");
    public static By previewSubCategory = By.xpath("(//label[text()='Sub-category']/following::p)[1]");
    public static By previewAttachments = By.xpath("//label[text()='Attachments']/following-sibling::div//span");
    public static By commentArea = By.xpath("//textarea[@name='comment']");
    public static By updateButton = By.xpath("//button[text()='Update']");
    public static By addedComment = By.xpath("(//span[@class=\"comments_name__MIEIh\"]//following::div)[1]");

    public static By ticketTypeDetails = By.xpath("//div[text()='Type']//following-sibling::div");
    public static By departmentTypeDetails = By.xpath("//div[text()='Department']//following-sibling::div");
    public static By categoryTypeDetails = By.xpath("//div[text()='Category']//following-sibling::div");
    public static By subCategoryTypeDetails = By.xpath("//div[text()='Sub Category']//following-sibling::div");
    public static By descriptionTypeDetails = By.xpath("//div[text()='Description']//following-sibling::div");
    public static By subjectDetails = By.xpath("//span[@class='header_title__gA+hp']");
    public static By attachedfiledetails = By.xpath("//div[text()='Attached files']//following-sibling::div");
    public static By attachedFileTitle = By.xpath("//div[text()='Attached files']");
    public static By showTimeLineButton = By.xpath("//div[text()='Show Timeline']");
    public static By createdOnDetails = By.xpath("//div[text()='Created on']//following-sibling::div");
    public static By createdDateTimeLine = By.xpath("(//span[@class='mb-1 timeline_timeLineTime__5GOs2'])[1]");
    public static By createdTime = By.xpath("(//span[@class='timeline_timeLineTime__5GOs2'])[1]");


}
