@cp @regression
Feature: Warriors - News & Features Menu

  Background:
    Given I navigate to warriors home page

  @news
  Scenario: Validate videos feeds on News Page (TC#2)
    When I select News & Features option from secondary menu
    And I should be presented with News Page
    Then I should be presented with Video Feeds on page



