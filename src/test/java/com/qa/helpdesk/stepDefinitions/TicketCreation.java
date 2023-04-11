package com.qa.helpdesk.stepDefinitions;

import com.gemini.generic.reporting.GemTestReporter;
import com.gemini.generic.reporting.STATUS;
import com.gemini.generic.ui.utils.DriverAction;
import com.gemini.generic.ui.utils.DriverManager;
import com.qa.helpdesk.genericUtils.Utils;
import com.qa.helpdesk.locators.SignInLocators;
import com.qa.helpdesk.locators.TicketCreationLocators;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.List;


public class TicketCreation {
    final Logger logger = LoggerFactory.getLogger(TicketCreation.class);
    static Utils util = new Utils();

    @Given("^User clicks on New Ticket Button$")
    public void clickNewTicketBtn() {
        try {
            if (DriverAction.isExist(SignInLocators.employeeView)) {
                util.clickOnNewTicketBtn();
            } else {
                GemTestReporter.addTestStep("Not Exists", "S", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("Exception occurred!", e);
            GemTestReporter.addTestStep("Not Verified", "Exception arises" + e.getMessage(), STATUS.FAIL, DriverAction.takeSnapShot());
            throw e;
        }
    }

    @When("^User Creates an Incident Ticket, fills the Subject - (.+), Description - (.+), and Department - (.+)$")
    public void createIncidentTicket(String Subject, String Description, String Department) {
        try {
            DriverAction.typeText(TicketCreationLocators.subject, Subject, "Subject");
            DriverAction.typeText(TicketCreationLocators.description, Description, "Description");
            DriverAction.click(TicketCreationLocators.typeOption);
            DriverAction.click(TicketCreationLocators.ticketTypeIncident);
            DriverAction.click(TicketCreationLocators.departmentOption); //Department select
            DriverAction.click(TicketCreationLocators.optionType(Department));
        } catch (Exception e) {
            logger.info("Exception occurred!", e);
            GemTestReporter.addTestStep("Not Verified", "Exception arises" + e.getMessage(), STATUS.FAIL, DriverAction.takeSnapShot());
            throw e;
        }
    }

    @When("^User Creates a Request Ticket, fills the Subject - (.+), Description - (.+), Department - (.+), Category - (.+), and Sub-category - (.+)$")
    public void createRequestTicket(String Subject, String Description, String Department, String Category, String subCategory) {
        try {
            DriverAction.typeText(TicketCreationLocators.subject, Subject, "Subject");
            DriverAction.typeText(TicketCreationLocators.description, Description, "Description");
            DriverAction.click(TicketCreationLocators.typeOption);
            DriverAction.click(TicketCreationLocators.ticketTypeRequest);
            Utils.waitForElement(TicketCreationLocators.departmentOption, 20);
            DriverAction.click(TicketCreationLocators.departmentOption); //Department select
            Utils.waitForElement(TicketCreationLocators.optionType(Department), 20);
            DriverAction.click(TicketCreationLocators.optionType(Department));
            Utils.waitForElement(TicketCreationLocators.chooseCategory, 20);
            DriverAction.click(TicketCreationLocators.chooseCategory);
            Utils.waitForElement(TicketCreationLocators.optionType(Category), 20);
            DriverAction.click(TicketCreationLocators.optionType(Category));
            Utils.waitForElement(TicketCreationLocators.chooseSubCategory, 20);
            DriverAction.click(TicketCreationLocators.chooseSubCategory);
            Utils.waitForElement(TicketCreationLocators.optionType(subCategory), 20);
            DriverAction.click(TicketCreationLocators.optionType(subCategory));
            DriverAction.waitSec(5);
        } catch (Exception e) {
            logger.info("Exception occurred!", e);
            GemTestReporter.addTestStep("Not Verified", "Exception arises" + e.getMessage(), STATUS.FAIL, DriverAction.takeSnapShot());
            throw e;
        }
    }

    @And("^User uploads the File - (.+)$")
    public void uploadFile(String uploadFiles) {
        util.uploadTheFile(uploadFiles);
    }

    @And("^User upload the invalid size (.+) and verifies (.+) is not uploaded$")
    public void invalidSizeFileUpload(String File, String invalidSizeFile) {
        try {
            DriverAction.waitSec(3);
            String FilePath = System.getProperty("user.dir") + File;
            DriverAction.fileUpload(TicketCreationLocators.fileUpload, FilePath);
            DriverAction.waitSec(3);
        } catch (Exception e) {
            logger.info("Exception occurred!", e);
            GemTestReporter.addTestStep("Verify Invalid Upload File", "Upload " + invalidSizeFile + " Size File is Not Successful expectedly ", STATUS.PASS, DriverAction.takeSnapShot());
        }
    }


    @And("^User upload the multiple (.+)$")
    public void uploadMultipleFile(String uploadMultipleFiles) {
        try {
            String[] multipleFiles = uploadMultipleFiles.split(",");
            DriverAction.waitSec(1);
            for (String s : multipleFiles) {
                util.uploadTheFile(s);
            }
        } catch (Exception e) {
            logger.info("Exception occurred!", e);
            GemTestReporter.addTestStep("Not Verified", "Exception arises" + e.getMessage(), STATUS.FAIL, DriverAction.takeSnapShot());
            throw e;
        }
    }

    @Then("^User verify the multiple uploaded (.+)$")
    public void verifyMultipleUploadFiles(String uploadMultipleFiles) {
        String[] multipleFiles = uploadMultipleFiles.split(",");
        int numberOfFiles = multipleFiles.length;
//        DriverAction.waitSec(2);
        Utils.waitForElement(TicketCreationLocators.previewBtn, 10);
        DriverAction.click(TicketCreationLocators.previewBtn, "Preview Button");
        List<WebElement> fileCount = DriverAction.getElements(TicketCreationLocators.previewAttachments);
        if (numberOfFiles == fileCount.size()) {
            GemTestReporter.addTestStep("Multiple Files Verification", numberOfFiles + " Files has been uploaded successfully", STATUS.PASS, DriverAction.takeSnapShot());
        } else {
            GemTestReporter.addTestStep("Multiple Files Verification", numberOfFiles + " Files not uploaded successfully", STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }

    @Then("^User verifies the Incident Ticket with Subject - (.+), Description - (.+), and Department - (.+)$")
    public void verifyIncidentTicket(String Subject, String Description, String Department) {
        try {
//            DriverAction.waitUntilElementAppear(TicketCreationLocators.previewBtn, 3);
            Utils.waitForElement(TicketCreationLocators.previewBtn, 10);
            DriverAction.click(TicketCreationLocators.previewBtn);
            util.previewIncidentTicket(Subject, Description, Department);
        } catch (Exception e) {
            logger.info("Exception occurred!", e);
            GemTestReporter.addTestStep("Not Verified", "Exception arises" + e.getMessage(), STATUS.FAIL, DriverAction.takeSnapShot());
            throw e;
        }
    }

    @And("^User clicks on Submit button$")
    public void clickSubmitButton() {
        try {
            util.clickSubmit();
            DriverAction.waitSec(3);
        } catch (Exception e) {
            logger.info("Exception occurred!", e);
            GemTestReporter.addTestStep("Not Verified", "Exception arises" + e.getMessage(), STATUS.FAIL, DriverAction.takeSnapShot());
            throw e;
        }
    }

    @And("^User clicks on Continue button$")
    public void clickContinueButton() {
        try {
            DriverAction.waitSec(3);
            util.clickContinue();
        } catch (Exception e) {
            logger.info("Exception occurred!", e);
            GemTestReporter.addTestStep("Not Verified", "Exception arises" + e.getMessage(), STATUS.FAIL, DriverAction.takeSnapShot());
            throw e;
        }
    }

    @Then("^User verify the Incident Ticket with (.+), (.+), and (.+) from the details page$")
    public void verifyIncidentTicketDetails(String Subject, String Description, String Department) {
        DriverAction.waitSec(3);
        String subjectDetailsText = DriverAction.getElementText(TicketCreationLocators.subjectDetails);
        String descriptionDetails = DriverAction.getElementText(TicketCreationLocators.descriptionTypeDetails);
        String ticketTypeDetails = DriverAction.getElementText(TicketCreationLocators.ticketTypeDetails);
        String departmentDetails = DriverAction.getElementText(TicketCreationLocators.departmentTypeDetails);

        String tickedID = util.getTicketID();
        String subjectPart = tickedID + " | " + subjectDetailsText;
        String newSubPart = subjectPart.substring(3);


        if (ticketTypeDetails.equalsIgnoreCase("Incident")) {
            GemTestReporter.addTestStep("Verify Incident Ticket Type", "Incident Ticket Type is Verified", STATUS.PASS, DriverAction.takeSnapShot());
        } else {
            GemTestReporter.addTestStep("Verify Incident Ticket Type", "Incident Ticket Type is Not Verified", STATUS.FAIL, DriverAction.takeSnapShot());
        }
        if (newSubPart.equalsIgnoreCase(subjectDetailsText)) {
            GemTestReporter.addTestStep("Verify Subject", "Subject is Verified", STATUS.PASS, DriverAction.takeSnapShot());
        } else {
            GemTestReporter.addTestStep("Verify Subject", "Subject is Not Verified", STATUS.FAIL, DriverAction.takeSnapShot());
        }
        if (descriptionDetails.equalsIgnoreCase(Description)) {
            GemTestReporter.addTestStep("Verify Description", "Description is Verified", STATUS.PASS, DriverAction.takeSnapShot());
        } else {
            GemTestReporter.addTestStep("Verify Description", "Description is Not Verified", STATUS.FAIL, DriverAction.takeSnapShot());
        }
        if (departmentDetails.equalsIgnoreCase(Department)) {
            GemTestReporter.addTestStep("Verify Department Type", "Department Type is Verified", STATUS.PASS, DriverAction.takeSnapShot());
        } else {
            GemTestReporter.addTestStep("Verify Department Type", "Department Type is Not Verified", STATUS.FAIL, DriverAction.takeSnapShot());
        }

    }

    @Then("^User verify the Request Ticket with (.+), (.+), (.+), (.+), and (.+) from the details page$")
    public void verifyRequestTicketDetails(String Subject, String Description, String Department, String Category, String SubCategory) {
        try {
            DriverAction.waitSec(3);
            String subjectDetailsText = DriverAction.getElementText(TicketCreationLocators.subjectDetails);
            String descriptionDetails = DriverAction.getElementText(TicketCreationLocators.descriptionTypeDetails);
            String ticketTypeDetails = DriverAction.getElementText(TicketCreationLocators.ticketTypeDetails);
            String departmentDetails = DriverAction.getElementText(TicketCreationLocators.departmentTypeDetails);
            String CategoryPreview = DriverAction.getElementText(TicketCreationLocators.categoryTypeDetails);
            String SubCategoryPreview = DriverAction.getElementText(TicketCreationLocators.subCategoryTypeDetails);

            String tickedID = util.getTicketID();
            String subjectPart = tickedID + " | " + subjectDetailsText;
            String newSubPart = subjectPart.substring(3);

            if (ticketTypeDetails.equalsIgnoreCase("Request")) {
                GemTestReporter.addTestStep("Verify Request Ticket Type", "Incident Ticket Type is Verified", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Verify Request Ticket Type", "Incident Ticket Type is Not Verified", STATUS.FAIL, DriverAction.takeSnapShot());
            }
            if (newSubPart.equalsIgnoreCase(subjectDetailsText)) {
                GemTestReporter.addTestStep("Verify Subject", "Subject is Verified", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Verify Subject", "Subject is Not Verified", STATUS.FAIL, DriverAction.takeSnapShot());
            }
            if (descriptionDetails.equalsIgnoreCase(Description)) {
                GemTestReporter.addTestStep("Verify Description", "Description is Verified", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Verify Description", "Description is Not Verified", STATUS.FAIL, DriverAction.takeSnapShot());
            }
            if (departmentDetails.equalsIgnoreCase(Department)) {
                GemTestReporter.addTestStep("Verify Department Type", "Department Type is Verified", STATUS.PASS, DriverAction.takeSnapShot());

            } else {
                GemTestReporter.addTestStep("Verify Department Type", "Department Type is Not Verified", STATUS.FAIL, DriverAction.takeSnapShot());
            }
            if (CategoryPreview.equalsIgnoreCase(Category)) {
                GemTestReporter.addTestStep("Verify Category Type", "Category Type is Verified", STATUS.PASS, DriverAction.takeSnapShot());

            } else {
                GemTestReporter.addTestStep("Verify Category Type", "Category Type is Not Verified", STATUS.FAIL, DriverAction.takeSnapShot());
            }
            if (SubCategoryPreview.equalsIgnoreCase(SubCategory)) {
                GemTestReporter.addTestStep("Verify Sub-category Type", "Sub-category Type is Verified", STATUS.PASS, DriverAction.takeSnapShot());

            } else {
                GemTestReporter.addTestStep("Verify Sub-category Type", "Sub-category Type is Not Verified", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("Exception occurred!", e);
            GemTestReporter.addTestStep("Not Verified", "Exception arises" + e.getMessage(), STATUS.FAIL, DriverAction.takeSnapShot());
            throw e;
        }
    }

    @Then("^User verifies the Request Ticket with Subject - (.+), Description - (.+), Department - (.+), Category - (.+), and Sub-category - (.+)$")
    public void verifyTheRequestTicket(String Subject, String Description, String Department, String Category, String SubCategory) {
        try {
//            DriverAction.waitUntilElementAppear(TicketCreationLocators.previewBtn, 3);
            Utils.waitForElement(TicketCreationLocators.previewBtn, 10);
            DriverAction.click(TicketCreationLocators.previewBtn);
            util.previewRequestTicket(Subject, Description, Department, Category, SubCategory);
        } catch (Exception e) {
            logger.info("Exception occurred!", e);
            GemTestReporter.addTestStep("Not Verified", "Exception arises" + e.getMessage(), STATUS.FAIL, DriverAction.takeSnapShot());
            throw e;
        }
    }

    @Then("^User verifies the file upload$")
    public void verifyFileUpload() throws AWTException {
        try {
            util.verifyUpload();
        } catch (AWTException e) {
            logger.info("Exception occurred!", e);
            GemTestReporter.addTestStep("Not Verified", "Exception arises" + e.getMessage(), STATUS.FAIL, DriverAction.takeSnapShot());
            throw e;
        }
    }

    @Then("^User verify the File Upload in details$")
    public void fileUploadDetailsVerification() {
        try {
            if ((DriverAction.isExist(TicketCreationLocators.attachedfiledetails)) && DriverAction.isExist(TicketCreationLocators.attachedFileTitle)) {
                GemTestReporter.addTestStep("File Upload Verification in Ticket Details", "File Upload is Verified", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("File Upload Verification in Ticket Details", "File Upload is Not Verified", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("Exception occurred!", e);
            GemTestReporter.addTestStep("Not Verified", "Exception arises" + e.getMessage(), STATUS.FAIL, DriverAction.takeSnapShot());
            throw e;
        }
    }


    @Then("^User verifies that the Ticket Creation Form has not been submitted$")
    public void verifyTicketShouldNotBeCreated() {
        try {
            if (DriverAction.isExist(TicketCreationLocators.submitBtn)) {
                GemTestReporter.addTestStep("Verify Ticket Creation Form", "Ticket has not been created as Mandatory fields are blank!", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Verify Ticket Creation Form", "Ticket has been created even Mandatory fields are blank!", STATUS.FAIL, DriverAction.takeSnapShot());
            }

        } catch (Exception e) {
            logger.info("Exception occurred!", e);
            GemTestReporter.addTestStep("Not Verified", "Exception arises" + e.getMessage(), STATUS.FAIL, DriverAction.takeSnapShot());
            throw e;
        }
    }

    @Then("^User adds a (.+) and verifies it$")
    public void addComment(String comment) {
        try {
            Utils.waitForElement(TicketCreationLocators.commentArea, 20);
            if (DriverAction.isExist(TicketCreationLocators.commentArea)) {
                DriverAction.typeText(TicketCreationLocators.commentArea, comment);
                DriverAction.scrollIntoView(TicketCreationLocators.updateButton);
                Utils.waitForElement(TicketCreationLocators.updateButton, 10);
//                DriverAction.waitSec(3);
                DriverAction.click(TicketCreationLocators.updateButton);
//                Utils.waitForElement(TicketCreationLocators.addedComment, 10);
                DriverAction.waitSec(3);
                if (DriverAction.isExist(TicketCreationLocators.addedComment)) {
                    DriverAction.scrollToBottom();
                    DriverAction.waitSec(5);
                    String addedCommentText = DriverAction.getElementText(TicketCreationLocators.addedComment);
                    System.out.println(addedCommentText);
                    if (addedCommentText.equalsIgnoreCase(comment)) {
                        GemTestReporter.addTestStep("Comment", "Added comment is Verified!", STATUS.PASS, DriverAction.takeSnapShot());
                    } else {
                        GemTestReporter.addTestStep("Comment", "Added comment is not Verified!", STATUS.FAIL, DriverAction.takeSnapShot());
                    }
                } else {
                    GemTestReporter.addTestStep("Comment", "Added comment does not exist!", STATUS.FAIL, DriverAction.takeSnapShot());
                }
            } else {
                GemTestReporter.addTestStep("Comment Area", "Comment area does not Exist!", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("Exception occurred!", e);
            GemTestReporter.addTestStep("Not Verified", "Exception arises" + e.getMessage(), STATUS.FAIL, DriverAction.takeSnapShot());
            throw e;
        }
    }

    @Then("^User verifies the file upload with valid (.+)$")
    public void verifyValidUploadExtension(String extensionName) throws AWTException {
        try {
//            DriverAction.waitSec(3);
            Utils.waitForElement(TicketCreationLocators.previewBtn, 10);
//            DriverAction.waitUntilElementAppear(TicketCreationLocators.previewBtn, 3);
            DriverAction.click(TicketCreationLocators.previewBtn);
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_SUBTRACT);
            robot.keyRelease(KeyEvent.VK_SUBTRACT);
            robot.keyPress(KeyEvent.VK_SUBTRACT);
            robot.keyRelease(KeyEvent.VK_SUBTRACT);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            DriverAction.waitSec(3);
            if (DriverAction.isExist(TicketCreationLocators.previewAttachments)) {
                GemTestReporter.addTestStep("Verify Upload File", "Upload " + extensionName + " File is Verified ", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Verify Upload File", "Upload " + extensionName + " File is Failed ", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (AWTException e) {
            logger.info("Exception occurred!", e);
            GemTestReporter.addTestStep("Not Verified", "Exception arises" + e.getMessage(), STATUS.FAIL, DriverAction.takeSnapShot());
            throw e;
        }
    }

    @Then("^User verifies that file is not uploaded with Invalid (.+)$")
    public void verifyInvalidFileUpload(String extensionName) throws AWTException {
        try {
//            DriverAction.waitSec(3);
            Utils.waitForElement(TicketCreationLocators.previewBtn, 10);
            DriverAction.click(TicketCreationLocators.previewBtn);
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_SUBTRACT);
            robot.keyRelease(KeyEvent.VK_SUBTRACT);
            robot.keyPress(KeyEvent.VK_SUBTRACT);
            robot.keyRelease(KeyEvent.VK_SUBTRACT);
            robot.keyRelease(KeyEvent.VK_CONTROL);

            DriverAction.waitSec(3);
            if (!(DriverAction.isExist(TicketCreationLocators.previewAttachments))) {
                GemTestReporter.addTestStep("Verify Invalid Upload File", "Upload " + extensionName + " File is Not Successful ", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Verify Invalid Upload File", "Upload " + extensionName + " File is successful unexpectedly  ", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (AWTException e) {
            logger.info("Exception occurred!", e);
            GemTestReporter.addTestStep("Not Verified", "Exception arises" + e.getMessage(), STATUS.FAIL, DriverAction.takeSnapShot());
            throw e;
        }
    }


    @Then("^User verifies the file upload failure with invalid (.+)$")
    public void verifyFileUploadInvalidSize(String invalidSizeFile) {
        try {
            String invalidSizeMessage = "File size should be maximum 2MB per attachment";
            //  DriverAction.waitSec(5);
            Alert alert = DriverManager.getWebDriver().switchTo().alert();
            String alertText = alert.getText();
            alert.accept();


            if (alertText.contains(invalidSizeMessage)) {
                GemTestReporter.addTestStep("Verify Invalid Upload File", "Upload " + invalidSizeFile + " Size File is Not Successful ", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Verify Invalid Upload File", "Upload " + invalidSizeFile + " Size File is successful unexpectedly  ", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("Exception occurred!", e);
            GemTestReporter.addTestStep("Not Verified", "Exception arises" + e.getMessage(), STATUS.FAIL, DriverAction.takeSnapShot());
//            throw e;
        }
    }
}


