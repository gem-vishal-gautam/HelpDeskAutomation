package com.qa.helpdesk.locators;

import org.openqa.selenium.By;

public class DashboardLocators {

    //SortingTestCases
    //public static By idBtn = By.xpath("//th[@id='ID']");
    public static By getSortingHeading(String headingName) {
        return By.xpath("//*[text()= '" + headingName + "']");
    }

    public static By idCol = By.xpath("//tr/td/a");
    public static By tickedID = By.xpath("(//tr/td/a)[1]");
    public static By subjectCol = By.xpath("//tr/td[2]");
    public static By departmentCol = By.xpath("//tbody/tr/td[3]");
    public static By createdOnCol = By.xpath("//tbody/tr/td[5]");
    public static By assignedToCol = By.xpath("//tbody/tr/td[6]");
    public static By statusCol = By.xpath("//tbody/tr/td[7]");

    //SearchTestCases
    public static By searchBoxCrossIcon = By.xpath("//img[@alt='cross']");
    public static By searchBox = By.xpath("//input[@id='search']");
    public static By searchBtn = By.xpath("//button[@type=\"submit\" and text()='Search']");
    public static By resultList = By.xpath("//tbody/tr");
    public static By noData = By.xpath("//img[@alt='No Data !']");


    //CalendarTestCases
    public static By previousCalendarButton = By.xpath("//button[@class='rdrNextPrevButton rdrPprevButton']");
    public static By nextCalendarButton = By.xpath("//button[@class='rdrNextPrevButton rdrNextButton']");
    public static By currentMonth = By.xpath("//span[@class=\"rdrMonthPicker\" and @xpath=\"1\"]");

    public static By calendarImg = By.xpath("//img[@alt='calender']");
    public static By calendarBox = By.xpath("(//div[@class='position-relative']/div/div)[1]");
    public static By currentDate = By.xpath("//button[contains(@class,'DayToday')]/span/span");
    public static By monthDropDown = By.xpath("//span[@class=\"rdrMonthPicker\"]/select");
    public static By yearDropDown = By.xpath("//span[@class=\"rdrYearPicker\"]/select");
    public static By randomDate = By.xpath("//span[@class=\"rdrDayNumber\"]");
    public static String dateSelector = "//span[@class=\"rdrDayNumber\"]/span[text()='@date']";
    public static String dateSelectorSecond = "(//span[@class=\"rdrDayNumber\"]/span[text()='@date'])[2]";
    public static By monthCount = By.xpath("//span[@class=\"rdrMonthPicker\"]//option");
    public static By weekDayCount = By.xpath("//span[@class='rdrWeekDay']");
    public static By clearData = By.xpath("//button[text()='Clear Data']");

    //FilterTestCases

    public static By filterBtn = By.xpath("(//button[@type='submit'])[2]");
    public static By filterTicketsToggle = By.xpath("//input[@type='checkbox']");
    public static By filtersHeading = By.xpath("//div[text()='Filters']");
    public static By itOption = By.xpath("//button[text()='IT']");

    public static By departmentName(String name) {
        return By.xpath("//button[text()='" + name + "']");
    }

    public static By unassignedDepartment = By.xpath("(//button[@class=\"filters_filters__VVFJc my-2 border-none d-flex flex-row\"])[7]");

    public static By statusName(String name) {
        return By.xpath("//button[text()='" + name + "']");
    }

    public static By selectedValue = By.xpath("//button[@class='filters_filters__VVFJc my-2 border-none d-flex flex-row filters_filterSelected__JbfhU']");
    public static By clearAllFiltersButton = By.xpath("//button[text()='Clear all']");
    public static By crossIcon = By.xpath("//img[@alt=\"cross icon\"]");
    public static By userGuideIcon = By.xpath("//img[@alt='userGuide']");
    public static By userGuideTitle = By.xpath("//span[text()='Helpdesk User Guide.pdf']");
    public static By notificationIcon = By.xpath("//button[@type='button']/img[@alt='notification']");
    public static By allOption = By.xpath("//div[text()='All']");
    public static By unreadOption = By.xpath("//div[text()='Unread']");
    public static By notificationHeading = By.xpath("//span[text()='Notifications']");
    public static By notificationCheckIcon = By.xpath("//span/img[@alt='check']");
    public static By showMore = By.xpath("//u[text()='Show More']");
    public static By notificationsList = By.xpath("//div[@class=\"notification_mainBody__E6eFf\"]//li");
    public static By firstNotification = By.xpath("(//div[@class=\"notification_mainBody__E6eFf\"]//li)[1]");
    public static By noNewNotifications = By.xpath("//div[text()='No new notification !!']");
    public static By contactUs = By.xpath("//img[@alt='Support']");
    public static By contactEmail = By.xpath("//p[text()='support.helpdesk@geminisolutions.com']");
    public static By sideMenuButton = By.xpath("//img[@alt='toggle icon']");
    public static By sideMenuMyTickets = By.xpath("//a[@class='menuitem_navItem__xo3ke menuitem_menuItem__FmRos active']//span[contains(text(),'My Tickets')]");
    public static By unreadNotificationHeaderCount = By.xpath("//div[@class=\"header_count__-dTHO\"]");
    public static By checkIcon = By.xpath("//img[@alt='check']");
    public static By rowsPerPageDropDown = By.xpath("//select[@aria-label=\"rows per page\"]");
    public static By numberOfRows = By.xpath("//tbody/tr");
    public static By previousBtn = By.xpath("//button[@title='Go to previous page']");
    public static By nextBtn = By.xpath("//button[@title='Go to next page']");

    public static By markAllReadIconChecked = By.xpath("//img[@src='../assets/ICON_Check_Active.svg']");
}

