Feature: As a tester, I want to click on a check box to enable it and verify it is selected
  Scenario: Check Box actions and status using automatic locators
    Given I have a "Chrome" browser
    When I navigate to "http://demo.guru99.com/test/radio.html"
    Then checkbox named "Checkbox1" is not selected

    When I click the "Checkbox1" checkbox
    Then checkbox named "Checkbox1" is selected
    Then I close the browser
