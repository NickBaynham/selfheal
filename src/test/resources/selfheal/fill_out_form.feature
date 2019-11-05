Feature: As a tester, I want to fill out the form on the page
  Scenario: Form Entry using automatic locators
    Given I have a "Chrome" browser open
    When I navigate to "http://localhost:1337/example2.html"
    Then I enter "Scott" in the "first Name" field
    Then I enter "Steele" in the "last" field
    Then I enter "53" in the "Age" field
    Then I enter "Senior Network Administrator" in the "occ" field
    Then I enter "Sushi" in the "Food" field
    Then I close the browser
