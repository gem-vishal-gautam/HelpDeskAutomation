package com.qa.helpdesk.stepDefinitions;

import com.gemini.generic.reporting.GemTestReporter;
import com.gemini.generic.reporting.STATUS;
import com.gemini.generic.ui.utils.DriverManager;
import com.qa.helpdesk.locators.SignInLocators;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import com.gemini.generic.ui.utils.DriverAction;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.ro.Si;
import org.apache.commons.codec.binary.Base64;
import org.openqa.selenium.JavascriptExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.qa.helpdesk.genericUtils.Utils;

public class LogIn extends Utils {
    final Logger logger = LoggerFactory.getLogger(LogIn.class);

    @Given("^User launches the HelpDesk$")
    public static void launchHelpDesk() {
        try {
            Utils.waitForElement(SignInLocators.logIn, 45);
            DriverAction.click(SignInLocators.logIn, "LogIn with SSO");
        } catch (Exception e) {
            GemTestReporter.addTestStep("ERROR", "Exception occurred :" + e.getMessage(), STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }

    @When("^User enters Email (.+) and Password (.+)$")
    public void enterEmailAndPassword(String email, String pass) {
        try {
            DriverAction.waitSec(3);
            DriverAction.typeText(SignInLocators.typeEmail, email, "Email");
            DriverAction.click(SignInLocators.nextBtn, "Next Button");
            DriverAction.waitSec(3);
            byte[] decodingString = Base64.decodeBase64(pass);
            String passwordDecoded = new String(decodingString);
            DriverAction.typeText(SignInLocators.typePassword, passwordDecoded, "Password");
        } catch (Exception e) {
            logger.info("Exception Occurred!", e);
            GemTestReporter.addTestStep("ERROR", "Exception occurred :" + e.getMessage(), STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }

    @And("^User clicks on signIn Button$")
    public void clickSignInButton() {
        try {
            DriverAction.waitSec(2);
            JavascriptExecutor js = (JavascriptExecutor) DriverManager.getWebDriver();
            js.executeScript("arguments[0].click();", DriverManager.getWebDriver().findElement(SignInLocators.signIn));
//            DriverAction.click(SignInLocators.signIn, "Sign-In Button");
            DriverAction.waitSec(5);
            DriverAction.click(SignInLocators.NoBtn, "No Button");
            DriverAction.waitSec(5);
        } catch (Exception e) {
            logger.info("Exception occurred!", e);
            GemTestReporter.addTestStep("ERROR", "Exception occurred :", STATUS.FAIL, DriverAction.takeSnapShot());
            throw e;
        }
    }

    @Then("^User verifies the expectedUrl LogIn on HelpDesk$")
    public void verifyLogIn() {
        try {
            String expectedUrl = "https://helpdeskui-np.geminisolutions.com/#/dashboard/0";
            DriverAction.waitSec(3);
            String actualUrl = DriverAction.getCurrentURL();
            if (actualUrl.equals(expectedUrl)) {
                GemTestReporter.addTestStep("VERIFIED", "HelpDesk is Verified", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("NOT VERIFIED", "HelpDesk is Not Verified, Wrong Credentials", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("Exception occurred!", e);
            GemTestReporter.addTestStep("ERROR", "Exception Occurred :" + e.getMessage(), STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }

    @And("^User SignOut the HelpDesk$")
    public void signOutHelpDesk() {
        try {
            DriverAction.click(SignInLocators.signOutBtn, "SignOut Button");
        } catch (Exception e) {
            logger.info("Exception occurred!", e);
            GemTestReporter.addTestStep("ERROR", "Exception Occurred :" + e.getMessage(), STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }

    @Then("^User verifies the LogOut (.+)$")
    public void verifyLogOut(String expUrl) {
        try {
            String actualUrl = DriverAction.getCurrentURL();
            if (actualUrl.equals(expUrl)) {
                GemTestReporter.addTestStep("VERIFIED", "SignOut is Verified", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("NOT VERIFIED", "SignOut is Not Verified", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("Exception occurred!", e);
            GemTestReporter.addTestStep("ERROR", "Exception Occurred :" + e.getMessage(), STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }

    @And("^User switches to Employee View$")
    public void navigateToEmployeeView() {
        try {
            switchesToEmployeeView();
        } catch (Exception e) {
            logger.info("Exception occurred!", e);
            GemTestReporter.addTestStep("ERROR", "Exception Occurred :" + e.getMessage(), STATUS.FAIL, DriverAction.takeSnapShot());
            throw e;
        }
    }

    @When("^User enters Incorrect credentials and clicks on SignIn$")
    public void enterIncorrectCredentials() {
        String email = "vishal.gautam@geminisolutions.com";
        String wrongPassword = "dskdksdjid";
        DriverAction.typeText(SignInLocators.typeEmail, email, "Email");
        DriverAction.click(SignInLocators.nextBtn, "Next Button");
        Utils.waitForElement(SignInLocators.typePassword, 45);
        DriverAction.typeText(SignInLocators.typePassword, wrongPassword, "Password");
        DriverAction.click(SignInLocators.signIn, "Sign-In Button");
    }

    @Then("^Verify LogIn is Unsuccessful$")
    public void verifyLogInUnsuccessful() {
        String loginErrorMessage = DriverAction.getElementText(SignInLocators.errorLoginMessage);
        if (!loginErrorMessage.isEmpty()) {
            GemTestReporter.addTestStep("LogIn", "LogIn is Unsuccessful", STATUS.PASS, DriverAction.takeSnapShot());
        } else {
            GemTestReporter.addTestStep("LogIn", "LogIn is Successful unexpectedly", STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }
}