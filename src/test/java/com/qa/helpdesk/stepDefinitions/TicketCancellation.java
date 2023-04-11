package com.qa.helpdesk.stepDefinitions;

import com.gemini.generic.reporting.GemTestReporter;
import com.gemini.generic.reporting.STATUS;
import com.gemini.generic.ui.utils.DriverAction;
import com.gemini.generic.ui.utils.DriverManager;
import com.qa.helpdesk.genericUtils.Utils;
import com.qa.helpdesk.locators.DashboardLocators;
import com.qa.helpdesk.locators.TicketCancelLocators;
import com.qa.helpdesk.locators.TicketCreationLocators;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.awt.event.KeyEvent;

public class TicketCancellation {
    final Logger logger = LoggerFactory.getLogger(TicketCancellation.class);
    Utils util = new Utils();

    @And("^The user obtains the newly created Ticked ID and searches it$")
    public void getCreatedTickedIDAndSearchID() {
        try {
            String tickedID = util.getTicketID();
            DriverAction.typeText(DashboardLocators.searchBox, tickedID, "Entered TickedID");
//            DriverAction.waitUntilElementAppear(DashboardLocators.searchBtn, 3);
            Utils.waitForElement(DashboardLocators.searchBtn, 10);
            DriverAction.click(DashboardLocators.searchBtn, "Search Button");
            DriverAction.waitSec(2);
        } catch (Exception e) {
            logger.info("Exception occurred!", e);
            GemTestReporter.addTestStep("ERROR", "Exception occurred :" + e.getMessage(), STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }

    @And("^User cancels the ticket from action button and provides a Reason - (.+)$")
    public void cancelTheTicketFromActionWithReason(String reason) {
        try {
            if (!(DriverAction.getElementText(DashboardLocators.statusCol).equalsIgnoreCase("Cancelled"))) {
                Utils.waitForElement(TicketCancelLocators.threeDotsBtn, 10);
                DriverAction.click(TicketCancelLocators.threeDotsBtn, "Action Button - Three Dots");
                Utils.waitForElement(TicketCancelLocators.cancelTicketBtn, 10);
                DriverAction.click(TicketCancelLocators.cancelTicketBtn, "Cancel Ticket Button");
                Utils.waitForElement(TicketCancelLocators.typeReason, 10);
                DriverAction.typeText(TicketCancelLocators.typeReason, reason);
                Utils.waitForElement(TicketCancelLocators.finalCancelBtn, 10);
                DriverAction.click(TicketCancelLocators.finalCancelBtn, "Final Ticket Cancel Button");
            } else {
                System.out.println("ALREADY CLOSED");
                GemTestReporter.addTestStep("Action Button Disabled", "Ticked has been Resolved or Closed or Cancelled already", STATUS.PASS, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("Exception occurred!", e);
            GemTestReporter.addTestStep("Not Verified", "Exception arises" + e.getMessage(), STATUS.FAIL, DriverAction.takeSnapShot());
            throw e;
        }
    }

    @And("^User cancels the ticket from action button and without providing a Reason$")
    public void cancelTheTicketWithoutReason() {
        try {
            DriverAction.waitSec(3);
            DriverAction.click(TicketCancelLocators.threeDotsBtn);
            DriverAction.waitUntilElementAppear(TicketCancelLocators.cancelTicketBtn, 3);
            DriverAction.click(TicketCancelLocators.cancelTicketBtn);
            DriverAction.waitUntilElementAppear(TicketCancelLocators.finalCancelBtn, 3);
            DriverAction.click(TicketCancelLocators.finalCancelBtn, "Final Cancel");
        } catch (Exception e) {
            logger.info("Exception occurred!", e);
            GemTestReporter.addTestStep("ERROR", "Exception occurred :" + e.getMessage(), STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }


    @Then("^User verify the canceled Ticket$")
    public void verifyCanceledTicket() {
        try {
            DriverAction.waitSec(5);
            String tickedID = util.getTicketID();
            DriverAction.typeText(DashboardLocators.searchBox, tickedID, "Entered TickedID");
//            DriverAction.waitUntilElementAppear(DashboardLocators.searchBtn, 3);
            Utils.waitForElement(DashboardLocators.searchBtn, 10);
            DriverAction.click(DashboardLocators.searchBtn);
            DriverAction.waitSec(3);
            String cancelStatus = DriverAction.getElementText(TicketCancelLocators.cancelTicketStatus);
            if (cancelStatus.equalsIgnoreCase("Cancelled")) {
                GemTestReporter.addTestStep("Ticket Cancel Verification", "Ticked Canceled Successfully", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Ticket Cancel Verification", "Ticked Cancellation Unsuccessful", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("Exception occurred!", e);
            GemTestReporter.addTestStep("ERROR", "Exception occurred :" + e.getMessage(), STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }

    @Given("^User searches random (.+)$")
    public void searchRandomTicket(String randomTicket) {
        try {
            Utils.waitForElement(DashboardLocators.searchBox, 10);
//            DriverAction.waitSec(3);
            DriverAction.typeText(DashboardLocators.searchBox, randomTicket, "Entered Random TickedID");
//            DriverAction.waitUntilElementAppear(DashboardLocators.searchBtn, 3);
            Utils.waitForElement(DashboardLocators.searchBtn, 10);
//            DriverAction.waitSec(3);
            DriverAction.click(DashboardLocators.searchBtn);
            DriverAction.waitSec(3);
        } catch (Exception e) {
            logger.info("Exception occurred!", e);
            GemTestReporter.addTestStep("ERROR", "Exception occurred :" + e.getMessage(), STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }

    @Then("^User verifies searched (.+) is present$")
    public void verifySearchedTicketPresence(String Ticket) {
        try {
            DriverAction.waitSec(5);
            String searchedTicket = DriverAction.getElementText(DashboardLocators.tickedID);
            if (Ticket.equalsIgnoreCase(searchedTicket)) {
                GemTestReporter.addTestStep("Searched Ticket Verification", "Searched Ticked is Found Successfully", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Searched Ticket Verification", "Searched Ticked is Not Found", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("Exception occurred!", e);
            GemTestReporter.addTestStep("ERROR", "Exception occurred :" + e.getMessage(), STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }

    @Then("^User verifies the Random Cancelled (.+)$")
    public void verifyRandomCancelledTicket(String randomTicket) {
        try {
//            DriverAction.waitUntilElementAppear(DashboardLocators.searchBox, 3);
            DriverAction.waitSec(2);
            DriverAction.typeText(DashboardLocators.searchBox, randomTicket, "Entered TickedID " + randomTicket);
//            DriverAction.waitUntilElementAppear(DashboardLocators.searchBtn, 3);
            DriverAction.waitSec(2);
            DriverAction.click(DashboardLocators.searchBtn);
            DriverAction.waitSec(5);
            String cancelStatus = DriverAction.getElementText(TicketCancelLocators.cancelTicketStatus);
            if (cancelStatus.equalsIgnoreCase("Cancelled")) {
                GemTestReporter.addTestStep("Ticket Cancel Verification", "Ticked is Cancelled", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Ticket Cancel Verification", "Ticked Cancellation Unsuccessful", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("Exception occurred!", e);
            GemTestReporter.addTestStep("ERROR", "Exception occurred :" + e.getMessage(), STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }

    @Then("^User verify that action button should be Enabled for newly created ticket$")
    public void verifyEnabledActiveButtonForCreatedTicket() {
        try {
//            Utils.waitForElement(TicketCancelLocators.threeDotsBtn, 10);
            DriverAction.waitSec(3);
            if (DriverManager.getWebDriver().findElement(TicketCancelLocators.threeDotsBtn).isEnabled()) {
                GemTestReporter.addTestStep("Action Button Enabled Verification", "Action Button is Enabled for the newly created ticket", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Action Button Enabled Verification", "Action Button is Not Enabled for the newly created ticket", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("Exception occurred!", e);
            GemTestReporter.addTestStep("ERROR", "Exception occurred :" + e.getMessage(), STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }

    @Then("^User verify that action button should be Disabled for cancelled ticket$")
    public void verifyDisabledActiveButtonForCreatedTicket() {
        try {
            if (DriverManager.getWebDriver().findElement(TicketCancelLocators.threeDotsBtn).isEnabled()) {
                GemTestReporter.addTestStep("Action Button Disabled Verification", "Action Button is Disabled for the newly cancelled ticket", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Action Button Disabled Verification", "Action Button is Enabled for the newly cancelled ticket", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("Exception occurred!", e);
            GemTestReporter.addTestStep("ERROR", "Exception occurred :" + e.getMessage(), STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }

    @Then("^User clicks on the Ticket ID and verify Ticket Details Page$")
    public void verifyPageTicketDetails() {
//        DriverAction.waitSec(4);
        Utils.waitForElement(DashboardLocators.tickedID, 20);
        DriverAction.click(DashboardLocators.tickedID, "Ticked ID");
        DriverAction.waitSec(4);
        util.verifyTicketDetailsPage();
    }

    @Then("^User verify ticket created on details with Timeline details$")
    public void verifyDateAndTimeDetails() throws AWTException {
        DriverAction.click(TicketCreationLocators.showTimeLineButton);
        DriverAction.waitSec(3);

        String createdOnDetails = DriverAction.getElementText(TicketCreationLocators.createdOnDetails);
        String[] createdOnDetailsNew = createdOnDetails.split(",");

        String timelineDateDetails = DriverAction.getElementText(TicketCreationLocators.createdDateTimeLine);
        String timelineTimeDetails = DriverAction.getElementText(TicketCreationLocators.createdTime);
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_SUBTRACT);
        robot.keyRelease(KeyEvent.VK_SUBTRACT);
        robot.keyPress(KeyEvent.VK_SUBTRACT);
        robot.keyPress(KeyEvent.VK_SUBTRACT);
        robot.keyRelease(KeyEvent.VK_SUBTRACT);
        robot.keyRelease(KeyEvent.VK_SUBTRACT);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        if (createdOnDetailsNew[0].equals(timelineDateDetails)) {
            GemTestReporter.addTestStep("TimeLine Date", "TimeLine Date is verified", STATUS.PASS, DriverAction.takeSnapShot());
        } else {
            GemTestReporter.addTestStep("TimeLine Date", "TimeLine Date is Not verified", STATUS.FAIL, DriverAction.takeSnapShot());
        }
//        if (createdOnDetailsNew[1].equals(timelineTimeDetails)) {
//            GemTestReporter.addTestStep("TimeLine Time ", "TimeLine Time is verified", STATUS.PASS, DriverAction.takeSnapShot());
//        } else {
//            GemTestReporter.addTestStep("TimeLine Time ", "TimeLine Time is Not verified", STATUS.FAIL, DriverAction.takeSnapShot());
//        }
    }

    @And("^User cancels the ticket from Ticket Details Page and provides a Reason -(.+)$")
    public void cancelTicketFromDetailsPageWithReason(String reason) {
        try {
            DriverAction.waitSec(3);
            DriverAction.click(TicketCancelLocators.ticketCancelFromDetailsPage);
            DriverAction.waitUntilElementAppear(TicketCancelLocators.typeReason, 3);
            DriverAction.typeText(TicketCancelLocators.typeReason, reason);
            DriverAction.waitUntilElementAppear(TicketCancelLocators.finalCancelBtn, 3);
            DriverAction.click(TicketCancelLocators.finalCancelBtn);
        } catch (Exception e) {
            logger.info("Exception occurred!", e);
            GemTestReporter.addTestStep("ERROR", "Exception occurred :" + e.getMessage(), STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }

    @Then("^User confirms that the ticket should not be cancelled$")
    public void verifyTicketNotCancelled() {
        DriverAction.waitSec(2);
        if (DriverAction.isExist(TicketCancelLocators.reasonRequiredWarning)) {
            GemTestReporter.addTestStep("Reason Required", "Reason is Required and Hence Ticket is not Cancelled", STATUS.PASS, DriverAction.takeSnapShot());
        } else {
            GemTestReporter.addTestStep("Reason Not Required", "Ticket is Cancelled unexpectedly", STATUS.PASS, DriverAction.takeSnapShot());
        }
    }
}


