Feature: Can we navigate to the page and build data structures from the document?
We want to navigate to a page, pull the HTML Document, and print it out on the terminal
  Scenario: Getting the HTML Source Code
    Given I have a "ChromeDriver" instance
    When I navigate to "http://localhost:1337/example1.html"
    Then I should be able to pull the HTML and print it
    Then I close the browser

