Feature: As a tester, I want to get a list of all of the input fields on a page
  Scenario: Getting a list of input fields and displaying the count
    Given I have a "Chrome" browser
    When I navigate to "http://localhost:1337/example2.html"
    Then the number of inputs found should be "5"
