Feature:  Browse Github User Profile and Seek social links
  As a developer,
  In order to get all followed users
  I want to visit user profile page and collect followed users

  Background: Web Browser is working
    Given Web browser initialized for Github

  @github
  Scenario: Find followed users
    Given Github User Page on following tab with "maxwu"
    When  Find all followed users
    Then  "goteststar" is one of them