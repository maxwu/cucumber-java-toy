Feature:  Browse the temperature converter page
  As a developer,
  In order to convert temperature
  I want to enter number and check result on google search page "temperature convertor".

  Scenario: Open Temperature Convertor
    Given Google Entrance Page
    When  Search "temperature converter"
    Then  The page title is "temperature converter"

#####################################################