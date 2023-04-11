@regression
Feature: Dashboard Automation

  Background: HelpDesk, Follow Login Functionality
    Given User launches the HelpDesk
    When User enters Email vishal.gautam@geminisolutions.com and Password R2VtaW5pQDEyMw==
    And User clicks on signIn Button
    And User switches to Employee View


  Scenario: HelpDesk, Check the Name and designation of the User [Employee View]
    Given User checks the LoggedIn UserName <Vishal Gautam> and LoggedIn User Designation <Technical Trainee>

  Scenario: HelpDesk, Verify the functionality of the Side Menu Button
    Given User clicks on the Toggle Button to collapse Side Menu
    Then User verifies the collapsed Side Menu
    And User clicks on the Toggle Button to expand Side Menu
    Then User verifies the expanded Side Menu

  Scenario Outline: HelpDesk, Verify the functionality of sorting is working properly
    Given User verifies the <column> sorting functionality

    Examples:
      | column      |
      | ID          |
      | Subject     |
      | Department  |
      | Created on  |
      | Assigned to |
      | Status      |

  Scenario Outline: HelpDesk, Verify the functionality to Search the data through global search box
    Given User <Enter> data in global search box
    Then User verifies the <Enter> data

    Examples:
      | Enter |
      | Api   |


  Scenario Outline: HelpDesk, Verify the Cross icon functionality in global search box
#    Given User writes api in global search box
    Given User <Enter> data in global search box
    And User click on cross icon
    Then User verify if entered text is removed

    Examples:
      | Enter |
      | Api   |


  Scenario Outline: HelpDesk, Verify if user is able to Search incorrect data
    Given User <Enter> data in global search box
    Then User verify the Negative <Enter> data

    Examples:
      | Enter      |
      | sxdcfvgbhn |


  Scenario: HelpDesk, Verify the calendar functionality
    Given User opens the Calendar
    Then User verifies the calendar


  Scenario: HelpDesk, Verify the Months and WeekDays functionality
    Given User opens the Calendar
    Then User verifies Month count
    Then User verifies WeekDay count

  Scenario: HelpDesk, Verify if user gets on the current date
    Given User opens the Calendar
    Then User verifies the current date


  Scenario: HelpDesk, Verify if user is able to select Random date
    Given User opens the Calendar
    Then User selects and verifies the Random Date - 10, Month - 10, Year - 2019


  Scenario: HelpDesk, Verify if user is able to select specific date range and Verify clear data
    Given User opens the Calendar
    When User chooses 2 Month and 2023 Year
    And User picks 25 and 30 to select date range
    Then User confirms that the data retrieved is from the 25 to 30 date range
    Then User verifies clears data
    Then User verifies the current date


  Scenario: HelpDesk, Verify the functionality of previous and next buttons for calendar
    Given User opens the Calendar
    Then User click on Previous Button in Calendar and Verifies it
    Then User click on Next Button in Calendar and Verifies it

  Scenario: HelpDesk, User verify the Filters
    Given User clicks on the Filter Button
    Then User verifies the Filter Button


  Scenario: HelpDesk, Verify the "Hide cancelled, closed and resolved tickets" functionality
    Given User clicks on the Filter Button
    And User switches on the toggle for 'Hide cancelled, closed and resolved tickets'
    Then User verify that ticket displayed should not be 'Cancelled, closed or resolved'


  Scenario Outline: HelpDesk, Verify is user is able to select Department and Status in Filters and Verify Clear all Filters
    Given User clicks on the Filter Button
    And User selects the <Department> and <Status>
