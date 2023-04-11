package com.qa.helpdesk.stepDefinitions;

import com.gemini.generic.reporting.GemTestReporter;
import com.gemini.generic.reporting.STATUS;
import com.gemini.generic.ui.utils.DriverAction;
import com.gemini.generic.ui.utils.DriverManager;
import com.qa.helpdesk.genericUtils.Utils;
import com.qa.helpdesk.locators.DashboardLocators;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.sl.In;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.awt.event.KeyEvent;

import java.util.List;
import java.util.Set;


public class Dashboard extends Utils {

    final Logger logger = LoggerFactory.getLogger(Dashboard.class);

    @Then("^User verifies the (.+) sorting functionality$")
    public void sortColumn(String colName) {
        try {
            columnSorting(colName);
        } catch (Exception e) {
            logger.info("Exception Occurred!", e);
            GemTestReporter.addTestStep("ERROR", "Exception Occurred :" + e.getMessage(), STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }

    //Search
    @Given("^User (.+) data in global search box$")
    public void enterDataInSearchBar(String data) {
        try {
//            Utils.waitForElement(DashboardLocators.searchBox, 40);
            DriverAction.waitSec(3);
            DriverAction.typeText(DashboardLocators.searchBox, data, "Search Box");
            DriverAction.click(DashboardLocators.searchBtn);
            DriverAction.waitSec(7);
        } catch (Exception e) {
            logger.info("Exception Occurred!", e);
            GemTestReporter.addTestStep("ERROR", "Exception Occurred :" + e.getMessage(), STATUS.FAIL, DriverAction.takeSnapShot());
            throw e;
        }
    }

    @Then("^User verifies the (.+) data$")
    public void verifyEnteredData(String enteredData) {
        boolean Flag = false;
        List<String> resultList = DriverAction.getElementsText(DashboardLocators.resultList);
        for (String s : resultList) {
            if (s.contains(enteredData)) {
                Flag = true;
                break;
            }
        }
        if (Flag) {
            GemTestReporter.addTestStep("Data Matched", "Entered Data is Matched in the Result", STATUS.PASS, DriverAction.takeSnapShot());
        } else {
            GemTestReporter.addTestStep("Data Not Matched", "Entered Data is NOT Matched in the Result", STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }

    @Then("^User verify the Negative (.+) data$")
    public void verifyNegativeEnteredData(String negativeEnteredData) {
        boolean Flag = false;
        List<String> resultList = DriverAction.getElementsText(DashboardLocators.resultList);
        for (String s : resultList) {
            if (s.contains(negativeEnteredData)) {
                Flag = true;
                break;
            }
        }
        if (Flag) {
            GemTestReporter.addTestStep("Data Matched", "Entered Data is Matched in the Result", STATUS.FAIL, DriverAction.takeSnapShot());
        } else {
            GemTestReporter.addTestStep("Data Not Matched", "Entered Data is NOT Matched in the Result", STATUS.PASS, DriverAction.takeSnapShot());
        }
    }

    @Then("^User verify the Negative entered data$")
    public void verifyNegativeData() {
        try {
            Utils.waitForElement(DashboardLocators.noData, 20);
//            Utils.presenceOfElement(DashboardLocators.noData, 40);
            if (DriverAction.isExist(DashboardLocators.noData)) {
                GemTestReporter.addTestStep("Validate if data is found!", "Entered Data is not Found", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Validate if data is found!", "Some Data is Found", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("Exception occurred!", e);
            GemTestReporter.addTestStep("Not Verified", "Exception arises" + e.getMessage(), STATUS.FAIL, DriverAction.takeSnapShot());
            throw e;
        }
    }

    @And("^User click on cross icon$")
    public void clickOnCrossIcon() {
        DriverAction.waitSec(2);
        clickSearchBoxCross();
    }

    @Then("^User verify if entered text is removed$")
    public void verifyTextRemoved() {
        try {
            String searchBoxValue = DriverAction.getElementText(DashboardLocators.searchBox);
            if (searchBoxValue.isEmpty()) {
                GemTestReporter.addTestStep("Cross Icon Function", "No data is present hence cross icon validated", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Cross Icon Function", "Data is still there", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("Exception Occurred!", e);
            GemTestReporter.addTestStep("ERROR", "Exception Occurred :" + e.getMessage(), STATUS.FAIL, DriverAction.takeSnapShot());
            throw e;
        }
    }


    @Given("^User opens the Calendar$")
    public void Calendar() {
        try {
            DriverAction.waitUntilElementAppear(DashboardLocators.calendarImg, 5);
            DriverAction.click(DashboardLocators.calendarImg, "Calendar icon");
        } catch (Exception e) {
            logger.info("Exception Occurred!", e);
            GemTestReporter.addTestStep("ERROR", "Exception Occurred :" + e.getMessage(), STATUS.FAIL, DriverAction.takeSnapShot());
            throw e;
        }
    }

    @Then("^User verifies the calendar$")
    public void verifyCalendar() {
        try {
            if (DriverAction.isExist(DashboardLocators.calendarBox)) {
                GemTestReporter.addTestStep("Calendar", "Calendar is Verified", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Calendar", "Calendar is Not Verified", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("Exception Occurred!", e);
            GemTestReporter.addTestStep("ERROR", "Exception Occurred :" + e.getMessage(), STATUS.FAIL, DriverAction.takeSnapShot());
            throw e;
        }
    }

    @Then("^User verifies the current date$")
    public void verifyCurrentDate() {
        try {
            String date = getDate().split("/")[0];
            String actualDate = DriverAction.getElementText(DashboardLocators.currentDate);
            int dateInt = Integer.parseInt(date);
            if (dateInt > 0 && dateInt < 10) {
                actualDate = "0" + actualDate;
            }
            if (date.equalsIgnoreCase(actualDate)) {
                GemTestReporter.addTestStep("Calendar", "Current Date is Verified :" + actualDate, STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Calendar", "Current Date is Not Verified", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("Exception Occurred!", e);
            GemTestReporter.addTestStep("ERROR", "Exception Occurred :" + e.getMessage(), STATUS.FAIL, DriverAction.takeSnapShot());
            throw e;
        }
    }

    @Then("^User verifies Month count$")
    public void verifyMonthCount() {
        try {
            if (monthCount() == 12) {
                GemTestReporter.addTestStep("Calendar Months", "Month count is valid", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Calendar Months", "Month count is not valid", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("Exception Occurred!", e);
            GemTestReporter.addTestStep("ERROR", "Exception Occurred :" + e.getMessage(), STATUS.FAIL, DriverAction.takeSnapShot());
            throw e;
        }
    }

    @Then("^User verifies WeekDay count$")
    public void verifyWeekDayCount() {
        try {
            if (weekDayCount() == 7) {
                GemTestReporter.addTestStep("Calendar WeekDays", "WeekDays count is valid", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Calendar WeekDays", "WeekDays count is not valid", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("Exception Occurred!", e);
            GemTestReporter.addTestStep("ERROR", "Exception Occurred :" + e.getMessage(), STATUS.FAIL, DriverAction.takeSnapShot());
            throw e;
        }
    }

    @Then("^User verifies clears data$")
    public void verifyClearsData() {
        try {
            clearData();
            if (DriverAction.isExist(DashboardLocators.currentDate)) {
                GemTestReporter.addTestStep("Calendar", "Clear Data Button is Verified", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Calendar", "Clear Data Button is Not Verified", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("Exception Occurred!", e);
            GemTestReporter.addTestStep("ERROR", "Exception Occurred :" + e.getMessage(), STATUS.FAIL, DriverAction.takeSnapShot());
            throw e;
        }
    }

    @Given("^User clicks on the Filter Button$")
    public void clickFilterButton() {
        try {
            clickOnFilterBtn();
        } catch (Exception e) {
            logger.info("Exception Occurred!", e);
            GemTestReporter.addTestStep("ERROR", "Exception Occurred :" + e.getMessage(), STATUS.FAIL, DriverAction.takeSnapShot());
            throw e;
        }
    }

    @Then("^User verifies the Filter Button$")
    public void verifyFilterButton() {
        try {
            DriverAction.waitUntilElementAppear(DashboardLocators.filtersHeading, 3);
            if (DriverAction.isExist(DashboardLocators.filtersHeading)) {
                GemTestReporter.addTestStep("Filter", "Filter Button is Verified", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Filter", "Filter Button is Not Verified", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("Exception Occurred!", e);
            GemTestReporter.addTestStep("ERROR", "Exception Occurred :" + e.getMessage(), STATUS.FAIL, DriverAction.takeSnapShot());
            throw e;
        }
    }

    @And("^User switches on the toggle for 'Hide cancelled, closed and resolved tickets'$")
    public void switchesOnTheToggle() {
        try {
            clickToggleButton();
            DriverAction.waitSec(2);
        } catch (Exception e) {
            logger.info("Exception occurred!", e);
            GemTestReporter.addTestStep("ERROR", "Exception occurred :" + e.getMessage(), STATUS.FAIL, DriverAction.takeSnapShot());
            throw e;
        }
    }

    @Then("^User verify that ticket displayed should not be 'Cancelled, closed or resolved'$")
    public void verifyTicketsAfterToggleIsOn() {
        boolean flag = false;
        DriverAction.scrollIntoView(DashboardLocators.nextBtn);
        selectRowsPerPageValue("25");
        while ((DriverManager.getWebDriver().findElement(DashboardLocators.nextBtn).isEnabled())) {
            List<String> statusListAfterToggleClick = DriverAction.getElementsText(DashboardLocators.statusCol);
            for (String s : statusListAfterToggleClick) {
                if (!(s.equalsIgnoreCase("Cancelled") || s.equalsIgnoreCase("Closed") || s.equalsIgnoreCase("Resolved"))) {
                    flag = true;
                    break;
                }
            }
            if (flag) {
                GemTestReporter.addTestStep("Filter Toggle Switch", "Filter Toggle Switch is Working", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Filter Toggle Switch", "Filter Toggle Switch is Not Working", STATUS.FAIL, DriverAction.takeSnapShot());
            }
            DriverAction.click(DashboardLocators.nextBtn, "Next Side Button");
            DriverAction.waitSec(3);
            if (!(DriverManager.getWebDriver().findElement(DashboardLocators.nextBtn).isEnabled())) {
                for (String s : statusListAfterToggleClick) {
                    if (!(s.equalsIgnoreCase("Cancelled") || s.equalsIgnoreCase("Closed") || s.equalsIgnoreCase("Resolved"))) {
                        flag = true;
                        break;
                    }
                }
                if (flag) {
                    GemTestReporter.addTestStep("Filter Toggle Switch", "Filter Toggle Switch is Working", STATUS.PASS, DriverAction.takeSnapShot());
                } else {
                    GemTestReporter.addTestStep("Filter Toggle Switch", "Filter Toggle Switch is Not Working", STATUS.FAIL, DriverAction.takeSnapShot());
                }
            }
        }
    }

    @And("^User selects the (.+) and (.+)$")
    public void selectDepartmentAndStatus(String departmentTypes, String statusTypes) throws AWTException {
        try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_SUBTRACT);
            robot.keyPress(KeyEvent.VK_SUBTRACT);
            robot.keyPress(KeyEvent.VK_SUBTRACT);
            robot.keyPress(KeyEvent.VK_SUBTRACT);
            robot.keyPress(KeyEvent.VK_SUBTRACT);
            robot.keyPress(KeyEvent.VK_SUBTRACT);
            robot.keyRelease(KeyEvent.VK_CONTROL);

            String[] departmentValues = departmentTypes.split(",");
            int departmentValuesSize = departmentValues.length;
            int departmentCount = 0;
            for (String s : departmentValues) {
                DriverAction.click(DashboardLocators.departmentName(s));
                DriverAction.waitSec(1);
                departmentCount++;
            }
            if (departmentCount == departmentValuesSize) {
                GemTestReporter.addTestStep("Filter Selection", "Successfully selected the : " + departmentValuesSize + ", Department Filters", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Filter Selection", "Unable to select the Department Filters", STATUS.FAIL, DriverAction.takeSnapShot());
            }
            String[] statusValues = statusTypes.split(",");
            int statusValuesSize = statusValues.length;
            int statusCount = 0;
            for (String s2 : statusValues) {
                DriverAction.click(DashboardLocators.statusName(s2));
                DriverAction.waitSec(1);
                statusCount++;
            }
            if (statusCount == statusValuesSize) {
                GemTestReporter.addTestStep("Filter Selection", "Successfully selected the : " + statusValuesSize + ", Status Filters", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Filter Selection", "Unable to select the Status Filters", STATUS.FAIL, DriverAction.takeSnapShot());
            }
//            DriverAction.waitSec(4);
            Utils.waitForElement(DashboardLocators.crossIcon, 10);
            DriverAction.click(DashboardLocators.crossIcon, "cross icon");
        } catch (Exception e) {
            logger.info("Exception Occurred!", e);
            GemTestReporter.addTestStep("ERROR", "Exception Occurred :" + e.getMessage(), STATUS.FAIL, DriverAction.takeSnapShot());
            throw e;
        }
    }

    @Then("^User Verify the (.+) and (.+)$")
    public void verifyDepartmentAndStatus(String departmentTypes, String statusTypes) {
        try {
            String[] departmentValues = departmentTypes.split(",");
            String[] statusValues = statusTypes.split(",");
            selectRowsPerPageValue("25");
            do {
                List<String> dItems = null;
                List<String> sItems = null;
                List<String> departmentItems = DriverAction.getElementsText(DashboardLocators.departmentCol);
                List<String> statusItems = DriverAction.getElementsText(DashboardLocators.statusCol);
                for (String s : departmentItems) {
                    if (!(dItems.contains(s))) {
                        dItems.add(s);
                    }
                }
                if (dItems.equals(departmentValues)) {
                    GemTestReporter.addTestStep("Filter Verification in Result", "Successful", STATUS.PASS, DriverAction.takeSnapShot());
                }
            } while ((DriverManager.getWebDriver().findElement(DashboardLocators.nextBtn).isEnabled()));


//            for (String departmentItem : departmentItems) {
//                if (departmentItem.contains(departmentTypes)) {
//                    GemTestReporter.addTestStep("Filter", "Department Result is Verified", STATUS.PASS, DriverAction.takeSnapShot());
//                } else {
//                    GemTestReporter.addTestStep("Filter", "Department Result is Not Verified", STATUS.FAIL, DriverAction.takeSnapShot());
//                }
//            }
//            for (String statusItem : statusItems) {
//                if (statusItem.contains(statusTypes)) {
//                    GemTestReporter.addTestStep("Filter", "Status Result is Verified", STATUS.PASS, DriverAction.takeSnapShot());
//                } else {
//                    GemTestReporter.addTestStep("Filter", "Status Result is Not Verified", STATUS.FAIL, DriverAction.takeSnapShot());
//                }
//            }
        } catch (Exception e) {
            logger.info("Exception Occurred!", e);
            GemTestReporter.addTestStep("ERROR", "Exception Occurred :" + e.getMessage(), STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }


    @Then("^User clears all filter and verifies it$")
    public void clearAllFilters() {
        clickOnFilterBtn();
        if (DriverAction.isExist(DashboardLocators.clearAllFiltersButton)) {
            DriverAction.click(DashboardLocators.clearAllFiltersButton, "Clear All");
            if (!(DriverAction.isExist(DashboardLocators.selectedValue))) {
                GemTestReporter.addTestStep("Clear Filters", "All filters are cleared", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Clear Filters", "All filters are not cleared", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } else {
            GemTestReporter.addTestStep("Clear All Filters Button", "Clear All Filters Button does not Exist", STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }

    @And("^User closes Filter Tab$")
    public void closeFilter() {
        try {
            closeFilterTab();
        } catch (Exception e) {
            logger.info("Exception Occurred!", e);
            GemTestReporter.addTestStep("ERROR", "Exception Occurred :" + e.getMessage(), STATUS.FAIL, DriverAction.takeSnapShot());
            throw e;
        }
    }

    @Then("^User verifies the closed filter tab$")
    public void verifyClosedFilterTab() {
        try {
            if (!DriverAction.isExist(DashboardLocators.filtersHeading)) {
                GemTestReporter.addTestStep("Filter", "Filter Close Button is Verified", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Filter", "Filter Close Button is Not Verified", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("Exception Occurred!", e);
            GemTestReporter.addTestStep("ERROR", "Exception Occurred :" + e.getMessage(), STATUS.FAIL, DriverAction.takeSnapShot());
            throw e;
        }
    }

    @Then("^User selects and verifies the Random Date - (.+), Month - (.+), Year - (.+)$")
    public void verifyRandomDate(Integer randomDate, Integer randomMonth, Integer randomYear) {
        try {
            Select monthDropDown = new Select(DriverAction.getElement(DashboardLocators.monthDropDown));
            if (randomMonth < 12) {
                monthDropDown.selectByIndex(randomMonth);
                GemTestReporter.addTestStep("Calendar", "Chosen Month Value is Valid :" + randomMonth, STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Calendar", "Chosen Month Value is Invalid", STATUS.FAIL, DriverAction.takeSnapShot());
            }
            DriverAction.waitSec(2);
            int currentYear = Integer.parseInt(getDate().split("/")[2]);
            if (randomYear <= currentYear) {
                Select yearDropDown = new Select(DriverAction.getElement(DashboardLocators.yearDropDown));
                yearDropDown.selectByValue(String.valueOf(randomYear));
                GemTestReporter.addTestStep("Calendar", "Chosen Year Value is Valid :" + randomYear, STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Calendar", "Chosen Year Value is Invalid", STATUS.FAIL, DriverAction.takeSnapShot());
            }
            if (randomDate > 0 && randomDate < 32) {
//                List<String> randomDateList = DriverAction.getElementsText(DashboardLocators.randomDate);
                String rDate = String.valueOf(randomDate);
                //String getDate = randomDateList.get(randomDate);
                DriverAction.click(By.xpath(DashboardLocators.dateSelector.replace("@date", rDate)));
                DriverAction.waitSec(3);
            } else {
                GemTestReporter.addTestStep("Calendar", "Chosen Date Value is Invalid", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (NumberFormatException e) {
            logger.info("Exception Occurred!", e);
            GemTestReporter.addTestStep("ERROR", "Exception Occurred :" + e.getMessage(), STATUS.FAIL, DriverAction.takeSnapShot());
            throw e;
        }
    }

    @When("^User chooses (.+) Month and (.+) Year$")
    public void pickMonthAndYear(int Month, int Year) {
        try {
            Select monthDropDown = new Select(DriverAction.getElement(DashboardLocators.monthDropDown));
            if (Month < 12) {
                monthDropDown.selectByIndex(Month);
                GemTestReporter.addTestStep("Calendar", "Chosen Month Value is Valid :" + Month, STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Calendar", "Chosen Month Value is Invalid", STATUS.FAIL, DriverAction.takeSnapShot());
            }
            DriverAction.waitSec(2);
            int currentYear = Integer.parseInt(getDate().split("/")[2]);
            if (Year <= currentYear) {
                Select yearDropDown = new Select(DriverAction.getElement(DashboardLocators.yearDropDown));
                yearDropDown.selectByValue(String.valueOf(Year));
                GemTestReporter.addTestStep("Calendar", "Chosen Year Value is Valid :" + Year, STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Calendar", "Chosen Year Value is Invalid", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("Exception Occurred!", e);
            GemTestReporter.addTestStep("ERROR", "Exception Occurred :" + e.getMessage(), STATUS.FAIL, DriverAction.takeSnapShot());
            throw e;
        }
    }

    @When("^User picks (.+) and (.+) to select date range$")
    public void pickDateRange(int randomStartDate, int randomEndDate) {
        if (randomStartDate > 0 && randomStartDate < 32) {
            String sDate = String.valueOf(randomStartDate);
            List<WebElement> counter = DriverAction.getElements(By.xpath(DashboardLocators.dateSelector.replace("@date", sDate)));
            if (counter.size() == 1) {
                DriverAction.click(By.xpath(DashboardLocators.dateSelector.replace("@date", sDate)));
            } else if (counter.size() > 1) {
                DriverAction.click(By.xpath(DashboardLocators.dateSelectorSecond.replace("@date", sDate)));
            }
            DriverAction.waitSec(3);
        } else {
            GemTestReporter.addTestStep("Calendar", "Chosen Date Value is Invalid", STATUS.FAIL, DriverAction.takeSnapShot());
        }
        if (randomEndDate > 0 && randomEndDate < 32) {
            String eDate = String.valueOf(randomEndDate);
            DriverAction.click(By.xpath(DashboardLocators.dateSelector.replace("@date", eDate)));
            DriverAction.waitSec(3);
        } else {
            GemTestReporter.addTestStep("Calendar", "Chosen Date Value is Invalid", STATUS.FAIL, DriverAction.takeSnapShot());
        }
        DriverAction.click(By.xpath("//div[@class=\"myticket_headingSection__wA3Mu\"]"));
        DriverAction.waitSec(2);
    }

    @Then("^User confirms that the data retrieved is from the (.+) to (.+) date range$")
    public void verifyDataRetrievedFromDateRange(int randomStartDate, int randomEndDate) {
        boolean flag = false;
        DriverAction.scrollIntoView(DashboardLocators.rowsPerPageDropDown);
        DriverAction.waitSec(2);
        DriverAction.scrollIntoView(DashboardLocators.nextBtn);
        List<WebElement> createdOnList = DriverAction.getElements(DashboardLocators.createdOnCol);

        while ((DriverManager.getWebDriver().findElement(DashboardLocators.nextBtn).isEnabled())) {
            DriverAction.waitSec(3);
            for (WebElement webElement : createdOnList) {
                String[] createdOnDate = webElement.getText().split(" ");
                int createdOnDateInt = Integer.parseInt(createdOnDate[0]);
                if (randomStartDate <= createdOnDateInt || createdOnDateInt >= randomEndDate) {
                    flag = true;
                }
            }
            DriverAction.waitSec(1);
            DriverAction.click(DashboardLocators.nextBtn, "Next Side Button");
        }
        if (flag) {
            GemTestReporter.addTestStep("Calendar Range Verification", "Calendar Range Result is Verified", STATUS.PASS, DriverAction.takeSnapShot());
        } else {
            GemTestReporter.addTestStep("Calendar Range Verification", "Calendar Range Result is Not Verified", STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }

    @Then("^User click on Previous Button in Calendar and Verifies it$")
    public void clickPreviousButtonCalendar() {
        Select monthDropDown = new Select(DriverAction.getElement(DashboardLocators.monthDropDown));
        WebElement selectedMonth = monthDropDown.getFirstSelectedOption();
        DriverAction.click(DashboardLocators.previousCalendarButton, "Previous Button in Calendar");
        WebElement selectedMonthAfterPreviousClick = monthDropDown.getFirstSelectedOption();

        if (selectedMonth != selectedMonthAfterPreviousClick) {
            GemTestReporter.addTestStep("Calendar Previous Button", "Calendar Previous Button is Verified", STATUS.PASS, DriverAction.takeSnapShot());
        } else {
            GemTestReporter.addTestStep("Calendar Previous Button", "Calendar Previous Button is Not Verified", STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }

    @Then("^User click on Next Button in Calendar and Verifies it$")
    public void clickNextButtonCalendar() {
        Select monthDropDown = new Select(DriverAction.getElement(DashboardLocators.monthDropDown));
        WebElement selectedMonth = monthDropDown.getFirstSelectedOption();
        DriverAction.click(DashboardLocators.nextCalendarButton, "Next Button in Calendar");
        WebElement selectedMonthAfterNextClick = monthDropDown.getFirstSelectedOption();

        if (selectedMonth != selectedMonthAfterNextClick) {
            GemTestReporter.addTestStep("Calendar Next Button", "Calendar Next Button is Verified", STATUS.PASS, DriverAction.takeSnapShot());
        } else {
            GemTestReporter.addTestStep("Calendar Next Button", "Calendar Next Button is Not Verified", STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }

    @Given("^User clicks on the User Guide Button$")
    public void clickUserGuideButton() {
        try {
            clickOnUserGuideBtn();
        } catch (Exception e) {
            logger.info("Exception Occurred!", e);
            GemTestReporter.addTestStep("ERROR", "Exception Occurred :" + e.getMessage(), STATUS.FAIL, DriverAction.takeSnapShot());
            throw e;
        }
    }

    @Then("^User verifies the User Guide tab$")
    public void verifyUserGuideTab() {
        try {
            Set<String> allWindowHandles1 = DriverManager.getWebDriver().getWindowHandles();
            for (String handle1 : allWindowHandles1) {
                DriverAction.waitSec(2);
                DriverManager.getWebDriver().switchTo().window(handle1);
            }
            if (DriverAction.getElementText(DashboardLocators.userGuideTitle).contains("Helpdesk User Guide")) {
                GemTestReporter.addTestStep("User Guide", "User Guide Tab is Verified", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("User Guide", "User Guide Tab is Not Verified", STATUS.FAIL, DriverAction.takeSnapShot());
            }
            DriverAction.closeCurrentTab();
        } catch (Exception e) {
            logger.info("Exception Occurred!", e);
            GemTestReporter.addTestStep("ERROR", "Exception Occurred :" + e.getMessage(), STATUS.FAIL, DriverAction.takeSnapShot());
            throw e;
        }
    }

    @Given("^User clicks on the Notification Button$")
    public void clickOnNotificationButton() {
        try {
            clickOnNotificationBtn();
        } catch (Exception e) {
            logger.info("Exception Occurred!", e);
            GemTestReporter.addTestStep("ERROR", "Exception Occurred :" + e.getMessage(), STATUS.FAIL, DriverAction.takeSnapShot());
            throw e;
        }
    }

    @Then("^User verify the Notification Panel$")
    public void verifyNotificationPanel() {
        try {
            if (DriverAction.isExist(DashboardLocators.notificationHeading)) {
                GemTestReporter.addTestStep("Notification", "Notification Panel is Verified", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Notification", "Notification Panel is Not Verified", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("Exception Occurred!", e);
            GemTestReporter.addTestStep("ERROR", "Exception Occurred :" + e.getMessage(), STATUS.FAIL, DriverAction.takeSnapShot());
            throw e;
        }
    }

    @Then("^User verify the Show More working$")
    public void verifyShowMoreWorking() {
        try {
            if (DriverAction.isExist(DashboardLocators.showMore)) {
                List<WebElement> notificationsList = DriverAction.getElements(DashboardLocators.notificationsList);
                if (notificationsList.size() > 0) {
                    while (DriverAction.isExist(DashboardLocators.showMore)) {
                        clickOnShowMoreBtn();
                        DriverAction.waitSec(2);
                    }
                    List<WebElement> showMoreNotificationsList = DriverAction.getElements(DashboardLocators.notificationsList);
                    if (showMoreNotificationsList.size() > notificationsList.size()) {
                        GemTestReporter.addTestStep("Notification", "Show More Option is Verified", STATUS.PASS, DriverAction.takeSnapShot());
                    } else {
                        GemTestReporter.addTestStep("Notification", "Show More Option is not Working", STATUS.FAIL, DriverAction.takeSnapShot());
                    }
                } else {
                    GemTestReporter.addTestStep("Notification", "No Notifications are there", STATUS.PASS, DriverAction.takeSnapShot());
                }
            } else {
                GemTestReporter.addTestStep("Notification", "Show More Option does not Exist", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("Exception Occurred!", e);
            GemTestReporter.addTestStep("ERROR", "Exception Occurred :" + e.getMessage(), STATUS.FAIL, DriverAction.takeSnapShot());
            throw e;
        }
    }

    @And("^User switches to Unread panel$")
    public void switchToUnreadPanel() {
        try {
            clickOnUnreadTab();
        } catch (Exception e) {
            logger.info("Exception Occurred!", e);
            GemTestReporter.addTestStep("ERROR", "Exception Occurred :" + e.getMessage(), STATUS.FAIL, DriverAction.takeSnapShot());
            throw e;
        }
    }

    @Then("^User verifies the unread notifications$")
    public void verifyUnreadNotifications() {
        try {
            List<WebElement> notificationsList = DriverAction.getElements(DashboardLocators.notificationsList);
            if (notificationsList.size() > 0) {
                GemTestReporter.addTestStep("Unread Notification", "Unread Notifications are Present", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Unread Notification", "Unread Notifications are not Present", STATUS.PASS, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("Exception Occurred!", e);
            GemTestReporter.addTestStep("ERROR", "Exception Occurred :" + e.getMessage(), STATUS.FAIL, DriverAction.takeSnapShot());
            throw e;
        }
    }


    @Given("^User clicks on the Contact Us Icon$")
    public void clickContactUsIcon() {
        try {
            if (DriverAction.isExist(DashboardLocators.contactUs)) {
                DriverAction.click(DashboardLocators.contactUs);
            } else {
                GemTestReporter.addTestStep("Contact Us", "Contact Us Option is Not Available", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("Exception Occurred!", e);
            GemTestReporter.addTestStep("ERROR", "Exception Occurred :" + e.getMessage(), STATUS.FAIL, DriverAction.takeSnapShot());
            throw e;
        }
    }

    @Then("^User verifies the Contact Icon$")
    public void VerifyContactIcon() {
        if (DriverAction.isExist(DashboardLocators.contactEmail)) {
            GemTestReporter.addTestStep("Contact Us", "Contact Us Option is Verified", STATUS.PASS, DriverAction.takeSnapShot());
        } else {
            GemTestReporter.addTestStep("Contact Us", "Contact Us Option is Not Verified", STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }

    @Given("^User clicks on the Toggle Button to collapse Side Menu$")
    public void clickOnTheToggleButtonToCollapseSideMenu() {
        try {
            clickSideMenuButton();
        } catch (Exception e) {
            logger.info("Exception Occurred!", e);
            GemTestReporter.addTestStep("ERROR", "Exception Occurred :" + e.getMessage(), STATUS.FAIL, DriverAction.takeSnapShot());
            throw e;
        }
    }

    @Then("^User verifies the collapsed Side Menu$")
    public void verifyCollapsedSideMenu() {
        if (!DriverAction.isExist(DashboardLocators.sideMenuMyTickets)) {
            GemTestReporter.addTestStep("Side Menu Button", "Side Menu is Collapsed successfully", STATUS.PASS, DriverAction.takeSnapShot());
        } else {
            GemTestReporter.addTestStep("Side Menu Button", "Side Menu Collapse is Unsuccessful", STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }

    @And("^User clicks on the Toggle Button to expand Side Menu$")
    public void clickOnTheToggleButtonToExpandSideMenu() {
        try {
            clickSideMenuButton();
        } catch (Exception e) {
            logger.info("Exception Occurred!", e);
            GemTestReporter.addTestStep("ERROR", "Exception Occurred :" + e.getMessage(), STATUS.FAIL, DriverAction.takeSnapShot());
            throw e;
        }
    }

    @Then("^User verifies the expanded Side Menu$")
    public void verifyExpandedSideMenu() {
        if (DriverAction.isExist(DashboardLocators.sideMenuMyTickets)) {
            GemTestReporter.addTestStep("Side Menu Button", "Side Menu is Expanded successfully", STATUS.PASS, DriverAction.takeSnapShot());
        } else {
            GemTestReporter.addTestStep("Side Menu Button", "Side Menu Expand is Unsuccessful", STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }

    @And("^User compares them with Unread Tab Notifications count with Unread Header count$")
    public void compareUnreadTabNotificationsCount() {
        String headerCount = DriverAction.getElementText(DashboardLocators.unreadNotificationHeaderCount);
        int newHeaderCount = Integer.parseInt(headerCount);
        if (DriverAction.isExist(DashboardLocators.unreadOption)) {
            DriverAction.click(DashboardLocators.unreadOption);
        } else {
            GemTestReporter.addTestStep("Unread Tab", "Unread Tab does not Exist!", STATUS.FAIL, DriverAction.takeSnapShot());
        }
        while (DriverAction.isExist(DashboardLocators.showMore)) {
            clickOnShowMoreBtn();
        }
        List<WebElement> notificationsList = DriverAction.getElements(DashboardLocators.notificationsList);
        int unreadTabHeaderCount = notificationsList.size();

        if (newHeaderCount == unreadTabHeaderCount) {
            GemTestReporter.addTestStep("Verify Unread Notifications", "Unread Notifications Count is Verified, " + " Header Count :" + newHeaderCount + " Unread Tab Count :" + unreadTabHeaderCount, STATUS.PASS, DriverAction.takeSnapShot());
        } else {
            GemTestReporter.addTestStep("Verify Unread Notifications", "Unread Notifications Count is Not Verified", STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }

    @And("^User clicks on a Ticket from Notifications$")
    public void clickTicketFromNotifications() {
        try {
            List<WebElement> notificationsList = DriverAction.getElements(DashboardLocators.notificationsList);
            if (notificationsList.size() > 0) {
                DriverAction.click(DashboardLocators.firstNotification, "Ticket from Notification");
            } else {
                GemTestReporter.addTestStep("Notification", "No Notifications are there", STATUS.PASS, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("Exception Occurred!", e);
            GemTestReporter.addTestStep("ERROR", "Exception occurred :" + e.getMessage(), STATUS.FAIL, DriverAction.takeSnapShot());
            throw e;
        }
    }

    @Then("^User verifies the appearance of Ticket Details$")
    public void verifyAppearanceOfTicketDetails() {
        try {
            verifyTicketDetailsPage();
        } catch (Exception e) {
            logger.info("Exception Occurred!", e);
            GemTestReporter.addTestStep("ERROR", "Exception Occurred :" + e.getMessage(), STATUS.FAIL, DriverAction.takeSnapShot());
            throw e;
        }
    }

    @Then("^User verify the Check icon is unchecked or not$")
    public void verifyCheckIconUnchecked() {
        DriverAction.waitSec(3);
//        if (!(DriverManager.getWebDriver().findElement(DashboardLocators.checkIcon).isSelected())) {
        if (!(DriverAction.isExist(DashboardLocators.markAllReadIconChecked))) {
            GemTestReporter.addTestStep("Notifications Check Icon", "Check Icon is Unselected", STATUS.PASS, DriverAction.takeSnapShot());
        } else {
            GemTestReporter.addTestStep("Notifications Check Icon", "No Unread Notifications exists", STATUS.PASS, DriverAction.takeSnapShot());
        }
    }

    @And("^User clicks on the Check icon$")
    public void clickCheckIcon() {
        if (DriverAction.isExist(DashboardLocators.checkIcon)) {
            if (DriverAction.isExist(DashboardLocators.markAllReadIconChecked)) {
                GemTestReporter.addTestStep("Notifications Check Icon", "No Unread Notifications Exist", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                Utils.waitForElement(DashboardLocators.checkIcon, 10);
                DriverAction.click(DashboardLocators.checkIcon, "Check Icon");
                DriverAction.waitSec(3);
            }
        } else {
            GemTestReporter.addTestStep("Notifications Check Icon", "Check Icon does not Exist", STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }

    @Then("^User verifies that all notifications are read$")
    public void verifyNotificationsRead() {
        Utils.waitForElement(DashboardLocators.unreadOption, 10);
        DriverAction.click(DashboardLocators.unreadOption);
        DriverAction.waitSec(3);
        if (DriverAction.isExist(DashboardLocators.noNewNotifications)) {
            GemTestReporter.addTestStep("Empty Unread Notification", "No unread notifications left", STATUS.PASS, DriverAction.takeSnapShot());
        } else {
            GemTestReporter.addTestStep("Unread Notification", "Unread notifications are left", STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }

    @Given("^User select (.+) from Rows per page dropdown$")

    public void selectCountFromRowsPerPageDropdown(String dropDownValue) {
        try {
            selectRowsPerPageValue(dropDownValue);
        } catch (Exception e) {
            logger.info("Exception Occurred!", e);
            GemTestReporter.addTestStep("ERROR", "Exception Occurred :" + e.getMessage(), STATUS.FAIL, DriverAction.takeSnapShot());
            throw e;
        }
    }

    @Then("^User verifies that the number of rows is equal to the (.+)$")
    public void verifyRowsEqualToTheSelectedValue(int dropDownValue) {
        try {
            int rowsCount = DriverAction.getElements(DashboardLocators.numberOfRows).size();
            if (dropDownValue == rowsCount) {
                GemTestReporter.addTestStep("Rows Per Page Value", "Number of Rows Per Page : " + rowsCount + " is Equal to the Selected DropDown Value : " + dropDownValue, STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Rows Per Page Value", "Number of Rows Per Page : " + rowsCount + " is Not Equal to the Selected DropDown Value : " + dropDownValue, STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("Exception Occurred!", e);
            GemTestReporter.addTestStep("ERROR", "Exception Occurred :" + e.getMessage(), STATUS.FAIL, DriverAction.takeSnapShot());
            throw e;
        }
    }

    @Given("^User checks Next button should be enabled and Previous button should be disabled on first page$")
    public void checkNextEnabledAndPreviousDisabledFirstPage() {
        try {
            DriverAction.waitSec(2);
            DriverAction.scrollIntoView(DashboardLocators.rowsPerPageDropDown);
            DriverAction.waitSec(2);
            if (!(DriverManager.getWebDriver().findElement(DashboardLocators.previousBtn).isEnabled())) {
                GemTestReporter.addTestStep("Previous Button", "Previous Button is not Enabled!", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Previous Button", "Previous Button is Enabled on First Page!", STATUS.FAIL, DriverAction.takeSnapShot());
            }
            if (DriverManager.getWebDriver().findElement(DashboardLocators.nextBtn).isEnabled()) {
                GemTestReporter.addTestStep("Next Button", "Next Button is Enabled on First Page!", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Next Button", "Next Button is Not Enabled on First Page!", STATUS.FAIL, DriverAction.takeSnapShot());
            }

        } catch (Exception e) {
            logger.info("Exception Occurred!", e);
            GemTestReporter.addTestStep("ERROR", "Exception Occurred :" + e.getMessage(), STATUS.FAIL, DriverAction.takeSnapShot());
            throw e;
        }
    }

    @And("^User clicks on Next button and verify that the previous button is enabled$")
    public void clickNextButtonVerifyPreviousButtonEnabled() {
        try {
            DriverAction.waitSec(2);
            DriverAction.scrollIntoView(DashboardLocators.rowsPerPageDropDown);
            DriverAction.waitSec(2);
            clickNextButton();
            DriverAction.waitSec(2);
            if (DriverManager.getWebDriver().findElement(DashboardLocators.previousBtn).isEnabled()) {
                GemTestReporter.addTestStep("Previous Button", "Previous Button is Enabled!", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Previous Button", "Previous Button is Not Enabled!", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("Exception Occurred!", e);
            GemTestReporter.addTestStep("ERROR", "Exception Occurred :" + e.getMessage(), STATUS.FAIL, DriverAction.takeSnapShot());
            throw e;
        }
    }

    @Then("^User clicks on Previous button and verify that it is disabled now$")
    public void clickPreviousButtonVerifyItIsDisabled() {
        try {
            DriverAction.waitSec(2);
            DriverAction.scrollIntoView(DashboardLocators.rowsPerPageDropDown);
            DriverAction.waitSec(2);
            clickPreviousButton();
            if (!(DriverManager.getWebDriver().findElement(DashboardLocators.previousBtn).isEnabled())) {
                GemTestReporter.addTestStep("Previous Button", "Previous Button is Disabled now!", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Previous Button", "Previous Button is still Enabled!", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("Exception Occurred!", e);
            GemTestReporter.addTestStep("ERROR", "Exception Occurred :" + e.getMessage(), STATUS.FAIL, DriverAction.takeSnapShot());
            throw e;
        }
    }

    @Given("^User writes (.+) in global search box$")
    public void writeTextInSearchBox(String text) {
        Utils.waitForElement(DashboardLocators.searchBox, 20);
//        Utils.presenceOfElement(DashboardLocators.searchBox, 20);
        DriverAction.typeText(DashboardLocators.searchBox, text, "Search Box");
        Utils.waitForElement(DashboardLocators.searchBoxCrossIcon, 20);
//        Utils.presenceOfElement(DashboardLocators.searchBoxCrossIcon, 20);
    }
}
