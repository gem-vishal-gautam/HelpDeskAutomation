@regression
Feature: HelpDesk Log-in

  Scenario: HelpDesk, Launch HelpDesk, perform SignIn and SignOut
    Given User launches the HelpDesk
    When User enters Email vishal.gautam@geminisolutions.com and Password R2VtaW5pQDEyMw==
    And User clicks on signIn Button
    Then User verifies the expectedUrl LogIn on HelpDesk
    And User SignOut the HelpDesk
    Then User verifies the LogOut https://helpdeskui-np.geminisolutions.com/#/


  Scenario: HelpDesk, Launch HelpDesk, perform SignIn with wrong credentials
    Given User launches the HelpDesk
    When User enters Incorrect credentials and clicks on SignIn
    Then Verify LogIn is Unsuccessful

