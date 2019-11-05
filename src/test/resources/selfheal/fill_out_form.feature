Feature: As a tester, I want to fill out the form on the page
  Scenario: Form Entry using automatic locators
    Given I have a "ChromeDriver" instance
    When I navigate to "http://localhost:1337/example2.html"
    Then I enter "Scott" in the "firstName" field
    Then I enter "Steele" in the "lastName" field
    Then I enter "53" in the "age" field
    Then I enter "Senior Network Administrator" in the "occupation" field
    Then I enter "Sushi" in the "favoriteFood" field
    Then I close the browser
