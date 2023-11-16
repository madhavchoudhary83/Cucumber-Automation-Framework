@dp2 @regression
Feature: Bulls - Home Page

  Background:
    Given I navigate to bulls home page

  @footer @attachoutput
  Scenario: Validate footer links (TC#4)
    When I navigate to footer section
    And I am presented with links across different sections under footer


