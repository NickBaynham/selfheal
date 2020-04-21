# Requires running local server: Go to html folder in resources and run server.bat
# (or on mac,python -m SimpleHTTPServer 7800 from terminal)
Feature: As a tester, I want to see a popover when the automation runs, to show me the test step, data, and locator that is used
  Scenario: Form Entry using automatic locators
    Given I have a "Chrome" browser
    When I navigate to "http://localhost:7800/example2.html"
    And I inject bootstrap into the page
    Then I enter "Scott" in the "first Name" field with test info displayed in a popover
    Then I close the browser
