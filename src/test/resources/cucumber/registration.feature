# Requires running local server: Go to html folder in resources and run server.bat
# Or on Mac, enter the same command in the file into terminal
Feature: New User Registration
  Scenario: As a new user, I perform the registration workflow
    Given I have a "Chrome" browser
    When I navigate to "http://localhost:7800/bootstrap1.html"
    Then I enter "Ada" in the "First Name" field
    Then I enter "Lovelace" in the "Last Name" field
    Then I enter "ALovelace" in the "Username" field
    Then I enter "Orlando" in the "City" field
    Then I enter "FL" in the "State" field
    Then I enter "32832" in the "Zip" field
    Then I click the "Agree to terms and conditions" checkbox
    Then I click the "Register" button
    Then I close the browser
