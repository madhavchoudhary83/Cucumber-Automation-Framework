@cp @regression
Feature: Warriors - Shop Menu

  Background:
    Given I navigate to warriors home page

  @shop @@attachoutput
  Scenario: Validate jackets on men's page (TC#1)
    When I select Mens option from Shop menu
  #  And I find for Jacket using search bar
    And I filter for Jackets under All Departments using side navigation bar
    Then I should be presented with list of Jackets across pages