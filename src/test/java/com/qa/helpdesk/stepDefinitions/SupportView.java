package com.qa.helpdesk.stepDefinitions;

import com.gemini.generic.reporting.GemTestReporter;
import com.gemini.generic.reporting.STATUS;
import com.gemini.generic.ui.utils.DriverAction;
import com.gemini.generic.ui.utils.DriverManager;
import com.qa.helpdesk.genericUtils.Utils;
import com.qa.helpdesk.locators.DashboardLocators;
import com.qa.helpdesk.locators.SupportViewLocators;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import io.cucumber.java.en_scouse.An;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class SupportView extends Utils {
    final Logger logger = LoggerFactory.getLogger(SupportView.class);
    String assignedUrl = "https://helpdeskui-np.geminisolutions.com/#/dashboard/0";
//
//    @When("^(.+) is selected by the user$")
//    public void selectCallerAndChannel(String Caller) throws AWTException {
//        try {
//            Robot robot = new Robot();
//            Utils.waitForElement(SupportViewLocators.callerSearchNameSupportView, 40);
//            DriverAction.click(SupportViewLocators.callerSearchNameSupportView);
//            DriverAction.typeText(SupportViewLocators.callerSearchNameSupportView, Caller);
//            DriverAction.waitSec(3);
//            robot.keyPress(KeyEvent.VK_ENTER);
////            DriverAction.click(SupportViewLocators.channelType(Channel));
//        } catch (Exception e) {
//            logger.info("Exception Occurred!", e);
//            GemTestReporter.addTestStep("ERROR", "Exception Occurred :" + e.getMessage(), STATUS.FAIL, DriverAction.takeSnapShot());
//            throw e;
//        }
//    }
//
//    @And("^User checks the caller details and past tickets$")
//    public void checkCallerDetailsAndPastTickets() {
//        DriverAction.click(SupportViewLocators.infoSupportView);
//    }

    @Given("^User looks over the current (.+)$")
    public void currentHeaders(String nameOfHeaders) {
        try {
            DriverAction.click(SupportViewLocators.headerName(nameOfHeaders));
        } catch (Exception e) {
            logger.info("Exception Occurred!", e);
            GemTestReporter.addTestStep("ERROR", "Exception Occurred :" + e.getMessage(), STATUS.FAIL, DriverAction.takeSnapShot());
            throw e;
        }
    }

    @Then("^User verify the headers$")
    public void verifyHeaders() {

//        String assignedUrl = "https://helpdeskui-np.geminisolutions.com/#/dashboard/0";
        String unassignedUrl = "https://helpdeskui-np.geminisolutions.com/#/dashboard/1";
        String myDepartmentUrl = "https://helpdeskui-np.geminisolutions.com/#/dashboard/2";
        String othersUrl = "https://helpdeskui-np.geminisolutions.com/#/dashboard/3";

        String currentUrl = DriverAction.getCurrentURL();

        if (currentUrl.contains(assignedUrl)) {
            GemTestReporter.addTestStep("Verify Headers", "Assigned Header is Verified", STATUS.PASS, DriverAction.takeSnapShot());
        } else if (currentUrl.contains(unassignedUrl)) {
            GemTestReporter.addTestStep("Verify Headers", "Unassigned Header is Verified", STATUS.PASS, DriverAction.takeSnapShot());
        } else if (currentUrl.contains(myDepartmentUrl)) {
            GemTestReporter.addTestStep("Verify Headers", "My Department Header is Verified", STATUS.PASS, DriverAction.takeSnapShot());
        } else if (currentUrl.contains(othersUrl)) {
            GemTestReporter.addTestStep("Verify Headers", "Others is Verified", STATUS.PASS, DriverAction.takeSnapShot());
        }
    }

    @Given("^User checks the LoggedIn UserName (.+) and LoggedIn User Designation (.+)$")
    public void checkNameAndDesignation(String user, String designation) {
        String userName = DriverAction.getElementText(SupportViewLocators.loggedUserName);
        String userDesignation = DriverAction.getElementText(SupportViewLocators.loggedUserDesignation);

        if (user.contains(userName)) {
            GemTestReporter.addTestStep("Verify Logged UserName", "Logged UserName is Verified", STATUS.PASS, DriverAction.takeSnapShot());
        } else {
            GemTestReporter.addTestStep("Verify Logged UserName", "Logged UserName is Not Verified", STATUS.FAIL, DriverAction.takeSnapShot());
        }
        if (designation.contains(userDesignation)) {
            GemTestReporter.addTestStep("Verify Logged User Designation", "Logged User Designation is Verified", STATUS.PASS, DriverAction.takeSnapShot());
        } else {
            GemTestReporter.addTestStep("Verify Logged User Designation", "Logged User Designation is Not Verified", STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }

    @Given("^User is in the Assigned Tab$")
    public void assignedTab() {
        DriverAction.waitSec(2);
        String currentUrl = DriverAction.getCurrentURL();
        DriverAction.waitSec(2);
        if (currentUrl.contains(assignedUrl)) {
            GemTestReporter.addTestStep("Verify Assigned Tab", "User is already in Assigned Tab", STATUS.PASS, DriverAction.takeSnapShot());
        } else {
            Utils.waitForElement(SupportViewLocators.assignedTab, 10);
            DriverAction.click(SupportViewLocators.assignedTab, "Assigned Tab");
        }
    }

    @And("^User fetches the UserName from the Assigned To Tab and verifies it$")
    public void fetchUserNameFromAssignedToTab() {
        List<String> assignedToTabUserNameList = DriverAction.getElementsText(SupportViewLocators.assignedToTab);
        boolean flag = false;
        DriverAction.scrollIntoView(DashboardLocators.nextBtn);
        selectRowsPerPageValue("25");
        if ((DriverManager.getWebDriver().findElement(DashboardLocators.nextBtn).isEnabled())) {
            while ((DriverManager.getWebDriver().findElement(DashboardLocators.nextBtn).isEnabled())) {
//                List<String> assignedToTabUserNameList = DriverAction.getElementsText(SupportViewLocators.assignedToTab);
                for (String s : assignedToTabUserNameList) {
                    if ((s.equalsIgnoreCase("Vishal Gautam"))) {
                        flag = true;
                        break;
                    }
                }
                if (flag) {
                    GemTestReporter.addTestStep("Assigned To UserName", "Assigned To UserName is Verified", STATUS.PASS, DriverAction.takeSnapShot());
                } else {
                    GemTestReporter.addTestStep("Assigned To UserName", "Assigned To UserName is not Working", STATUS.FAIL, DriverAction.takeSnapShot());
                }
                DriverAction.click(DashboardLocators.nextBtn, "Next Side Button");
                DriverAction.waitSec(3);
            }
        } else {
            GemTestReporter.addTestStep("Next Button", "Next Button is Not Enabled because of few Data", STATUS.PASS, DriverAction.takeSnapShot());
        }
        if (!(DriverManager.getWebDriver().findElement(DashboardLocators.nextBtn).isEnabled())) {
            DriverAction.waitSec(2);
            for (String s : assignedToTabUserNameList) {
                if ((s.equalsIgnoreCase("Vishal Gautam"))) {
                    flag = true;
                    break;
                }
            }
            if (flag) {
                GemTestReporter.addTestStep("Assigned To UserName", "Assigned To UserName is Verified", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Assigned To UserName", "Assigned To UserName is not Working", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        }
    }
}
