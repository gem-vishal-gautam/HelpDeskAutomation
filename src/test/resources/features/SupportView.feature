Feature: Support View Automation

  Background: HelpDesk, Follow Login Functionality
    Given User launches the HelpDesk
    When User enters Email vishal.gautam@geminisolutions.com and Password R2VtaW5pQDEyMw==
    And User clicks on signIn Button

#  Scenario Outline: HelpDesk, Verify Support View Ticket Creation - Incident Type
#    Given User clicks on New Ticket Button
#    When <Caller> is selected by the user
#    And User checks the caller details and past tickets
#    And User clicks on Submit button
#    Examples:
#      | Caller         | Channel    | Subject     | Description     | Department |
#      | Vishal Gautam | Phone Call | New Subject | New Description | IT         |

  Scenario Outline: HelpDesk, Check whether the various headers are present or not
    Given User looks over the current <headers>
    Then User verify the headers

    Examples:
      | headers       |
      | My Department |
      | Assigned      |
      | Unassigned    |
      | Others        |


  Scenario: HelpDesk, Check the Name and designation of the User
    Given User checks the LoggedIn UserName <Vishal Gautam> and LoggedIn User Designation <Technical Trainee>


  Scenario: HelpDesk, Verify the Assigned UserName in Assigned Tab
    Given User is in the Assigned Tab
    And User fetches the UserName from the Assigned To Tab and verifies it

