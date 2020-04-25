Feature: As a tester, I want to click on a radio button to select from possible states and to verify state
  Scenario: Check the state, select options, verify option selected
    Given I have a "Chrome" browser
    When I navigate to "http://demo.guru99.com/test/radio.html"
    Then radio button option "Option1" is not selected

    When I click on radio button "Option1"
    Then radio button "Option1" is selected
    Then I close the browser