#    Then User Verify the <Department> and <Status>
    Then User clears all filter and verifies it

    Examples:
      | Department                     | Status                                                                         |
      | IT,HR,Accounts,Admin,test dept | Open,Unassigned ,Assigned,On Hold,Resolved,Cancelled,Closed,Re-opened,Rejected |
      | IT                             | Open,Unassigned ,Assigned,On Hold,Resolved,Cancelled,Closed,Re-opened,Rejected |
      | IT                             | Unassigned ,Assigned,On Hold,Resolved,Cancelled,Closed,Re-opened,Rejected      |
      | IT                             | Assigned,On Hold,Resolved,Cancelled,Closed,Re-opened,Rejected                  |
      | IT                             | On Hold,Resolved,Cancelled,Closed,Re-opened,Rejected                           |
      | IT                             | Resolved,Cancelled,Closed,Re-opened,Rejected                                   |
      | IT                             | Cancelled,Closed,Re-opened,Rejected                                            |
      | IT                             | Closed,Re-opened,Rejected                                                      |
      | IT                             | Re-opened,Rejected                                                             |
      | IT                             | Rejected                                                                       |
      | HR                             | Open,Unassigned ,Assigned,On Hold,Resolved,Cancelled,Closed,Re-opened,Rejected |
      | HR                             | Unassigned ,Assigned,On Hold,Resolved,Cancelled,Closed,Re-opened,Rejected      |
      | HR                             | Assigned,On Hold,Resolved,Cancelled,Closed,Re-opened,Rejected                  |
      | HR                             | On Hold,Resolved,Cancelled,Closed,Re-opened,Rejected                           |
      | HR                             | Resolved,Cancelled,Closed,Re-opened,Rejected                                   |
      | HR                             | Cancelled,Closed,Re-opened,Rejected                                            |
      | HR                             | Closed,Re-opened,Rejected                                                      |
      | HR                             | Re-opened,Rejected                                                             |
      | HR                             | Rejected                                                                       |
      | Accounts                       | Open,Unassigned ,Assigned,On Hold,Resolved,Cancelled,Closed,Re-opened,Rejected |
      | Accounts                       | Unassigned ,Assigned,On Hold,Resolved,Cancelled,Closed,Re-opened,Rejected      |
      | Accounts                       | Assigned,On Hold,Resolved,Cancelled,Closed,Re-opened,Rejected                  |
      | Accounts                       | On Hold,Resolved,Cancelled,Closed,Re-opened,Rejected                           |
      | Accounts                       | Resolved,Cancelled,Closed,Re-opened,Rejected                                   |
      | Accounts                       | Cancelled,Closed,Re-opened,Rejected                                            |
      | Accounts                       | Closed,Re-opened,Rejected                                                      |
      | Accounts                       | Re-opened,Rejected                                                             |
      | Accounts                       | Rejected                                                                       |
      | Admin                          | Open,Unassigned ,Assigned,On Hold,Resolved,Cancelled,Closed,Re-opened,Rejected |
      | Admin                          | Unassigned ,Assigned,On Hold,Resolved,Cancelled,Closed,Re-opened,Rejected      |
      | Admin                          | Assigned,On Hold,Resolved,Cancelled,Closed,Re-opened,Rejected                  |
      | Admin                          | On Hold,Resolved,Cancelled,Closed,Re-opened,Rejected                           |
      | Admin                          | Resolved,Cancelled,Closed,Re-opened,Rejected                                   |
      | Admin                          | Cancelled,Closed,Re-opened,Rejected                                            |
      | Admin                          | Closed,Re-opened,Rejected                                                      |
      | Admin                          | Re-opened,Rejected                                                             |
      | Admin                          | Rejected                                                                       |
      | test dept                      | Open,Unassigned ,Assigned,On Hold,Resolved,Cancelled,Closed,Re-opened,Rejected |
      | test dept                      | Unassigned ,Assigned,On Hold,Resolved,Cancelled,Closed,Re-opened,Rejected      |
      | test dept                      | Assigned,On Hold,Resolved,Cancelled,Closed,Re-opened,Rejected                  |
      | test dept                      | On Hold,Resolved,Cancelled,Closed,Re-opened,Rejected                           |
      | test dept                      | Resolved,Cancelled,Closed,Re-opened,Rejected                                   |
      | test dept                      | Cancelled,Closed,Re-opened,Rejected                                            |
      | test dept                      | Closed,Re-opened,Rejected                                                      |
      | test dept                      | Re-opened,Rejected                                                             |
      | test dept                      | Rejected                                                                       |
      | IT,HR                          | Open,Unassigned ,Assigned,On Hold,Resolved,Cancelled,Closed,Re-opened,Rejected |
      | IT,HR                          | Unassigned ,Assigned,On Hold,Resolved,Cancelled,Closed,Re-opened,Rejected      |
      | IT,HR                          | Assigned,On Hold,Resolved,Cancelled,Closed,Re-opened,Rejected                  |
      | IT,HR                          | On Hold,Resolved,Cancelled,Closed,Re-opened,Rejected                           |
      | IT,HR                          | Resolved,Cancelled,Closed,Re-opened,Rejected                                   |
      | IT,HR                          | Cancelled,Closed,Re-opened,Rejected                                            |
      | IT,HR                          | Closed,Re-opened,Rejected                                                      |
      | IT,HR                          | Re-opened,Rejected                                                             |
      | IT,HR                          | Rejected                                                                       |
      | IT,HR,Accounts                 | Open,Unassigned ,Assigned,On Hold,Resolved,Cancelled,Closed,Re-opened,Rejected |
      | IT,HR,Accounts                 | Unassigned ,Assigned,On Hold,Resolved,Cancelled,Closed,Re-opened,Rejected      |
      | IT,HR,Accounts                 | Assigned,On Hold,Resolved,Cancelled,Closed,Re-opened,Rejected                  |
      | IT,HR,Accounts                 | On Hold,Resolved,Cancelled,Closed,Re-opened,Rejected                           |
      | IT,HR,Accounts                 | Resolved,Cancelled,Closed,Re-opened,Rejected                                   |
      | IT,HR,Accounts                 | Cancelled,Closed,Re-opened,Rejected                                            |
      | IT,HR,Accounts                 | Closed,Re-opened,Rejected                                                      |
      | IT,HR,Accounts                 | Re-opened,Rejected                                                             |
      | IT,HR,Accounts                 | Rejected                                                                       |
      | IT,HR,Accounts,Admin           | Open,Unassigned ,Assigned,On Hold,Resolved,Cancelled,Closed,Re-opened,Rejected |
      | IT,HR,Accounts,Admin           | Unassigned ,Assigned,On Hold,Resolved,Cancelled,Closed,Re-opened,Rejected      |
      | IT,HR,Accounts,Admin           | Assigned,On Hold,Resolved,Cancelled,Closed,Re-opened,Rejected                  |
      | IT,HR,Accounts,Admin           | On Hold,Resolved,Cancelled,Closed,Re-opened,Rejected                           |
      | IT,HR,Accounts,Admin           | Resolved,Cancelled,Closed,Re-opened,Rejected                                   |
      | IT,HR,Accounts,Admin           | Cancelled,Closed,Re-opened,Rejected                                            |
      | IT,HR,Accounts,Admin           | Closed,Re-opened,Rejected                                                      |
      | IT,HR,Accounts,Admin           | Re-opened,Rejected                                                             |
      | IT,HR,Accounts,Admin           | Rejected                                                                       |
      | IT,HR,Accounts,Admin,test dept | Open,Unassigned ,Assigned,On Hold,Resolved,Cancelled,Closed,Re-opened,Rejected |
      | IT,HR,Accounts,Admin,test dept | Unassigned ,Assigned,On Hold,Resolved,Cancelled,Closed,Re-opened,Rejected      |
      | IT,HR,Accounts,Admin,test dept | Assigned,On Hold,Resolved,Cancelled,Closed,Re-opened,Rejected                  |
      | IT,HR,Accounts,Admin,test dept | On Hold,Resolved,Cancelled,Closed,Re-opened,Rejected                           |
      | IT,HR,Accounts,Admin,test dept | Resolved,Cancelled,Closed,Re-opened,Rejected                                   |
      | IT,HR,Accounts,Admin,test dept | Cancelled,Closed,Re-opened,Rejected                                            |
      | IT,HR,Accounts,Admin,test dept | Closed,Re-opened,Rejected                                                      |
      | IT,HR,Accounts,Admin,test dept | Re-opened,Rejected                                                             |
      | IT,HR,Accounts,Admin,test dept | Rejected                                                                       |


  Scenario: HelpDesk, Verify if user is able to close Filter Tab
    Given User clicks on the Filter Button
    And User closes Filter Tab
    Then User verifies the closed filter tab


  Scenario: HelpDesk, Verify the User Guide Functionality
    Given User clicks on the User Guide Button
    Then User verifies the User Guide tab


  Scenario: HelpDesk, Verify the Notification Functionality and Unread Notification Count
    Given User clicks on the Notification Button
    Then User verify the Notification Panel
    And User compares them with Unread Tab Notifications count with Unread Header count


  Scenario: HelpDesk, Verify the Show More Option
    Given User clicks on the Notification Button
    Then User verify the Show More working

  Scenario: HelpDesk, Verify the functionality of Mark all Read icon
    Given User clicks on the Notification Button
    Then User verify the Check icon is unchecked or not
    And User clicks on the Check icon
    Then User verifies that all notifications are read

  Scenario: HelpDesk, Navigate to a Ticket from Notifications
    Given User clicks on the Notification Button
    And User clicks on a Ticket from Notifications
    Then User verifies the appearance of Ticket Details

    # Might delete in Future
#  Scenario: HelpDesk, Verify the Unread Notifications
#    Given User clicks on the Notification Button
#    And User switches to Unread panel
#    Then User verifies the unread notifications

  Scenario Outline: HelpDesk, Verify Rows per page
    Given User select <value> from Rows per page dropdown
    Then User verifies that the number of rows is equal to the <value>
    Examples:
      | value |
      | 5     |
      | 10    |
      | 25    |

  Scenario: HelpDesk, Verify Next/Previous Button functionalities
    Given User checks Next button should be enabled and Previous button should be disabled on first page
    And User clicks on Next button and verify that the previous button is enabled
    Then User clicks on Previous button and verify that it is disabled now

  Scenario: HelpDesk, Verify the Contact Us
    Given User clicks on the Contact Us Icon
    Then User verifies the Contact Icon

