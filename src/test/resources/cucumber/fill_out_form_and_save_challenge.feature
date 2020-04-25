# Requires running local server: Go to html folder in resources and run server.bat
Feature: As a tester, I want to fill out the form on the page and save it
  Scenario: Form Entry using automatic locators
    Given I have a "Chrome" browser
    When I navigate to "http://localhost:7800/example3b.html"
    Then I enter "Scott" in the "first Name" field
    Then I enter "Steele" in the "last" field
    Then I enter "53" in the "Age" field
    Then I enter "System Admin" in the "occ" field
    Then I enter "Sushi" in the "Food" field
    Then I click the "Save" button
    Then I close the browser
