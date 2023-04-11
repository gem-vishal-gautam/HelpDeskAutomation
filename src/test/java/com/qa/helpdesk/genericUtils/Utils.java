package com.qa.helpdesk.genericUtils;

import com.gemini.generic.reporting.GemTestReporter;
import com.gemini.generic.reporting.STATUS;
import com.gemini.generic.ui.utils.DriverAction;
import com.gemini.generic.ui.utils.DriverManager;
import com.qa.helpdesk.locators.DashboardLocators;
import com.qa.helpdesk.locators.SignInLocators;
import com.qa.helpdesk.locators.TicketCreationLocators;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Utils {

    final Logger logger = LoggerFactory.getLogger(Utils.class);


    public static void waitForElement(By locator, int duration) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getWebDriver(), Duration.ofSeconds((long) duration));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
//    public static void waitUntilElementAppear(By locator, int duration) {
//        WebElement webElement = DriverAction.getElement(locator);
//        WebDriverWait webDriverWait = new WebDriverWait(DriverManager.getWebDriver(), Duration.ofSeconds(duration));
//        webDriverWait.until(ExpectedConditions.visibilityOf(webElement));
//    }

//    public static void presenceOfElement(By elementXpath, int time) {
//        try {
//            WebDriverWait wait = new WebDriverWait(DriverManager.getWebDriver(), Duration.ofSeconds(time));
//            wait.until(ExpectedConditions.presenceOfElementLocated(elementXpath));
//        } catch (Exception e) {
//            GemTestReporter.addTestStep("Presence of Element ", "Element is not present", STATUS.FAIL);
//        }
//    }

    public String getDate() {
        try {
            LocalDate currentDate = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String formattedDate;
            formattedDate = currentDate.format(formatter);
            return formattedDate;
        } catch (Exception e) {
            logger.info("Exception Occurred!", e);
            GemTestReporter.addTestStep("ERROR", "Exception Occurred :" + e.getMessage(), STATUS.FAIL, DriverAction.takeSnapShot());
            throw e;
        }
    }

    public int monthCount() {
        try {
            List<String> monthCounter = DriverAction.getElementsText(DashboardLocators.monthCount);
            return monthCounter.size();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int weekDayCount() {
        try {
            List<String> weekDayCounter = DriverAction.getElementsText(DashboardLocators.weekDayCount);
            return weekDayCounter.size();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void clickSearchBoxCross() {
        try {
            DriverAction.click(DashboardLocators.searchBoxCrossIcon, "Cross Icon");
        } catch (Exception e) {
            logger.info("Exception Occurred!", e);
            GemTestReporter.addTestStep("ERROR", "Exception Occurred :" + e.getMessage(), STATUS.FAIL, DriverAction.takeSnapShot());
            throw e;
        }
    }

    public void clearData() {
        try {
//            Utils.waitForElement(DashboardLocators.calendarImg, 10);
            DriverAction.waitSec(3);
            DriverAction.click(DashboardLocators.calendarImg, "Calendar icon");
            DriverAction.scrollIntoView(DashboardLocators.notificationIcon);
//            DriverAction.waitUntilElementAppear(DashboardLocators.clearData, 3);
//            Utils.waitForElement(DashboardLocators.clearData, 10);
            DriverAction.waitSec(3);
            DriverAction.click(DashboardLocators.clearData);
        } catch (Exception e) {
            logger.info("Exception Occurred!", e);
            GemTestReporter.addTestStep("ERROR", "Exception Occurred :" + e.getMessage(), STATUS.FAIL, DriverAction.takeSnapShot());
            throw e;
        }
    }

    public void clickOnFilterBtn() {
        try {
            //   DriverAction.waitUntilElementAppear(DashboardLocators.filterBtn, 3);
//            DriverAction.waitSec(2);
            Utils.waitForElement(DashboardLocators.filterBtn, 10);
            DriverAction.click(DashboardLocators.filterBtn, "Filter Button");
        } catch (Exception e) {
            logger.info("Exception Occurred!", e);
            GemTestReporter.addTestStep("ERROR", "Exception Occurred :" + e.getMessage(), STATUS.FAIL, DriverAction.takeSnapShot());
            throw e;
        }
    }

    public void clickOnNewTicketBtn() {
        try {
            Utils.waitForElement(TicketCreationLocators.newTicket, 45);
            DriverAction.click(TicketCreationLocators.newTicket);
        } catch (Exception e) {
            logger.info("Exception Occurred!", e);
            GemTestReporter.addTestStep("ERROR", "Exception Occurred :" + e.getMessage(), STATUS.FAIL, DriverAction.takeSnapShot());
            throw e;
        }
    }

    public void columnSorting(String colName) {
        try {
            List<String> colItems = null;
            List<String> colItems2 = null;

            Select rowsPerPage = new Select(DriverManager.getWebDriver().findElement(DashboardLocators.rowsPerPageDropDown));
            DriverAction.waitSec(2);
            rowsPerPage.selectByValue("25");
            DriverAction.waitSec(2);

            switch (colName) {
                case ("ID"):
                    colItems = DriverAction.getElementsText(DashboardLocators.idCol);
                    DriverAction.click(DashboardLocators.getSortingHeading(colName));
                    colItems2 = DriverAction.getElementsText(DashboardLocators.idCol);
                    break;

                case ("Subject"):
                    colItems = DriverAction.getElementsText(DashboardLocators.subjectCol);
                    DriverAction.click(DashboardLocators.getSortingHeading(colName));
                    colItems2 = DriverAction.getElementsText(DashboardLocators.subjectCol);
                    break;

                case ("Department"):
                    colItems = DriverAction.getElementsText(DashboardLocators.departmentCol);
                    DriverAction.click(DashboardLocators.getSortingHeading(colName));
                    colItems2 = DriverAction.getElementsText(DashboardLocators.departmentCol);
                    break;

                case ("Created on"):
                    colItems = DriverAction.getElementsText(DashboardLocators.createdOnCol);
                    DriverAction.click(DashboardLocators.getSortingHeading(colName));
                    colItems2 = DriverAction.getElementsText(DashboardLocators.createdOnCol);
                    break;

                case ("Assigned to"):
                    colItems = DriverAction.getElementsText(DashboardLocators.assignedToCol);
                    DriverAction.click(DashboardLocators.getSortingHeading(colName));
                    colItems2 = DriverAction.getElementsText(DashboardLocators.assignedToCol);
                    break;


                case ("Status"):
                    colItems = DriverAction.getElementsText(DashboardLocators.statusCol);
                    DriverAction.click(DashboardLocators.getSortingHeading(colName));
                    colItems2 = DriverAction.getElementsText(DashboardLocators.statusCol);
                    break;
            }
            if (!(colItems == (colItems2))) {
                GemTestReporter.addTestStep("Column Sorted", "Column is Sorted", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Column NOT Sorted", "Column is NOT Sorted", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("Exception Occurred!", e);
            GemTestReporter.addTestStep("ERROR", "Exception Occurred :" + e.getMessage(), STATUS.FAIL, DriverAction.takeSnapShot());
            throw e;
        }
    }

    public void closeFilterTab() {
        try {
            //   DriverAction.waitUntilElementAppear(DashboardLocators.crossIcon, 3);
            DriverAction.click(DashboardLocators.crossIcon, "Close Button");
        } catch (Exception e) {
            logger.info("Exception Occurred!", e);
            GemTestReporter.addTestStep("ERROR", "Exception Occurred :" + e.getMessage(), STATUS.FAIL, DriverAction.takeSnapShot());
            throw e;
        }
    }

    public void clickSubmit() {
        try {
//            DriverAction.waitUntilElementAppear(TicketCreationLocators.submitBtn, 3);
            Utils.waitForElement(TicketCreationLocators.submitBtn, 10);
            DriverAction.click(TicketCreationLocators.submitBtn);
        } catch (Exception e) {
            logger.info("Exception Occurred!", e);
            GemTestReporter.addTestStep("ERROR", "Exception Occurred :" + e.getMessage(), STATUS.FAIL, DriverAction.takeSnapShot());
            throw e;
        }
    }

    public void clickContinue() {
        try {
            Utils.waitForElement(TicketCreationLocators.continueBtn, 30);
            DriverAction.click(TicketCreationLocators.continueBtn, "continue");
        } catch (Exception e) {
            logger.info("Exception Occurred!", e);
            GemTestReporter.addTestStep("ERROR", "Exception Occurred :" + e.getMessage(), STATUS.FAIL, DriverAction.takeSnapShot());
            throw e;
        }
    }

    public void uploadTheFile(String File) {
        try {
            DriverAction.waitSec(3);
            String FilePath = System.getProperty("user.dir") + File;
            DriverAction.fileUpload(TicketCreationLocators.fileUpload, FilePath);
            DriverAction.waitSec(3);
        } catch (Exception e) {
            logger.info("Exception Occurred!", e);
            GemTestReporter.addTestStep("ERROR", "Exception Occurred :" + e.getMessage(), STATUS.WARN, DriverAction.takeSnapShot());
            //  throw e;
        }
    }

    public void verifyUpload() throws AWTException {
        try {
            DriverAction.waitSec(5);
//            DriverAction.waitUntilElementAppear(TicketCreationLocators.previewBtn, 3);
            if (DriverAction.isExist(TicketCreationLocators.previewBtn)) {
                Utils.waitForElement(TicketCreationLocators.previewBtn, 10);
                DriverAction.click(TicketCreationLocators.previewBtn);
            } else {
                GemTestReporter.addTestStep("Preview Button", "Already in Preview Page", STATUS.PASS, DriverAction.takeSnapShot());
            }
//            Utils.waitForElement(TicketCreationLocators.previewBtn, 10);
//            DriverAction.click(TicketCreationLocators.previewBtn);
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_SUBTRACT);
            robot.keyRelease(KeyEvent.VK_SUBTRACT);
            robot.keyPress(KeyEvent.VK_SUBTRACT);
            robot.keyRelease(KeyEvent.VK_SUBTRACT);
            robot.keyRelease(KeyEvent.VK_CONTROL);

            DriverAction.waitSec(3);
            if (DriverAction.isExist(TicketCreationLocators.previewAttachments)) {
                GemTestReporter.addTestStep("Verify Upload File", "Upload File is Verified", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Verify Upload File", "Upload File is Not Verified", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (AWTException e) {
            logger.info("Exception occurred!", e);
            GemTestReporter.addTestStep("Not Verified", "Exception arises" + e.getMessage(), STATUS.FAIL, DriverAction.takeSnapShot());
            throw e;
        }
    }

    public void previewIncidentTicket(String Sub, String Desc, String Dept) {
        try {
            String subjectPreview = DriverAction.getElementText(TicketCreationLocators.previewSubject);
            String DescriptionPreview = DriverAction.getElementText(TicketCreationLocators.previewDescription);
            String TypePreview = DriverAction.getElementText(TicketCreationLocators.previewType);
            String DepartmentPreview = DriverAction.getElementText(TicketCreationLocators.previewDepartmentType);

            if (TypePreview.equalsIgnoreCase("Incident")) {
                GemTestReporter.addTestStep("Verify Incident Ticket Type", "Incident Ticket Type is Verified", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Verify Incident Ticket Type", "Incident Ticket Type is Not Verified", STATUS.FAIL, DriverAction.takeSnapShot());
            }
            if (subjectPreview.equalsIgnoreCase(Sub)) {
                GemTestReporter.addTestStep("Verify Subject", "Subject is Verified", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Verify Subject", "Subject is Not Verified", STATUS.FAIL, DriverAction.takeSnapShot());
            }
            if (DescriptionPreview.equalsIgnoreCase(Desc)) {
                GemTestReporter.addTestStep("Verify Description", "Description is Verified", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Verify Description", "Description is Not Verified", STATUS.FAIL, DriverAction.takeSnapShot());
            }
            if (DepartmentPreview.equalsIgnoreCase(Dept)) {
                GemTestReporter.addTestStep("Verify Department Type", "Department Type is Verified", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Verify Department Type", "Department Type is Not Verified", STATUS.FAIL, DriverAction.takeSnapShot());
            }
            DriverAction.waitSec(2);
        } catch (Exception e) {
            logger.info("Exception occurred!", e);
            GemTestReporter.addTestStep("Not Verified", "Exception arises" + e.getMessage(), STATUS.FAIL, DriverAction.takeSnapShot());
            throw e;
        }
    }

    public void previewRequestTicket(String Sub, String Desc, String Dept, String Ctgy, String subCtgy) {
        try {
            String subjectPreview = DriverAction.getElementText(TicketCreationLocators.previewSubject);
            String descriptionPreview = DriverAction.getElementText(TicketCreationLocators.previewDescription);
            String typePreview = DriverAction.getElementText(TicketCreationLocators.previewType);
            String departmentPreview = DriverAction.getElementText(TicketCreationLocators.previewDepartmentType);
            String categoryPreview = DriverAction.getElementText(TicketCreationLocators.previewCategory);
            String subCategoryPreview = DriverAction.getElementText(TicketCreationLocators.previewSubCategory);

            if (typePreview.equalsIgnoreCase("Request")) {
                GemTestReporter.addTestStep("Verify Request Ticket Type", "Incident Ticket Type is Verified", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Verify Request Ticket Type", "Incident Ticket Type is Not Verified", STATUS.FAIL, DriverAction.takeSnapShot());
            }
            if (subjectPreview.equalsIgnoreCase(Sub)) {
                GemTestReporter.addTestStep("Verify Subject", "Subject is Verified", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Verify Subject", "Subject is Not Verified", STATUS.FAIL, DriverAction.takeSnapShot());
            }
            if (descriptionPreview.equalsIgnoreCase(Desc)) {
                GemTestReporter.addTestStep("Verify Description", "Description is Verified", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Verify Description", "Description is Not Verified", STATUS.FAIL, DriverAction.takeSnapShot());
            }
            if (departmentPreview.equalsIgnoreCase(Dept)) {
                GemTestReporter.addTestStep("Verify Department Type", "Department Type is Verified", STATUS.PASS, DriverAction.takeSnapShot());

            } else {
                GemTestReporter.addTestStep("Verify Department Type", "Department Type is Not Verified", STATUS.FAIL, DriverAction.takeSnapShot());
            }
            if (categoryPreview.equalsIgnoreCase(Ctgy)) {
                GemTestReporter.addTestStep("Verify Category Type", "Category Type is Verified", STATUS.PASS, DriverAction.takeSnapShot());

            } else {
                GemTestReporter.addTestStep("Verify Category Type", "Category Type is Not Verified", STATUS.FAIL, DriverAction.takeSnapShot());
            }
            if (subCategoryPreview.equalsIgnoreCase(subCtgy)) {
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

    public String getTicketID() {
        return DriverAction.getElementText(DashboardLocators.tickedID);
    }

    public void clickOnUserGuideBtn() {
        try {
            DriverAction.click(DashboardLocators.userGuideIcon);
        } catch (Exception e) {
            logger.info("Exception Occurred!", e);
            GemTestReporter.addTestStep("ERROR", "Exception Occurred :" + e.getMessage(), STATUS.FAIL, DriverAction.takeSnapShot());
            throw e;
        }
    }

    public void clickOnNotificationBtn() {
        try {
            DriverAction.waitUntilElementAppear(DashboardLocators.notificationIcon, 3);
            DriverAction.click(DashboardLocators.notificationIcon, "Notification Icon");
        } catch (Exception e) {
            logger.info("Exception Occurred!", e);
            GemTestReporter.addTestStep("ERROR", "Exception occurred :" + e.getMessage(), STATUS.FAIL, DriverAction.takeSnapShot());
            throw e;
        }
    }

    public void clickOnShowMoreBtn() {
        try {
            DriverAction.waitUntilElementAppear(DashboardLocators.showMore, 3);
            DriverAction.click(DashboardLocators.showMore, "Show More");
        } catch (Exception e) {
            logger.info("Exception occurred!", e);
            GemTestReporter.addTestStep("ERROR", "Exception occurred :" + e.getMessage(), STATUS.FAIL, DriverAction.takeSnapShot());
            throw e;
        }
    }

    public void clickOnUnreadTab() {
        try {
            DriverAction.waitUntilElementAppear(DashboardLocators.unreadOption, 3);
            DriverAction.click(DashboardLocators.unreadOption);
        } catch (Exception e) {
            logger.info("Exception occurred!", e);
            GemTestReporter.addTestStep("ERROR", "Exception occurred :" + e.getMessage(), STATUS.FAIL, DriverAction.takeSnapShot());
            throw e;
        }
    }

    public void switchesToEmployeeView() {
        try {
            DriverAction.waitSec(4);
            DriverAction.click(SignInLocators.supportView, "Support View");
            DriverAction.waitSec(4);
            DriverAction.click(SignInLocators.employeeView, "Employee View");
            DriverAction.waitSec(2);
        } catch (Exception e) {
            logger.info("Exception occurred!", e);
            GemTestReporter.addTestStep("ERROR", "Exception occurred :" + e.getMessage(), STATUS.FAIL, DriverAction.takeSnapShot());
            throw e;
        }
    }

    public void clickSideMenuButton() {
        try {
            if (DriverAction.isExist(DashboardLocators.sideMenuButton)) {
                DriverAction.click(DashboardLocators.sideMenuButton, "Side Menu Button");
            } else {
                GemTestReporter.addTestStep("Side Menu Button", "Side Menu Button does not Exist!", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("Exception occurred!", e);
            GemTestReporter.addTestStep("ERROR", "Exception occurred :" + e.getMessage(), STATUS.FAIL, DriverAction.takeSnapShot());
            throw e;
        }
    }

    public void clickPreviousButton() {
        try {
            if (DriverAction.isExist(DashboardLocators.previousBtn)) {
                DriverAction.click(DashboardLocators.previousBtn, "Previous Button");
            } else {
                GemTestReporter.addTestStep("Previous Button", "Previous Button does not Exist!", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("Exception occurred!", e);
            GemTestReporter.addTestStep("ERROR", "Exception occurred :" + e.getMessage(), STATUS.FAIL, DriverAction.takeSnapShot());
            throw e;
        }
    }

    public void clickNextButton() {
        try {
            if (DriverAction.isExist(DashboardLocators.nextBtn)) {
                DriverAction.click(DashboardLocators.nextBtn, "Next Button");
            } else {
                GemTestReporter.addTestStep("Next Button", "Next Button does not Exist!", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("Exception occurred!", e);
            GemTestReporter.addTestStep("ERROR", "Exception occurred :" + e.getMessage(), STATUS.FAIL, DriverAction.takeSnapShot());
            throw e;
        }
    }

    public void clickToggleButton() {
        try {
            if (DriverAction.isExist(DashboardLocators.filterTicketsToggle)) {
                DriverAction.click(DashboardLocators.filterTicketsToggle, "Toggle Button");
            } else {
                GemTestReporter.addTestStep("Toggle Button", "Toggle Button does not Exist!", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("Exception occurred!", e);
            GemTestReporter.addTestStep("ERROR", "Exception occurred :" + e.getMessage(), STATUS.FAIL, DriverAction.takeSnapShot());
            throw e;
        }
    }

    public void verifyTicketDetailsPage() {
        try {
            String ticketDetailsPage = DriverAction.getCurrentURL();
            if (ticketDetailsPage.contains("ticket-details")) {
                GemTestReporter.addTestStep("Ticket Details Page Verification", "Ticket Details Page is Successfully Opened", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Ticket Details Page Verification", "Ticket Details Page is not Opened", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("Exception occurred!", e);
            GemTestReporter.addTestStep("ERROR", "Exception occurred :" + e.getMessage(), STATUS.FAIL, DriverAction.takeSnapShot());
            throw e;
        }
    }

    public void selectRowsPerPageValue(String drpDownValue) {
        try {
            if (DriverAction.isExist(DashboardLocators.rowsPerPageDropDown)) {
                DriverAction.scrollIntoView(DashboardLocators.nextBtn);
                Select rowsPerPage = new Select(DriverManager.getWebDriver().findElement(DashboardLocators.rowsPerPageDropDown));
                DriverAction.waitSec(2);
                rowsPerPage.selectByValue(drpDownValue);
                DriverAction.waitSec(2);
                DriverAction.scrollIntoView(DashboardLocators.rowsPerPageDropDown);
                DriverAction.waitSec(2);
                GemTestReporter.addTestStep("Rows Per Page DropDown", drpDownValue + " selected from Rows Per Page DropDown", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Rows Per Page DropDown", "Rows Per Page DropDown does not Exist", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("Exception Occurred!", e);
            GemTestReporter.addTestStep("ERROR", "Exception Occurred :" + e.getMessage(), STATUS.FAIL, DriverAction.takeSnapShot());
            throw e;
        }
    }

}

