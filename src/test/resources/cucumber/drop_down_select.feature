Feature: As a tester, I want to select from a drop-down select element
  Scenario: Select an option from a Drop-down
    Given I have a "Chrome" browser
    When I navigate to "http://demo.guru99.com/test/newtours/register.php"
    Then I select "UNITED STATES" from the "country" drop-down
    Then I close the browser
