Feature: Is it Friday yet?
  Everybody wants to know if it's Friday

  Scenario: Sunday isn't Friday
    Given today is Sunday
    When I ask whether it is Friday or not
    Then I should be told "No"
