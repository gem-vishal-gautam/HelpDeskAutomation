@regression
Feature: Ticket Cancellation Automation

  Background: HelpDesk, Follow Login Functionality
    Given User launches the HelpDesk
    When User enters Email vishal.gautam@geminisolutions.com and Password R2VtaW5pQDEyMw==
    And User clicks on signIn Button
    And User switches to Employee View


  Scenario Outline: HelpDesk, Verify Ticket Cancellation - Incident Type
    Given User clicks on New Ticket Button
    When User Creates an Incident Ticket, fills the Subject - <Subject>, Description - <Description>, and Department - <Department>
    Then User verifies the Incident Ticket with Subject - <Subject>, Description - <Description>, and Department - <Department>
    And User clicks on Submit button
    And User clicks on Continue button
    And The user obtains the newly created Ticked ID and searches it
    And User cancels the ticket from action button and provides a Reason - <Reason>
    Then User verify the canceled Ticket

    Examples:
      | Subject     | Description                | Department | Reason    |
      | New Subject | New Description            | IT         | Glitching |
      | New IT      | New Description - IT       | IT         | Glitching |
      | NewAccounts | New Description - Accounts | Accounts   | Glitching |
      | NewHR       | New Description - HR       | HR         | Glitching |
      | NewAdmin    | New Description - Admin    | Admin      | Glitching |


  Scenario Outline: HelpDesk, Verify Ticket Cancellation - Incident Type with File
    Given User clicks on New Ticket Button
    When User Creates an Incident Ticket, fills the Subject - <Subject>, Description - <Description>, and Department - <Department>
    And User uploads the File - <file>
    Then User verifies the file upload
    And User clicks on Submit button
    And User clicks on Continue button
    And The user obtains the newly created Ticked ID and searches it
    And User cancels the ticket from action button and provides a Reason - <Reason>
    Then User verify the canceled Ticket

    Examples:
      | Subject     | Description                | Department | file                                                       | Reason    |
      | New Subject | New Description            | IT         | \\src\\test\\resources\\sampleFiles\\file-sample_100kB.doc | Glitching |
      | NewAccounts | New Description - Accounts | Accounts   | \\src\\test\\resources\\sampleFiles\\file-sample_100kB.doc | Glitching |
      | NewHR       | New Description - HR       | HR         | \\src\\test\\resources\\sampleFiles\\file-sample_100kB.doc | Glitching |
      | NewAdmin    | New Description - Admin    | Admin      | \\src\\test\\resources\\sampleFiles\\file-sample_100kB.doc | Glitching |


  Scenario: HelpDesk, Verify Ticket Cancellation - Request Type
    Given User clicks on New Ticket Button
    When User Creates a Request Ticket, fills the Subject - New Subject, Description - New Description, Department - IT, Category - sample req type, and Sub-category - sub type 1
    Then User verifies the Request Ticket with Subject - New Subject, Description - New Description, Department - IT, Category - sample req type, and Sub-category - sub type 1
    And User clicks on Submit button
    And User clicks on Continue button
    And The user obtains the newly created Ticked ID and searches it
    And User cancels the ticket from action button and provides a Reason - Glitching
    Then User verify the canceled Ticket


  Scenario: HelpDesk, Verify Ticket Cancellation - Request Type with File
    Given User clicks on New Ticket Button
    When User Creates a Request Ticket, fills the Subject - New Subject, Description - New Description, Department - IT, Category - sample req type, and Sub-category - sub type 1
    And User uploads the File - \\src\\test\\resources\\sampleFiles\\file-sample_100kB.doc
    Then User verifies the file upload
    And User clicks on Submit button
    And User clicks on Continue button
    And The user obtains the newly created Ticked ID and searches it
    And User cancels the ticket from action button and provides a Reason - Glitching
    Then User verify the canceled Ticket


  Scenario: HelpDesk, Verify if user is able to cancel Random Ticket
    Given User searches random REQ-000002922
    Then User verifies searched REQ-000002922 is present
    And User cancels the ticket from action button and provides a Reason - Glitching
    Then User verifies the Random Cancelled REQ-000002922


  Scenario: HelpDesk, Verify if ticket cancels from action button
    Given User clicks on New Ticket Button
    When User Creates a Request Ticket, fills the Subject - New Subject, Description - New Description, Department - IT, Category - sample req type, and Sub-category - sub type 1
    Then User verifies the Request Ticket with Subject - New Subject, Description - New Description, Department - IT, Category - sample req type, and Sub-category - sub type 1
    And User clicks on Submit button
    And User clicks on Continue button
    And The user obtains the newly created Ticked ID and searches it
    Then User verify that action button should be Enabled for newly created ticket
    And User cancels the ticket from action button and provides a Reason - Glitching
    Then User verify the canceled Ticket
    Then User verify that action button should be Disabled for cancelled ticket


  Scenario: HelpDesk, Verify if ticket cancels from Ticket Details Page
    Given User clicks on New Ticket Button
    When User Creates a Request Ticket, fills the Subject - New Subject, Description - New Description, Department - IT, Category - sample req type, and Sub-category - sub type 1
    Then User verifies the Request Ticket with Subject - New Subject, Description - New Description, Department - IT, Category - sample req type, and Sub-category - sub type 1
    And User clicks on Submit button
    And User clicks on Continue button
    And The user obtains the newly created Ticked ID and searches it
    Then User clicks on the Ticket ID and verify Ticket Details Page
    And User cancels the ticket from Ticket Details Page and provides a Reason - Glitching
    Then User verify the canceled Ticket


  Scenario: HelpDesk, Verify Ticket Cancellation without providing Reason
    Given User clicks on New Ticket Button
    When User Creates an Incident Ticket, fills the Subject - <Subject>, Description - <Description>, and Department - <Department>
    Then User verifies the Incident Ticket with Subject - <Subject>, Description - <Description>, and Department - <Department>
    And User clicks on Submit button
    And User clicks on Continue button
    And The user obtains the newly created Ticked ID and searches it
    And User cancels the ticket from action button and without providing a Reason
    Then User confirms that the ticket should not be cancelled

